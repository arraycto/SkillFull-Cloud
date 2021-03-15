// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.coremvc.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
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
public interface LogSendProcessor {
    /**
     * 上报数据日志日志
     */
    String PROCESS_DATA_LOG = "process_data_log_output";

    /**
     * 上报操作日志
     */
    String PROCESS_OPERATE_LOG = "process_operate_log_output";


    /**
     * 收集日志
     *
     * @return SubscribableChannel ${@link SubscribableChannel}
     * @author zxiaozhou
     * @date 2020-09-13 02:06
     */
    @Output(PROCESS_DATA_LOG)
    MessageChannel sendDataLogMessage();


    /**
     * 收集日志
     *
     * @return SubscribableChannel ${@link SubscribableChannel}
     * @author zxiaozhou
     * @date 2020-09-13 02:06
     */
    @Output(PROCESS_OPERATE_LOG)
    MessageChannel sendOperateLogMessage();
}
