// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.logging.modules.manage.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.zxiaozhou.skillfull.corecommon.base.model.OperateLogModel;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.logging.modules.manage.controller.vo.OperatePageVo;
import indi.zxiaozhou.skillfull.logging.modules.manage.entity.OperateEntity;
import indi.zxiaozhou.skillfull.logging.modules.manage.mapper.OperateMapper;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.IOperateService;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.dto.OperateDto;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.dto.OperatePageDto;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.mapstruct.dto.OperateDtoMap;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.mapstruct.vo.OperateModelMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 操作日志(Operate)业务层实现
 *
 * @author zxiaozhou
 * @date 2021-01-12 14:40:38
 * @since JDK11
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OperateServiceImpl extends ServiceImpl<OperateMapper, OperateEntity> implements IOperateService {
    private final OperateDtoMap dtoMap;
    private final OperateMapper mapper;
    private final OperateModelMap modelMap;

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void save(OperateLogModel model) throws RuntimeException {
        OperateEntity operateEntity = modelMap.toEntity(model);
        operateEntity.setCreateUserId(model.getUserId());
        operateEntity.setCreateUserName(model.getUserName());
        this.save(operateEntity);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public PageDto<OperatePageDto> pageByModel(OperatePageVo vo) throws RuntimeException {
        return new PageDto<>(mapper.pageByModel(vo.getPage(), vo));
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public OperateDto getById(String sysLogId) throws RuntimeException {
        OperateEntity byId = super.getById(sysLogId);
        if (Objects.isNull(byId)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "为找到符合条件数据");
        }
        return dtoMap.toDto(byId);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void deleteById(String sysLogId) throws RuntimeException {
        // 查询数据是否存在
        this.getById(sysLogId);
        boolean b = this.removeById(sysLogId);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "删除数据失败");
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void deleteBatch(List<String> sysLogIds) throws RuntimeException {
        List<OperateEntity> entities = this.listByIds(sysLogIds);
        if (CollectionUtil.isEmpty(entities)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "数据不存在或已经被别人删除");
        }
        List<String> waitDeleteList = new ArrayList<>();
        entities.forEach(v -> waitDeleteList.add(v.getSysLogId()));
        boolean b = this.removeByIds(waitDeleteList);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "批量删除数据失败");
        }
    }
}