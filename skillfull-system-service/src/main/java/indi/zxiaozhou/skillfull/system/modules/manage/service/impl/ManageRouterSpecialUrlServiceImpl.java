// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.manage.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageRouterSpecialUrlPageVo;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageRouterSpecialUrlQueryVo;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageRouterSpecialUrlVo;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageRouterSpecialUrlEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.mapper.ManageRouterSpecialUrlMapper;
import indi.zxiaozhou.skillfull.system.modules.manage.service.IManageRouterSpecialUrlService;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageRouterSpecialUrlDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageRouterSpecialUrlPageDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.mapstruct.dto.ManageRouterSpecialUrlDtoMap;
import indi.zxiaozhou.skillfull.system.modules.manage.service.mapstruct.dto.ManageRouterSpecialUrlPageDtoMap;
import indi.zxiaozhou.skillfull.system.modules.manage.service.mapstruct.vo.ManageRouterSpecialUrlPageVoMap;
import indi.zxiaozhou.skillfull.system.modules.manage.service.mapstruct.vo.ManageRouterSpecialUrlVoMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 路由特殊地址(ManageRouterSpecialUrl)业务层实现
 *
 * @author zxiaozhou
 * @date 2020-12-31 22:50:51
 * @since JDK1.8
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ManageRouterSpecialUrlServiceImpl extends ServiceImpl<ManageRouterSpecialUrlMapper, ManageRouterSpecialUrlEntity> implements IManageRouterSpecialUrlService {
    private final ManageRouterSpecialUrlDtoMap dtoMap;
    private final ManageRouterSpecialUrlPageDtoMap pageDtoMap;
    private final ManageRouterSpecialUrlVoMap voMap;
    private final ManageRouterSpecialUrlPageVoMap pageVoMap;
    private final ManageRouterSpecialUrlMapper mapper;


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void save(ManageRouterSpecialUrlVo vo) throws RuntimeException {
        ManageRouterSpecialUrlEntity entity = voMap.toEntity(vo);
        boolean result = super.save(entity);
        if (!result) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "保存数据失败");
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void updateById(String routeSpecialId, ManageRouterSpecialUrlVo vo) throws RuntimeException {
        // 查询数据是否存在
        this.getById(routeSpecialId);
        // 更新数据
        ManageRouterSpecialUrlEntity entity = voMap.toEntity(vo);
        entity.setRouteSpecialId(routeSpecialId);
        boolean result = super.updateById(entity);
        if (!result) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "更新数据失败");
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public List<ManageRouterSpecialUrlDto> selectListByModel(ManageRouterSpecialUrlQueryVo vo) throws RuntimeException {
        List<ManageRouterSpecialUrlDto> list = mapper.selectListByModel(vo);
        if (CollectionUtils.isEmpty(list)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "未找到符合条件数据");
        }
        return list;
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public PageDto<ManageRouterSpecialUrlPageDto> pageByModel(ManageRouterSpecialUrlPageVo vo) throws RuntimeException {
        return new PageDto<>(mapper.pageByModel(vo.getPage(), vo));
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public PageDto<ManageRouterSpecialUrlPageDto> page(ManageRouterSpecialUrlPageVo vo) throws RuntimeException {
        LambdaQueryWrapper<ManageRouterSpecialUrlEntity> queryWrapper = new LambdaQueryWrapper<>(pageVoMap.toEntity(vo));
        if (Objects.nonNull(vo.getStartTime())) {
            queryWrapper.gt(ManageRouterSpecialUrlEntity::getCreateTime, vo.getStartTime());
        }
        if (Objects.nonNull(vo.getEndTime())) {
            queryWrapper.lt(ManageRouterSpecialUrlEntity::getCreateTime, vo.getEndTime());
        }
        Page<ManageRouterSpecialUrlEntity> page = this.page(vo.getPage(), queryWrapper);
        return new PageDto<>(page, pageDtoMap.toDto(page.getRecords()));
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public ManageRouterSpecialUrlDto getById(String routeSpecialId) throws RuntimeException {
        ManageRouterSpecialUrlEntity byId = super.getById(routeSpecialId);
        if (Objects.isNull(byId)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "为找到符合条件数据");
        }
        return dtoMap.toDto(byId);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void deleteById(String routeSpecialId) throws RuntimeException {
        // 查询数据是否存在
        this.getById(routeSpecialId);
        boolean b = this.removeById(routeSpecialId);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "删除数据失败");
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void deleteBatch(List<String> routeSpecialIds) throws RuntimeException {
        List<ManageRouterSpecialUrlEntity> entities = this.listByIds(routeSpecialIds);
        if (CollectionUtil.isEmpty(entities)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "数据不存在或已经被别人删除");
        }
        List<String> waitDeleteList = new ArrayList<>();
        entities.forEach(v -> waitDeleteList.add(v.getRouteSpecialId()));
        boolean b = this.removeByIds(waitDeleteList);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "批量删除数据失败");
        }
    }
}