// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.modules.websocket;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import indi.zxiaozhou.skillfull.corecommon.base.model.MqMessageMsgModel;
import indi.zxiaozhou.skillfull.corecommon.base.model.UserAndAuthModel;
import indi.zxiaozhou.skillfull.corecommon.constant.MsgBusinessType;
import indi.zxiaozhou.skillfull.corecommon.constant.MsgType;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import indi.zxiaozhou.skillfull.corecommon.utils.TokenUtils;
import indi.zxiaozhou.skillfull.corewebflux.utils.ContextHolderUtils;
import indi.zxiaozhou.skillfull.corewebflux.utils.MessageUtils;
import indi.zxiaozhou.skillfull.corewebflux.utils.ServletUtils;
import indi.zxiaozhou.skillfull.message.core.annotation.WebSocketEndpoint;
import indi.zxiaozhou.skillfull.message.core.config.model.WebSocketMsgModel;
import indi.zxiaozhou.skillfull.message.core.message.ReceiveMsgProcessor;
import indi.zxiaozhou.skillfull.message.utils.WebSocketRedisUtil;
import io.micrometer.core.lang.NonNullApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.CloseStatus;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


/**
 * websocket 消息
 *
 * @author zxiaozhou
 * @date 2021-01-13 22:50
 * @since JDK11
 */
