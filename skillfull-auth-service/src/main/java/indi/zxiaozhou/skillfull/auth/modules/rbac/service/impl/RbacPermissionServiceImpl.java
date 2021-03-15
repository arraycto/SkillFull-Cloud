// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.rbac.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.zxiaozhou.skillfull.auth.core.constant.PermissionType;
import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacPermissionPageVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacPermissionVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.entity.RbacPermissionEntity;
import indi.zxiaozhou.skillfull.auth.modules.rbac.mapper.RbacPermissionMapper;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.IRbacPermissionService;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacPermissionDto;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacPermissionPageDto;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacPermissionTreeDto;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.mapstruct.dto.RbacPermissionDtoMap;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.mapstruct.dto.RbacPermissionPageDtoMap;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.mapstruct.vo.PermissionVoMap;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import indi.zxiaozhou.skillfull.corecommon.utils.CodeUtil;
import indi.zxiaozhou.skillfull.corecommon.utils.ConvertUtil;
import indi.zxiaozhou.skillfull.corecommon.utils.tree.TreeToolUtils;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 权限表(Permission)业务层实现
 *
 * @author zxiaozhou
 * @date 2020-09-26 17:16:15
 * @since JDK11
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class, Error.class})
public class RbacPermissionServiceImpl extends ServiceImpl<RbacPermissionMapper, RbacPermissionEntity> implements IRbacPermissionService {
    private final RbacPermissionDtoMap dtoMap;
    private final RbacPermissionPageDtoMap pageDtoMap;
    private final PermissionVoMap voMap;
    private final RbacPermissionMapper mapper;


