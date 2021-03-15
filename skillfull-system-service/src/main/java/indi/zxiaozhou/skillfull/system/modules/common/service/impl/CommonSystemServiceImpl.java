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
import indi.zxiaozhou.skillfull.system.modules.common.controller.vo.CommonSystemPageVo;
import indi.zxiaozhou.skillfull.system.modules.common.controller.vo.CommonSystemVo;
import indi.zxiaozhou.skillfull.system.modules.common.entity.CommonSystemEntity;
import indi.zxiaozhou.skillfull.system.modules.common.mapper.CommonSystemMapper;
import indi.zxiaozhou.skillfull.system.modules.common.service.ICommonSystemService;
import indi.zxiaozhou.skillfull.system.modules.common.service.dto.CommonSystemDto;
import indi.zxiaozhou.skillfull.system.modules.common.service.dto.CommonSystemPageDto;
import indi.zxiaozhou.skillfull.system.modules.common.service.mapstruct.dto.CommonSystemDtoMap;
import indi.zxiaozhou.skillfull.system.modules.common.service.mapstruct.vo.CommonSystemVoMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 系统表(CommonSystem)业务层实现
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:25:34
 * @since JDK11
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CommonSystemServiceImpl extends ServiceImpl<CommonSystemMapper, CommonSystemEntity> implements ICommonSystemService {
    private final CommonSystemDtoMap dtoMap;
    private final CommonSystemVoMap voMap;
    private final CommonSystemMapper mapper;


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void save(CommonSystemVo vo) throws RuntimeException {
        CommonSystemEntity entity = voMap.toEntity(vo);
        boolean result = super.save(entity);
        if (!result) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "保存数据失败");
        }
    }

    /**
     * 数据校验
     *
     * @param entity ${@link CommonSystemEntity} 待校验数据
     * @author zxiaozhou
     * @date 2021-01-08 10:00
     */
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    void checkData(CommonSystemEntity entity) {
        LambdaQueryWrapper<CommonSystemEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CommonSystemEntity::getSystemCode, entity.getSystemCode());
        if (StringUtils.isNotBlank(entity.getSystemId())) {
            lambdaQueryWrapper.ne(CommonSystemEntity::getSystemId, entity.getSystemId());
        }
        CommonSystemEntity one = this.getOne(lambdaQueryWrapper);
        if (Objects.nonNull(one)) {
            throw new ResponseException(Status.VERIFICATION_FAILED, "当前系统编码已经存在:" + one.getSystemCode());
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void updateById(String systemId, CommonSystemVo vo) throws RuntimeException {
        // 查询数据是否存在
        this.getById(systemId);
        // 更新数据
        CommonSystemEntity entity = voMap.toEntity(vo);
        entity.setSystemId(systemId);
        boolean result = super.updateById(entity);
        if (!result) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "更新数据失败");
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public List<CommonSystemDto> selectListAll() throws RuntimeException {
        List<CommonSystemEntity> list = this.list();
        if (CollectionUtil.isNotEmpty(list)) {
            return dtoMap.toDto(list);
        }
        return Collections.emptyList();
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public PageDto<CommonSystemPageDto> pageByModel(CommonSystemPageVo vo) throws RuntimeException {
        return new PageDto<>(mapper.pageByModel(vo.getPage(), vo));
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public CommonSystemDto getById(String systemId) throws RuntimeException {
        CommonSystemEntity byId = super.getById(systemId);
        if (Objects.isNull(byId)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "为找到符合条件数据");
        }
        return dtoMap.toDto(byId);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void deleteById(String systemId) throws RuntimeException {
        // 查询数据是否存在
        this.getById(systemId);
        boolean b = this.removeById(systemId);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "删除数据失败");
        }
    }
}