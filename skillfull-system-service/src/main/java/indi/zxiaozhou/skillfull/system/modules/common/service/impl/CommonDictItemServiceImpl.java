// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.common.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.system.modules.common.controller.vo.CommonDictItemPageVo;
import indi.zxiaozhou.skillfull.system.modules.common.controller.vo.CommonDictItemVo;
import indi.zxiaozhou.skillfull.system.modules.common.entity.CommonDictEntity;
import indi.zxiaozhou.skillfull.system.modules.common.entity.CommonDictItemEntity;
import indi.zxiaozhou.skillfull.system.modules.common.mapper.CommonDictItemMapper;
import indi.zxiaozhou.skillfull.system.modules.common.mapper.CommonDictMapper;
import indi.zxiaozhou.skillfull.system.modules.common.service.ICommonDictItemService;
import indi.zxiaozhou.skillfull.system.modules.common.service.dto.CommonDictItemDto;
import indi.zxiaozhou.skillfull.system.modules.common.service.dto.CommonDictItemPageDto;
import indi.zxiaozhou.skillfull.system.modules.common.service.mapstruct.dto.CommonDictItemDtoMap;
import indi.zxiaozhou.skillfull.system.modules.common.service.mapstruct.vo.CommonDictItemVoMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 数据字典配置项表(CommonDictItem)业务层实现
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:25:26
 * @since JDK11
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CommonDictItemServiceImpl extends ServiceImpl<CommonDictItemMapper, CommonDictItemEntity> implements ICommonDictItemService {
    private final CommonDictItemDtoMap dtoMap;
    private final CommonDictItemVoMap voMap;
    private final CommonDictItemMapper mapper;
    private final CommonDictMapper dictMapper;


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void save(CommonDictItemVo vo) throws RuntimeException {
        CommonDictItemEntity entity = voMap.toEntity(vo);
        // 查询当前值是否已经存在
        LambdaQueryWrapper<CommonDictItemEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.or(v -> v.eq(CommonDictItemEntity::getItemText, vo.getItemText()).or()
                .eq(CommonDictItemEntity::getItemValue, vo.getItemValue()))
                .eq(CommonDictItemEntity::getDictId, vo.getDictId());
        List<CommonDictItemEntity> list = this.list(lambdaQueryWrapper);
        if (CollectionUtil.isNotEmpty(list)) {
            throw new ResponseException(Status.VERIFICATION_FAILED, "当前字典项值或字典项名称已经存在,请重新输入");
        }
        // 查询当前字典编码
        CommonDictEntity commonDictEntity = dictMapper.selectById(vo.getDictId());
        if (Objects.isNull(commonDictEntity)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "字典类型不存在或被删除,请返回从新选择再添加");
        }
        entity.setDictCode(commonDictEntity.getDictCode());
        boolean result = super.save(entity);
        if (!result) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "保存数据字典项失败");
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void updateById(String itemId, CommonDictItemVo vo) throws RuntimeException {
        // 查询数据是否存在
        this.getById(itemId);
        // 查询字典项是否已经存在
        LambdaQueryWrapper<CommonDictItemEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.or(v -> v.eq(CommonDictItemEntity::getItemText, vo.getItemText()).or()
                .eq(CommonDictItemEntity::getItemValue, vo.getItemValue()))
                .eq(CommonDictItemEntity::getDictId, vo.getDictId())
                .ne(CommonDictItemEntity::getItemId, itemId);
        List<CommonDictItemEntity> list = this.list(lambdaQueryWrapper);
        if (CollectionUtil.isNotEmpty(list)) {
            throw new ResponseException(Status.VERIFICATION_FAILED, "当前字典项值或字典项名称已经存在,请重新输入");
        }
        // 更新数据
        CommonDictItemEntity entity = voMap.toEntity(vo);
        entity.setItemId(itemId);
        boolean result = super.updateById(entity);
        if (!result) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "更新数据字典项失败");
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public List<CommonDictItemDto> selectListByCode(String dictCode) throws RuntimeException {
        return mapper.selectListByCode(dictCode);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public PageDto<CommonDictItemPageDto> pageByModel(CommonDictItemPageVo vo) throws RuntimeException {
        return new PageDto<>(mapper.pageByModel(vo.getPage(), vo));
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public CommonDictItemDto getById(String itemId) throws RuntimeException {
        CommonDictItemEntity byId = super.getById(itemId);
        if (Objects.isNull(byId)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "为找到符合条件数据");
        }
        return dtoMap.toDto(byId);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void deleteById(String itemId) throws RuntimeException {
        // 查询数据是否存在
        this.getById(itemId);
        boolean b = this.removeById(itemId);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "删除数据字典项失败");
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void deleteBatch(List<String> itemIds) throws RuntimeException {
        List<CommonDictItemEntity> entities = this.listByIds(itemIds);
        if (CollectionUtil.isEmpty(entities)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "数据不存在或已经被别人删除");
        }
        List<String> waitDeleteList = new ArrayList<>();
        entities.forEach(v -> waitDeleteList.add(v.getItemId()));
        boolean b = this.removeByIds(waitDeleteList);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "批量删除数据字典项失败");
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void updateDictItemState(String itemId, Integer type) throws RuntimeException {
        // 查询数据是否存在
        this.getById(itemId);
        CommonDictItemEntity entity = new CommonDictItemEntity();
        entity.setItemId(itemId);
        entity.setItemStatus(type == 1 ? 1 : 0);
        boolean b = this.updateById(entity);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "更新字典项状态失败");
        }
    }
}