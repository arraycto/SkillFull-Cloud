// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.corewebflux.utils;

import cn.hutool.core.collection.CollectionUtil;
import indi.zxiaozhou.skillfull.corecommon.base.Result;
import indi.zxiaozhou.skillfull.corecommon.base.model.MessageChartModel;
import indi.zxiaozhou.skillfull.corecommon.base.model.MessageMsgModel;
import indi.zxiaozhou.skillfull.corecommon.base.model.MqMessageChartModel;
import indi.zxiaozhou.skillfull.corecommon.base.model.MqMessageMsgModel;
import indi.zxiaozhou.skillfull.corecommon.constant.MsgType;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import indi.zxiaozhou.skillfull.corecommon.utils.CoreCommonUtils;
import indi.zxiaozhou.skillfull.corewebflux.message.MessageSendProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

/**
 * 日志工具类
 *
 * @author zxiaozhou
 * @date 2020-09-15 00:24
 * @since JDK11
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MessageUtils {
    private static MessageUtils utils;
    private final MessageSendProcessor processor;


    @PostConstruct
    private void init() {
        utils = this;
    }


    /**
     * 通知mq广播websocket
     *
     * @param model ${@link MqMessageChartModel} 聊天消息
     * @author zxiaozhou
     * @date 2021-01-25 16:12
     */
    public static void sendChart(MqMessageChartModel model) {
        if (model.getType() != MsgType.BROADCAST.getType() && CollectionUtil.isEmpty(model.getReceiverUserIds())) {
            throw new ResponseException(Status.VERIFICATION_FAILED, "非广播消息时接收人不能为空");
        }
        Message<MqMessageChartModel> stringMessage = MessageBuilder.withPayload(model).build();
        utils.processor.sendChartMessage().send(stringMessage);
    }


    /**
     * 通知mq广播websocket
     *
     * @param model ${@link MqMessageMsgModel} 业务消息
     * @author zxiaozhou
     * @date 2021-01-25 16:12
     */
    public static void sendMsg(MqMessageMsgModel model) {
        if (model.getType() != MsgType.BROADCAST.getType() && CollectionUtil.isEmpty(model.getReceiverUserIds())) {
            throw new ResponseException(Status.VERIFICATION_FAILED, "非广播消息时接收人不能为空");
        }
        Message<MqMessageMsgModel> stringMessage = MessageBuilder.withPayload(model).build();
        utils.processor.sendMsgMessage().send(stringMessage);
    }


    /**
     * 构建websocket发送消息字符串
     *
     * @param errorMsg ${@link String} 待发送异常消息
     * @return String ${@link String}
     * @author zxiaozhou
     * @date 2021-01-25 16:11
     */
    public static String getWebSocketErrorMsg(String errorMsg) {
        return getWebSocketErrorMsg(Status.ERROR, errorMsg);
    }


    /**
     * 构建websocket发送消息字符串
     *
     * @param errorMsg ${@link String} 待发送异常消息
     * @param status   ${@link Status} 消息状态
     * @return String ${@link String}
     * @author zxiaozhou
     * @date 2021-01-25 16:11
     */
    public static String getWebSocketErrorMsg(Status status, String errorMsg) {
        status = Objects.nonNull(status) ? status : Status.ERROR;
        Result<Object> errorMsgInfo = new Result<>(status, StringUtils.isNotBlank(errorMsg) ? errorMsg : status.getMessage());
        return CoreCommonUtils.objectToJsonStr(errorMsgInfo);
    }


    /**
     * 构建websocket发送消息字符串
     *
     * @param status ${@link Status} 消息状态
     * @return String ${@link String}
     * @author zxiaozhou
     * @date 2021-01-25 16:11
     */
    public static String getWebSocketErrorMsg(Status status) {
        return getWebSocketErrorMsg(status, null);
    }


    /**
     * 构建websocket发送消息字符串
     *
     * @param data ${@link MqMessageChartModel} 待发送聊天消息
     * @return String ${@link String}
     * @author zxiaozhou
     * @date 2021-01-25 16:11
     */
    public static String getWebSocketMsg(MqMessageChartModel data) {
        MessageChartModel model = new MessageChartModel(data);
        Result<MessageChartModel> messageChartModelResult = new Result<>(Status.SUCCESS, model);
        return CoreCommonUtils.objectToJsonStr(messageChartModelResult);
    }


    /**
     * 构建websocket发送消息字符串
     *
     * @param data ${@link MqMessageChartModel} 待发送业务消息
     * @return String ${@link String}
     * @author zxiaozhou
     * @date 2021-01-25 16:11
     */
    public static String getWebSocketMsg(MqMessageMsgModel data) {
        MessageMsgModel model = new MessageMsgModel(data);
        Result<MessageMsgModel> messageChartModelResult = new Result<>(Status.SUCCESS, model);
        return CoreCommonUtils.objectToJsonStr(messageChartModelResult);
    }
}
