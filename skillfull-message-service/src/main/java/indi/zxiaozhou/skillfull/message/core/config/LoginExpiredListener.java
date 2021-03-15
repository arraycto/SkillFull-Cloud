// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.core.config;

import indi.zxiaozhou.skillfull.corecommon.base.model.MqMessageMsgModel;
import indi.zxiaozhou.skillfull.corecommon.constant.MsgBusinessType;
import indi.zxiaozhou.skillfull.corecommon.constant.MsgType;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import indi.zxiaozhou.skillfull.corecommon.constant.SysBaseConstant;
import indi.zxiaozhou.skillfull.corewebflux.utils.MessageUtils;
import indi.zxiaozhou.skillfull.message.core.config.model.WebSocketChartModel;
import indi.zxiaozhou.skillfull.message.core.config.model.WebSocketMsgModel;
import indi.zxiaozhou.skillfull.message.utils.WebSocketRedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.web.reactive.socket.CloseStatus;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * redis登录信息过期监听
 *
 * @author zxiaozhou
 * @date 2021-01-25 21:22
 * @since JDK11
 */
public class LoginExpiredListener extends KeyExpirationEventMessageListener {
    private final ConcurrentHashMap<String, WebSocketChartModel> senderChartMap;
    private final ConcurrentHashMap<String, WebSocketMsgModel> senderMsgMap;

    public LoginExpiredListener(RedisMessageListenerContainer listenerContainer,
                                ConcurrentHashMap<String, WebSocketChartModel> senderChartMap,
                                ConcurrentHashMap<String, WebSocketMsgModel> senderMsgMap) {
        super(listenerContainer);
        this.senderChartMap = senderChartMap;
        this.senderMsgMap = senderMsgMap;
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 登录过期key校验
        String key = new String(message.getBody(), StandardCharsets.UTF_8);
        if (StringUtils.isNotBlank(key) && key.startsWith(SysBaseConstant.USER_LOGIN_PREFIX)) {
            String[] s = key.split("_");
            String systemId = s[s.length - 1];
            // 用户登录踢出
            WebSocketChartModel chartModel = senderChartMap.remove(systemId);
            if (Objects.nonNull(chartModel)) {
                chartModel.sendData(MessageUtils.getWebSocketErrorMsg(Status.TOKEN_EXPIRED));
                chartModel.getSession().close(CloseStatus.NOT_ACCEPTABLE).then().block();
                WebSocketRedisUtil.deleteCharUser(chartModel.getUserId(), chartModel.getSystemId());
            }
            WebSocketMsgModel msgModel = senderMsgMap.remove(systemId);
            if (Objects.nonNull(msgModel)) {
                MqMessageMsgModel model = new MqMessageMsgModel(Status.TOKEN_EXPIRED.getMessage(), MsgType.ONE_TO_ONE.getType());
                model.setBusinessType(MsgBusinessType.LOGOUT.getType());
                msgModel.sendData(MessageUtils.getWebSocketMsg(model));
                msgModel.getSession().close(CloseStatus.NOT_ACCEPTABLE).then().block();
                WebSocketRedisUtil.deleteMsgUser(msgModel.getUserId(), msgModel.getSystemId());
            }
        }
    }
}
