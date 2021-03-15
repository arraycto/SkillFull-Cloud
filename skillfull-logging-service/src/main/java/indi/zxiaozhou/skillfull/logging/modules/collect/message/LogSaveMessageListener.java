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

import indi.zxiaozhou.skillfull.corecommon.base.model.DataLogModel;
import indi.zxiaozhou.skillfull.corecommon.base.model.OperateLogModel;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.IDataService;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.IOperateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * 路由信息
 *
 * @author zxiaozhou
 * @date 2020-09-13 01:28
 * @since JDK11
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LogSaveMessageListener {
    private final IDataService dataService;
    private final IOperateService operateService;

    /**
     * 接收数据日志
     *
     * @param dataLog ${@link DataLogModel} 数据日志
     * @author zxiaozhou
     * @date 2020-09-15 00:10
     */
    @StreamListener(LogSaveProcessor.PROCESS_DATA_LOG)
    public void receiveDataLog(DataLogModel dataLog) {
        log.debug("------------LogSaveMessageListener------接收数据日志------>receiveDataLog:{}", dataLog);
        dataService.save(dataLog);
    }


    /**
     * 接收操作日志
     *
     * @param operateLog ${@link OperateLogModel} 操作日志
     * @author zxiaozhou
     * @date 2020-09-15 00:10
     */
    @StreamListener(LogSaveProcessor.PROCESS_OPERATE_LOG)
    public void receiveOperateLog(OperateLogModel operateLog) {
        log.debug("------------LogSaveMessageListener------接收操作日志------>receiveOperateLog:{}", operateLog);
        operateService.save(operateLog);
    }
}
