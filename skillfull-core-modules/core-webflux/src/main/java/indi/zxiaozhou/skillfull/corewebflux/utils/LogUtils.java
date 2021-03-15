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

import indi.zxiaozhou.skillfull.corecommon.base.model.DataLogModel;
import indi.zxiaozhou.skillfull.corecommon.base.model.OperateLogModel;
import indi.zxiaozhou.skillfull.corewebflux.message.LogSendProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 日志工具类
 *
 * @author zxiaozhou
 * @date 2020-09-15 00:24
 * @since JDK11
 */
@Component
@RequiredArgsConstructor
public class LogUtils {
    private static LogUtils utils;
    private final LogSendProcessor sendProcessor;

    @PostConstruct
    private void init() {
        utils = this;
    }


    /**
     * 发送数据日志
     *
     * @param dataLog ${@link DataLogModel} 数据日志
     * @author zxiaozhou
     * @date 2020-09-15 00:28
     */
    public static void sedDataLog(DataLogModel dataLog) {
        Message<DataLogModel> stringMessage = MessageBuilder.withPayload(dataLog).build();
        utils.sendProcessor.sendDataLogMessage().send(stringMessage);
    }


    /**
     * 发送数据日志
     *
     * @param operateLog ${@link OperateLogModel} 操作日志
     * @author zxiaozhou
     * @date 2020-09-15 00:28
     */
    public static void sedDataLog(OperateLogModel operateLog) {
        Message<OperateLogModel> stringMessage = MessageBuilder.withPayload(operateLog).build();
        utils.sendProcessor.sendOperateLogMessage().send(stringMessage);
    }

}