@Component
@Slf4j
@WebSocketEndpoint("/websocket/msg")
@RequiredArgsConstructor
@NonNullApi
public class WebSocketMsgService implements WebSocketHandler {
    private final ConcurrentHashMap<String, WebSocketMsgModel> senderMsgMap;

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        String token = ServletUtils.getToken(session.getHandshakeInfo());
        // 出站
        Mono<Void> output = session.send(Flux.create(sink -> {
            try {
                senderMsgMap.put(TokenUtils.getTokenSystemId(token), new WebSocketMsgModel(sink, session, token));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        // 入站
        Mono<Void> input = session.receive()
                .doOnSubscribe(conn -> this.onOpen(session, token))
                .doOnNext(msg -> this.onMessage(msg, token)).then();
        // 合并
        return Mono.zip(input, output).doFinally(signalType -> {
            WebSocketMsgModel model = senderMsgMap.remove(TokenUtils.getTokenSystemId(token));
            if (Objects.nonNull(model)) {
                WebSocketRedisUtil.deleteMsgUser(model.getUserId(), model.getSystemId());
            }
        }).then();
    }

    /**
     * 收到消息
     *
     * @param msg   ${@link WebSocketMessage} 消息
     * @param token ${@link String} token
     * @author zxiaozhou
     * @date 2021-01-25 15:51
     */
    private void onMessage(WebSocketMessage msg, String token) {
        boolean isRefreshToken = false;
        String systemId = "";
        // 判断是否为刷新token
        MqMessageMsgModel mqMessageChartModel = JSONObject.parseObject(msg.getPayloadAsText(), MqMessageMsgModel.class);
        if (mqMessageChartModel.getType() == MsgType.REFRESH_TOKEN.getType()) {
            token = mqMessageChartModel.getMsg();
            isRefreshToken = true;
        }
        try {
            systemId = TokenUtils.getTokenSystemId(token);
            if (isRefreshToken) {
                update(systemId, token);
            }
            // 验证用户是否登录过期
            UserAndAuthModel userAndAuthModel = ContextHolderUtils.getUserAndAuthModel(token);
            MqMessageMsgModel mqMessageMsgModel = JSONObject.parseObject(msg.getPayloadAsText(), MqMessageMsgModel.class);
            // 补全发送消息的人员信息
            if (StringUtils.isBlank(mqMessageMsgModel.getSendUserId())) {
                mqMessageMsgModel.setSendUserId(userAndAuthModel.getUserId());
                String userName = StringUtils.isNotBlank(userAndAuthModel.getRealName()) ? userAndAuthModel.getRealName() : userAndAuthModel.getNickName();
                mqMessageMsgModel.setSendUserName(userName);
            }
            // 发送消息到mq,由mq进行广播订阅者
            if (!isRefreshToken) {
                MessageUtils.sendMsg(mqMessageMsgModel);
            }
        } catch (Exception e) {
            if (StringUtils.isNotBlank(systemId)) {
                boolean isSend = false;
                if (e instanceof ResponseException) {
                    ResponseException responseException = (ResponseException) e;
                    // 移除链接缓存
                    if (responseException.getResult().getCode() == Status.TOKEN_EXPIRED.getCode()) {
                        MqMessageMsgModel model = new MqMessageMsgModel(Status.TOKEN_EXPIRED.getMessage(), MsgType.ONE_TO_ONE.getType());
                        model.setBusinessType(MsgBusinessType.LOGOUT.getType());
                        senderMsgMap.get(systemId).sendData(MessageUtils.getWebSocketMsg(model));
                        removeCache(systemId);
                        isSend = true;
                    }
                }
                if (!isSend) {
                    senderMsgMap.get(systemId).sendData(MessageUtils.getWebSocketErrorMsg(e.getMessage()));
                }
            }

        }
    }


    /**
     * 打开链接
     *
     * @param session ${@link WebSocketSession} session
     * @param token   ${@link String} token
     * @author zxiaozhou
     * @date 2021-01-25 15:51
     */
    private void onOpen(WebSocketSession session, String token) {
        try {
            // 获取token并校验用户登录情况
            WebSocketRedisUtil.addMsgConnect(token);
        } catch (Exception e) {
            boolean isSend = false;
            if (e instanceof ResponseException) {
                ResponseException responseException = (ResponseException) e;
                if (responseException.getResult().getCode() == Status.TOKEN_EXPIRED.getCode()) {
                    MqMessageMsgModel model = new MqMessageMsgModel(Status.TOKEN_EXPIRED.getMessage(), MsgType.ONE_TO_ONE.getType());
                    model.setBusinessType(MsgBusinessType.LOGOUT.getType());
                    session.send(Flux.create(sink -> sink.next(session.textMessage(MessageUtils.getWebSocketMsg(model))))).then().block();
                    isSend = true;
                }
            }
            if (!isSend) {
                session.send(Flux.create(sink -> sink.next(session.textMessage(MessageUtils.getWebSocketErrorMsg(e.getMessage()))))).then().block();
            }
        }
    }

    /**
     * 删除缓存并断开链接
     *
     * @param systemId ${@link String} 登录系统唯一id
     * @author zxiaozhou
     * @date 2021-01-25 15:33
     */
    private void removeCache(String systemId) {
        WebSocketMsgModel model = senderMsgMap.remove(systemId);
        if (Objects.nonNull(model)) {
            model.getSession().close(CloseStatus.NOT_ACCEPTABLE).then().block();
            WebSocketRedisUtil.deleteCharUser(model.getUserId(), model.getSystemId());
        }
    }


    /**
     * 刷新鉴权缓存以及链接信息
     *
     * @param systemId ${@link String} 系统标识
     * @param token    ${@link String} 新token
     * @author zxiaozhou
     * @date 2021-01-26 09:55
     */
    private void update(String systemId, String token) {
        WebSocketMsgModel model = senderMsgMap.remove(systemId);
        if (Objects.nonNull(model)) {
            model.setToken(token);
            senderMsgMap.put(systemId, model);
            WebSocketRedisUtil.addChartConnect(token);
        }
    }


    /**
     * 消息广播
     *
     * @param message ${@link String} 待发送消息
     * @author zxiaozhou
     * @date 2021-01-13 23:12
     */
    private void sendAllMessage(String message) {
        if (CollectionUtil.isNotEmpty(senderMsgMap)) {
            Set<String> waitDeleteSystemIds = new HashSet<>();
            senderMsgMap.forEach((k, v) -> {
                try {
                    ContextHolderUtils.getUserAndAuthModel(v.getToken());
                    v.sendData(message);
                } catch (Exception e) {
                    boolean isSend = false;
                    if (e instanceof ResponseException) {
                        ResponseException responseException = (ResponseException) e;
                        if (responseException.getResult().getCode() == Status.TOKEN_EXPIRED.getCode()) {
                            waitDeleteSystemIds.add(v.getSystemId());
                            MqMessageMsgModel model = new MqMessageMsgModel(Status.TOKEN_EXPIRED.getMessage(), MsgType.ONE_TO_ONE.getType());
                            model.setBusinessType(MsgBusinessType.LOGOUT.getType());
                            v.sendData(MessageUtils.getWebSocketMsg(model));
                            isSend = true;
                        }
                    }
                    if (!isSend) {
                        v.sendData(MessageUtils.getWebSocketErrorMsg(e.getMessage()));
                    }
                }
            });
            // 移除链接缓存
            if (CollectionUtil.isNotEmpty(waitDeleteSystemIds)) {
                waitDeleteSystemIds.forEach(this::removeCache);
            }
        }
    }


    /**
     * 一对一消息
     *
     * @param userId  ${@link String} 用户id
     * @param message ${@link String} 消息内容
     * @author zxiaozhou
     * @date 2021-01-13 23:13
     */
    private void sendOneMessage(String userId, String message) {
        if (CollectionUtil.isNotEmpty(senderMsgMap)) {
            Set<String> waitDeleteSystemIds = new HashSet<>();
            senderMsgMap.forEach((k, v) -> {
                try {
                    UserAndAuthModel userAndAuthModel = ContextHolderUtils.getUserAndAuthModel(v.getToken());
                    if (userId.equals(userAndAuthModel.getUserId())) {
                        v.sendData(message);
                    }
                } catch (Exception e) {
                    boolean isSend = false;
                    if (e instanceof ResponseException) {
                        ResponseException responseException = (ResponseException) e;
                        if (responseException.getResult().getCode() == Status.TOKEN_EXPIRED.getCode()) {
                            waitDeleteSystemIds.add(v.getSystemId());
                            MqMessageMsgModel model = new MqMessageMsgModel(Status.TOKEN_EXPIRED.getMessage(), MsgType.ONE_TO_ONE.getType());
                            model.setBusinessType(MsgBusinessType.LOGOUT.getType());
                            v.sendData(MessageUtils.getWebSocketMsg(model));
                            isSend = true;
                        }
                    }
                    if (!isSend) {
                        v.sendData(MessageUtils.getWebSocketErrorMsg(e.getMessage()));
                    }
                }
            });
            // 移除链接缓存
            if (CollectionUtil.isNotEmpty(waitDeleteSystemIds)) {
                waitDeleteSystemIds.forEach(this::removeCache);
            }
        }

    }


    /**
     * 接收业务消息
     *
     * @param chartMsg ${@link MqMessageMsgModel} 业务消息
     * @author zxiaozhou
     * @date 2020-09-15 00:10
     */
    @StreamListener(ReceiveMsgProcessor.PROCESS_DATA_MSG)
    public void receiveMsgData(MqMessageMsgModel chartMsg) {
        String sendMsg = MessageUtils.getWebSocketMsg(chartMsg);
        if (chartMsg.getType() == MsgType.BROADCAST.getType()) {
            // 广播
            sendAllMessage(sendMsg);
        } else {
            // 一对一
            Set<String> receiverUserIds = chartMsg.getReceiverUserIds();
            if (CollectionUtil.isNotEmpty(receiverUserIds)) {
                receiverUserIds.forEach(v -> sendOneMessage(v, sendMsg));
            }
        }
    }

}