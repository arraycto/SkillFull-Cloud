// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.logging.modules.collect.message;

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
public interface LogSaveProcessor {
    /**
     * 上报数据日志日志
     */
    String PROCESS_DATA_LOG = "process_data_log_input";

    /**
     * 上报操作日志
     */
    String PROCESS_OPERATE_LOG = "process_operate_log_input";


    /**
     * 收集数据日志
     *
     * @return SubscribableChannel ${@link SubscribableChannel}
     * @author zxiaozhou
     * @date 2020-09-13 02:06
     */
    @Input(PROCESS_DATA_LOG)
    SubscribableChannel getDataLogMessage();


    /**
     * 收集操作日志
     *
     * @return SubscribableChannel ${@link SubscribableChannel}
     * @author zxiaozhou
     * @date 2020-09-13 02:06
     */
    @Input(PROCESS_OPERATE_LOG)
    SubscribableChannel getOperateLogMessage();
}
