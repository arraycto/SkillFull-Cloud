// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.core.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 * 自动以processor
 *
 * @author zxiaozhou
 * @date 2020-09-13 02:01
 * @since JDK11
 */
@Component
public interface ReceiveMsgProcessor {
    /**
     * 接收聊天消息
     */
    String PROCESS_DATA_CHART = "process_message_chart_input";

    /**
     * 接收业务消息
     */
    String PROCESS_DATA_MSG = "process_message_msg_input";


    /**
     * 接收聊天消息
     *
     * @return SubscribableChannel ${@link SubscribableChannel}
     * @author zxiaozhou
     * @date 2020-09-13 02:06
     */
    @Input(PROCESS_DATA_CHART)
    SubscribableChannel getDataChartMessage();


    /**
     * 接收业务消息
     *
     * @return SubscribableChannel ${@link SubscribableChannel}
     * @author zxiaozhou
     * @date 2020-09-13 02:06
     */
    @Input(PROCESS_DATA_MSG)
    SubscribableChannel getDataMsgMessage();
}
