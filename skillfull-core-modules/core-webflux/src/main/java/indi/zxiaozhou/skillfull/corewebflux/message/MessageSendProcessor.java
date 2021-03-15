// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.corewebflux.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 * 向消息中心发送消息
 *
 * @author zxiaozhou
 * @date 2020-09-13 02:01
 * @since JDK11
 */
@Component
public interface MessageSendProcessor {
    /**
     * chart通道
     */
    String PROCESS_CHART = "process_message_chart_output";

    /**
     * msg通道
     */
    String PROCESS_MSG = "process_message_msg_output";


    /**
     * 发送聊天通道
     *
     * @return SubscribableChannel ${@link SubscribableChannel}
     * @author zxiaozhou
     * @date 2020-09-13 02:06
     */
    @Output(PROCESS_CHART)
    MessageChannel sendChartMessage();


    /**
     * 发送消息通道
     *
     * @return SubscribableChannel ${@link SubscribableChannel}
     * @author zxiaozhou
     * @date 2020-09-13 02:06
     */
    @Output(PROCESS_MSG)
    MessageChannel sendMsgMessage();
}
