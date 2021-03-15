// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.logging.modules.collect.service.impl;

import indi.zxiaozhou.skillfull.logging.modules.collect.service.ICollectService;
import indi.zxiaozhou.skillfull.logging.modules.manage.controller.vo.DataVo;
import indi.zxiaozhou.skillfull.logging.modules.manage.controller.vo.OperateVo;
import indi.zxiaozhou.skillfull.logging.modules.manage.entity.DataEntity;
import indi.zxiaozhou.skillfull.logging.modules.manage.entity.OperateEntity;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.IDataService;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.IOperateService;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.mapstruct.vo.DataVoMap;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.mapstruct.vo.OperateVoMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据收集服务层接口实现
 *
 * @author zxiaozhou
 * @date 2021-01-12 15:51
 * @since JDK11
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CollectServiceImpl implements ICollectService {
    private final IOperateService operateService;
    private final DataVoMap dataVoMap;
    private final OperateVoMap operateVoMap;
    private final IDataService dataService;


    @Override
    @Async
    public void dataCollectBatch(List<DataVo> vos) throws RuntimeException {
        List<DataEntity> dataEntities = dataVoMap.toEntity(vos);
        boolean b = dataService.saveBatch(dataEntities);
        if (!b) {
            log.error("------------CollectServiceImpl------数据日志入库失败------>dataCollectBatch--->\n参数:{}", vos);
        }
    }


    @Override
    @Async
    public void operateCollectBatch(List<OperateVo> vos) throws RuntimeException {
        List<OperateEntity> operateEntities = operateVoMap.toEntity(vos);
        boolean b = operateService.saveBatch(operateEntities);
        if (!b) {
            log.error("------------CollectServiceImpl------操作日志入库失败------>operateCollectBatch--->\n参数:{}", vos);
        }
    }
}