    @Override
    public void save(RbacPermissionVo vo) throws RuntimeException {
        RbacPermissionEntity entity = voMap.toEntity(vo);
        // 数据校验
        checkData(entity);
        entity.setPermissionSysCode(generateCode(null, vo.getParentId(), false));
        boolean result = super.save(entity);
        if (!result) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "保存失败");
        }
    }

    /**
     * 入库前数据校验
     *
     * @param entity ${@link RbacPermissionEntity} 待交易数据
     * @author zxiaozhou
     * @date 2020-10-06 23:45
     */
    private void checkData(RbacPermissionEntity entity) {
        if (entity.getPermissionType() == PermissionType.SUB_MENU.getType() || entity.getPermissionType() == PermissionType.PARENT_MENU.getType()) {
            // 如果是目录或菜单:缓存,隐藏,隐藏子菜单,基础组件(组件路径),路由地址
            if (Objects.isNull(entity.getKeepAlive())) {
                throw new ResponseException(Status.VERIFICATION_FAILED, "请选择是否缓存");
            }
            if (Objects.isNull(entity.getHidden())) {
                throw new ResponseException(Status.VERIFICATION_FAILED, "请选择是否隐藏");
            }
            if (StringUtils.isBlank(entity.getComponent())) {
                throw new ResponseException(Status.VERIFICATION_FAILED, "基础组件或组件路径不能为空");
            }
            if (StringUtils.isBlank(entity.getPathName())) {
                throw new ResponseException(Status.VERIFICATION_FAILED, "路由名称不能为空");
            }
            // 菜单:外部链接,上级
            if (entity.getPermissionType() == PermissionType.SUB_MENU.getType()) {
                if (Objects.isNull(entity.getIsExternal())) {
                    throw new ResponseException(Status.VERIFICATION_FAILED, "是否外链接不能为空");
                }
                if (StringUtils.isBlank(entity.getParentId())) {
                    throw new ResponseException(Status.VERIFICATION_FAILED, "上级不能为空");
                }
            }
        } else {
            // 如果是按钮:上级,授权策略,校验后端不能为空
            if (StringUtils.isBlank(entity.getParentId())) {
                throw new ResponseException(Status.VERIFICATION_FAILED, "上级不能为空");
            }
            if (Objects.isNull(entity.getButtonStrategy())) {
                throw new ResponseException(Status.VERIFICATION_FAILED, "授权策略不能为空");
            }
            if (entity.getButtonStrategy() != 1 && entity.getButtonStrategy() != 2) {
                throw new ResponseException(Status.VERIFICATION_FAILED, "授权策略只能为1、2");
            }
            if (Objects.isNull(entity.getCheckActionRequest())) {
                throw new ResponseException(Status.VERIFICATION_FAILED, "是否校验后端不能为空");
            }
        }
    }


    /**
     * 生成系统code
     *
     * @param oldParentId ${@link String} 历史上级id
     * @param newParentId ${@link String} 现在上级id
     * @param isUpdate    ${@link Boolean} false-新建,true-更新
     * @return String ${@link String}
     * @author zxiaozhou
     * @date 2021-03-08 12:00
     */
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    synchronized String generateCode(String oldParentId, String newParentId, boolean isUpdate) {
        oldParentId = StringUtils.isBlank(oldParentId) ? "" : oldParentId;
        newParentId = StringUtils.isBlank(newParentId) ? "" : newParentId;
        if (isUpdate && oldParentId.equals(newParentId)) {
            return null;
        }
        String code;
        if (StringUtils.isBlank(newParentId)) {
            LambdaQueryWrapper<RbacPermissionEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.and(v -> v.isNull(RbacPermissionEntity::getParentId).or().eq(RbacPermissionEntity::getParentId, ""))
                    .orderByDesc(RbacPermissionEntity::getPermissionSysCode)
                    .last("LIMIT 1");
            RbacPermissionEntity one = this.getOne(lambdaQueryWrapper);
            code = CodeUtil.getSubYouBianCode(null, Objects.isNull(one) ? null : one.getPermissionSysCode());
        } else {
            // 获取上级code
            RbacPermissionDto byId = this.getById(newParentId);
            // 获取本级最大code
            LambdaQueryWrapper<RbacPermissionEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.like(RbacPermissionEntity::getPermissionSysCode, byId.getPermissionSysCode() + "____")
                    .orderByDesc(RbacPermissionEntity::getPermissionSysCode)
                    .last("LIMIT 1");
            RbacPermissionEntity one = this.getOne(lambdaQueryWrapper);
            code = CodeUtil.getSubYouBianCode(byId.getPermissionSysCode(), Objects.isNull(one) ? null : one.getPermissionSysCode());
        }
        return code;
    }


    @Override
    public void updateById(String permissionId, RbacPermissionVo vo) throws RuntimeException {
        if (StringUtils.isBlank(vo.getParentId())) {
            vo.setParentId("");
        }
        // 查询数据是否存在
        RbacPermissionDto byId = this.getById(permissionId);
        RbacPermissionEntity entity = voMap.toEntity(vo);
        entity.setPermissionId(permissionId);
        entity.setPermissionSysCode(generateCode(byId.getParentId(), vo.getParentId(), true));
        // 数据校验
        checkData(entity);
        // 更新数据
        boolean result = super.updateById(entity);
        if (!result) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "更新失败");
        }
        if (StringUtils.isNotBlank(entity.getPermissionSysCode()) && !byId.getPermissionSysCode().equals(entity.getPermissionSysCode())) {
            // 修改下级系统编码
            LambdaQueryWrapper<RbacPermissionEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.likeRight(RbacPermissionEntity::getPermissionSysCode, byId.getPermissionSysCode());
            List<RbacPermissionEntity> list = this.list(lambdaQueryWrapper);
            if (CollectionUtil.isNotEmpty(list)) {
                List<RbacPermissionEntity> waitUpdateList = new ArrayList<>();
                list.forEach(v -> {
                    RbacPermissionEntity newEntity = new RbacPermissionEntity();
                    newEntity.setPermissionId(v.getPermissionId());
                    newEntity.setPermissionSysCode(v.getPermissionSysCode().replaceFirst(byId.getPermissionSysCode(), entity.getPermissionSysCode()));
                    waitUpdateList.add(newEntity);
                });
                boolean b = this.updateBatchById(waitUpdateList);
                if (!b) {
                    throw new ResponseException(Status.DATABASE_BASE_ERROR, "更新下级失败");
                }
            }
        }
    }


    @Override
    public PageDto<RbacPermissionPageDto> pageByModel(RbacPermissionPageVo vo) throws RuntimeException {
        // 分页查询
        Set<String> ascs = vo.getAscs();
        if (CollectionUtil.isEmpty(ascs)) {
            ascs = new HashSet<>();
        }
        ascs.add("sortNo");
        vo.setAscs(ascs);
        IPage<RbacPermissionPageDto> pageInfo = mapper.pageByModel(vo.getPage(), vo);
        // 处理是否有子节点
        List<RbacPermissionPageDto> records = pageInfo.getRecords();
        checkHasChildren(records);
        return new PageDto<>(pageInfo, records);
    }


    /**
     * 判断是否有子节点
     *
     * @param pageDtoList ${@link List<RbacPermissionPageDto>} 待判断数据
     * @author zxiaozhou
     * @date 2020-10-05 20:22
     */
    private void checkHasChildren(List<RbacPermissionPageDto> pageDtoList) {
        if (CollectionUtil.isNotEmpty(pageDtoList)) {
            Set<String> parentIds = new HashSet<>();
            pageDtoList.forEach(v -> parentIds.add(v.getPermissionId()));
            LambdaQueryWrapper<RbacPermissionEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.in(RbacPermissionEntity::getParentId, parentIds);
            List<RbacPermissionEntity> list = this.list(lambdaQueryWrapper);
            if (CollectionUtil.isNotEmpty(list)) {
                for (RbacPermissionPageDto dto : pageDtoList) {
                    for (RbacPermissionEntity entity : list) {
                        if (dto.getPermissionId().equals(entity.getParentId())) {
                            dto.setHasChildren(true);
                            break;
                        }
                    }
                }
            }
        }
    }


    @Override
    public List<RbacPermissionPageDto> getChildren(String parentId) throws RuntimeException {
        LambdaQueryWrapper<RbacPermissionEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RbacPermissionEntity::getParentId, parentId)
                .orderByAsc(RbacPermissionEntity::getSortNo);
        List<RbacPermissionEntity> list = this.list(lambdaQueryWrapper);
        List<RbacPermissionPageDto> pageDtoList = pageDtoMap.toDto(list);
        checkHasChildren(pageDtoList);
        return pageDtoList;
    }


    @Override
    public RbacPermissionDto getById(String permissionId) throws RuntimeException {
        RbacPermissionEntity byId = super.getById(permissionId);
        if (Objects.isNull(byId)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "未查询到信息");
        }
        return dtoMap.toDto(byId);
    }


    @Override
    public void updatePermissionState(String permissionId, Integer type) throws RuntimeException {
        this.getById(permissionId);
        RbacPermissionEntity entity = new RbacPermissionEntity();
        entity.setPermissionId(permissionId);
        entity.setPermissionStatus(type);
        boolean b = this.updateById(entity);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "更新数据失败");
        }
    }


    @Override
    public void deleteById(String permissionId) throws RuntimeException {
        // 查询是否有下级
        LambdaQueryWrapper<RbacPermissionEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RbacPermissionEntity::getParentId, permissionId);
        List<RbacPermissionEntity> list = this.list(lambdaQueryWrapper);
        if (CollectionUtil.isNotEmpty(list)) {
            throw new ResponseException(Status.VERIFICATION_FAILED, "当前菜单存在下级,请先删除下级");
        }
        // 查询数据是否存在
        RbacPermissionDto byId = this.getById(permissionId);
        if (byId.getEnableDelete() == 0) {
            throw new ResponseException(Status.VERIFICATION_FAILED, "当前菜单不可删除");
        }
        boolean b = this.removeById(permissionId);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "删除菜单失败");
        }
    }


    @Override
    public List<RbacPermissionTreeDto> getPermissionTree(Integer type, Integer status) {
        LambdaQueryWrapper<RbacPermissionEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.le(RbacPermissionEntity::getPermissionType, type)
                .orderByAsc(RbacPermissionEntity::getSortNo);
        if (status == 1) {
            lambdaQueryWrapper.le(RbacPermissionEntity::getPermissionStatus, 1);
        }
        List<RbacPermissionEntity> list = this.list(lambdaQueryWrapper);
        if (CollectionUtil.isNotEmpty(list)) {
            List<RbacPermissionTreeDto> rootList = new ArrayList<>();
            List<RbacPermissionTreeDto> subList = new ArrayList<>();
            list.forEach(v -> {
                RbacPermissionTreeDto dto = ConvertUtil.map(v, RbacPermissionTreeDto.class);
                if (StringUtils.isBlank(v.getParentId())) {
                    rootList.add(dto);
                } else {
                    subList.add(dto);
                }
            });
            TreeToolUtils<RbacPermissionTreeDto> toolUtils = new TreeToolUtils<>(rootList, subList, new TreeToolUtils.TreeId<>() {
                @Override
                public String getId(RbacPermissionTreeDto permissionTreeDto) {
                    return permissionTreeDto.getPermissionId();
                }

                @Override
                public String getParentId(RbacPermissionTreeDto permissionTreeDto) {
                    return permissionTreeDto.getParentId();
                }
            });
            return toolUtils.getTree();
        }
        return Collections.emptyList();
    }
}