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
import indi.zxiaozhou.skillfull.message.modules.manage.controller.vo.ManageSendRecordPageVo;
import indi.zxiaozhou.skillfull.message.modules.manage.controller.vo.ManageSendRecordQueryVo;
import indi.zxiaozhou.skillfull.message.modules.manage.controller.vo.ManageSendRecordVo;
import indi.zxiaozhou.skillfull.message.modules.manage.entity.ManageSendRecordEntity;
import indi.zxiaozhou.skillfull.message.modules.manage.mapper.ManageSendRecordMapper;
import indi.zxiaozhou.skillfull.message.modules.manage.service.IManageSendRecordService;
import indi.zxiaozhou.skillfull.message.modules.manage.service.dto.ManageSendRecordDto;
import indi.zxiaozhou.skillfull.message.modules.manage.service.dto.ManageSendRecordPageDto;
import indi.zxiaozhou.skillfull.message.modules.manage.service.mapstruct.dto.ManageSendRecordDtoMap;
import indi.zxiaozhou.skillfull.message.modules.manage.service.mapstruct.dto.ManageSendRecordPageDtoMap;
import indi.zxiaozhou.skillfull.message.modules.manage.service.mapstruct.vo.ManageSendRecordPageVoMap;
import indi.zxiaozhou.skillfull.message.modules.manage.service.mapstruct.vo.ManageSendRecordQueryVoMap;
import indi.zxiaozhou.skillfull.message.modules.manage.service.mapstruct.vo.ManageSendRecordVoMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 消息发送记录表(ManageSendRecord)业务层实现
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:57:39
 * @since JDK1.8
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ManageSendRecordServiceImpl extends ServiceImpl<ManageSendRecordMapper, ManageSendRecordEntity> implements IManageSendRecordService {
    private final ManageSendRecordDtoMap dtoMap;
    private final ManageSendRecordPageDtoMap pageDtoMap;
    private final ManageSendRecordVoMap voMap;
    private final ManageSendRecordQueryVoMap queryVoMap;
    private final ManageSendRecordPageVoMap pageVoMap;
    private final ManageSendRecordMapper mapper;


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void save(ManageSendRecordVo vo) throws RuntimeException {
        ManageSendRecordEntity entity = voMap.toEntity(vo);
        boolean result = super.save(entity);
        if (!result) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "保存数据失败");
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void updateById(String esId, ManageSendRecordVo vo) throws RuntimeException {
        // 查询数据是否存在
        this.getById(esId);
        // 更新数据
        ManageSendRecordEntity entity = voMap.toEntity(vo);
        entity.setEsId(esId);
        boolean result = super.updateById(entity);
        if (!result) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "更新数据失败");
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public List<ManageSendRecordDto> selectListByModel(ManageSendRecordQueryVo vo) throws RuntimeException {
        List<ManageSendRecordDto> list = mapper.selectListByModel(vo);
        if (CollectionUtils.isEmpty(list)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "未找到符合条件数据");
        }
        return list;
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public PageDto<ManageSendRecordPageDto> pageByModel(ManageSendRecordPageVo vo) throws RuntimeException {
        return new PageDto<>(mapper.pageByModel(vo.getPage(), vo));
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public PageDto<ManageSendRecordPageDto> page(ManageSendRecordPageVo vo) throws RuntimeException {
        LambdaQueryWrapper<ManageSendRecordEntity> queryWrapper = new LambdaQueryWrapper<>(pageVoMap.toEntity(vo));
        if (Objects.nonNull(vo.getStartTime())) {
            queryWrapper.gt(ManageSendRecordEntity::getCreateTime, vo.getStartTime());
        }
        if (Objects.nonNull(vo.getEndTime())) {
            queryWrapper.lt(ManageSendRecordEntity::getCreateTime, vo.getEndTime());
        }
        Page<ManageSendRecordEntity> page = this.page(vo.getPage(), queryWrapper);
        return new PageDto<>(page, pageDtoMap.toDto(page.getRecords()));
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public ManageSendRecordDto getById(String esId) throws RuntimeException {
        ManageSendRecordEntity byId = super.getById(esId);
        if (Objects.isNull(byId)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "为找到符合条件数据");
        }
        return dtoMap.toDto(byId);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void deleteById(String esId) throws RuntimeException {
        // 查询数据是否存在
        this.getById(esId);
        boolean b = this.removeById(esId);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "删除数据失败");
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void deleteBatch(List<String> esIds) throws RuntimeException {
        List<ManageSendRecordEntity> entities = this.listByIds(esIds);
        if (CollectionUtil.isEmpty(entities)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "数据不存在或已经被别人删除");
        }
        List<String> waitDeleteList = new ArrayList<>();
        entities.forEach(v -> waitDeleteList.add(v.getEsId()));
        boolean b = this.removeByIds(waitDeleteList);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "批量删除数据失败");
        }
    }
}