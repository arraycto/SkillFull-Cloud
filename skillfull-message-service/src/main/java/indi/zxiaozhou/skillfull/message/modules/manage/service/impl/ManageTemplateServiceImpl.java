// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.modules.manage.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import indi.zxiaozhou.skillfull.message.core.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.message.modules.manage.controller.vo.ManageTemplatePageVo;
import indi.zxiaozhou.skillfull.message.modules.manage.controller.vo.ManageTemplateQueryVo;
import indi.zxiaozhou.skillfull.message.modules.manage.controller.vo.ManageTemplateVo;
import indi.zxiaozhou.skillfull.message.modules.manage.entity.ManageTemplateEntity;
import indi.zxiaozhou.skillfull.message.modules.manage.mapper.ManageTemplateMapper;
import indi.zxiaozhou.skillfull.message.modules.manage.service.IManageTemplateService;
import indi.zxiaozhou.skillfull.message.modules.manage.service.dto.ManageTemplateDto;
import indi.zxiaozhou.skillfull.message.modules.manage.service.dto.ManageTemplatePageDto;
import indi.zxiaozhou.skillfull.message.modules.manage.service.mapstruct.dto.ManageTemplateDtoMap;
import indi.zxiaozhou.skillfull.message.modules.manage.service.mapstruct.dto.ManageTemplatePageDtoMap;
import indi.zxiaozhou.skillfull.message.modules.manage.service.mapstruct.vo.ManageTemplatePageVoMap;
import indi.zxiaozhou.skillfull.message.modules.manage.service.mapstruct.vo.ManageTemplateQueryVoMap;
import indi.zxiaozhou.skillfull.message.modules.manage.service.mapstruct.vo.ManageTemplateVoMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 消息模板(ManageTemplate)业务层实现
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:58:15
 * @since JDK1.8
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ManageTemplateServiceImpl extends ServiceImpl<ManageTemplateMapper, ManageTemplateEntity> implements IManageTemplateService {
    private final ManageTemplateDtoMap dtoMap;
    private final ManageTemplatePageDtoMap pageDtoMap;
    private final ManageTemplateVoMap voMap;
    private final ManageTemplateQueryVoMap queryVoMap;
    private final ManageTemplatePageVoMap pageVoMap;
    private final ManageTemplateMapper mapper;


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void save(ManageTemplateVo vo) throws RuntimeException {
        ManageTemplateEntity entity = voMap.toEntity(vo);
        boolean result = super.save(entity);
        if (!result) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "保存数据失败");
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void updateById(String templateId, ManageTemplateVo vo) throws RuntimeException {
        // 查询数据是否存在
        this.getById(templateId);
        // 更新数据
        ManageTemplateEntity entity = voMap.toEntity(vo);
        entity.setTemplateId(templateId);
        boolean result = super.updateById(entity);
        if (!result) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "更新数据失败");
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public List<ManageTemplateDto> selectListByModel(ManageTemplateQueryVo vo) throws RuntimeException {
        List<ManageTemplateDto> list = mapper.selectListByModel(vo);
        if (CollectionUtils.isEmpty(list)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "未找到符合条件数据");
        }
        return list;
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public PageDto<ManageTemplatePageDto> pageByModel(ManageTemplatePageVo vo) throws RuntimeException {
        return new PageDto<>(mapper.pageByModel(vo.getPage(), vo));
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public PageDto<ManageTemplatePageDto> page(ManageTemplatePageVo vo) throws RuntimeException {
        LambdaQueryWrapper<ManageTemplateEntity> queryWrapper = new LambdaQueryWrapper<>(pageVoMap.toEntity(vo));
        if (Objects.nonNull(vo.getStartTime())) {
            queryWrapper.gt(ManageTemplateEntity::getCreateTime, vo.getStartTime());
        }
        if (Objects.nonNull(vo.getEndTime())) {
            queryWrapper.lt(ManageTemplateEntity::getCreateTime, vo.getEndTime());
        }
        Page<ManageTemplateEntity> page = this.page(vo.getPage(), queryWrapper);
        return new PageDto<>(page, pageDtoMap.toDto(page.getRecords()));
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public ManageTemplateDto getById(String templateId) throws RuntimeException {
        ManageTemplateEntity byId = super.getById(templateId);
        if (Objects.isNull(byId)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "为找到符合条件数据");
        }
        return dtoMap.toDto(byId);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void deleteById(String templateId) throws RuntimeException {
        // 查询数据是否存在
        this.getById(templateId);
        boolean b = this.removeById(templateId);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "删除数据失败");
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void deleteBatch(List<String> templateIds) throws RuntimeException {
        List<ManageTemplateEntity> entities = this.listByIds(templateIds);
        if (CollectionUtil.isEmpty(entities)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "数据不存在或已经被别人删除");
        }
        List<String> waitDeleteList = new ArrayList<>();
        entities.forEach(v -> waitDeleteList.add(v.getTemplateId()));
        boolean b = this.removeByIds(waitDeleteList);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "批量删除数据失败");
        }
    }
}