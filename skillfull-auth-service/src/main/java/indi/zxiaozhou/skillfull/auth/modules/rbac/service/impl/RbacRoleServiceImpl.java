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
import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacRolePageVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacRolePermissionQueryVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacRolePermissionVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacRoleVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.entity.RbacPermissionEntity;
import indi.zxiaozhou.skillfull.auth.modules.rbac.entity.RbacRoleEntity;
import indi.zxiaozhou.skillfull.auth.modules.rbac.mapper.RbacPermissionMapper;
import indi.zxiaozhou.skillfull.auth.modules.rbac.mapper.RbacRoleMapper;
import indi.zxiaozhou.skillfull.auth.modules.rbac.mapper.RbacRolePermissionMapper;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.IRbacRolePermissionService;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.IRbacRoleService;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacRoleDto;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacRoleEffectiveDto;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacRolePageDto;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacRolePermissionDto;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.mapstruct.dto.RbacRoleDtoMap;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.mapstruct.dto.RbacRoleEffectiveDtoMap;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.mapstruct.dto.RbacRolePageDtoMap;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.mapstruct.vo.RbacRolePageVoMap;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.mapstruct.vo.RbacRoleQueryVoMap;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.mapstruct.vo.RbacRoleVoMap;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import indi.zxiaozhou.skillfull.corecommon.utils.CodeUtil;
import indi.zxiaozhou.skillfull.corecommon.utils.ConvertUtil;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static indi.zxiaozhou.skillfull.corecommon.constant.SysBaseConstant.SUPER_ROLE;

/**
 * 角色表(RbacRole)业务层实现
 *
 * @author zxiaozhou
 * @date 2020-10-08 13:44:03
 * @since JDK11
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class, Error.class})
public class RbacRoleServiceImpl extends ServiceImpl<RbacRoleMapper, RbacRoleEntity> implements IRbacRoleService {
    private final RbacRoleDtoMap dtoMap;
    private final RbacRoleEffectiveDtoMap effectiveDtoMap;
    private final RbacRolePageDtoMap pageDtoMap;
    private final RbacRoleVoMap voMap;
    private final RbacRoleQueryVoMap queryVoMap;
    private final RbacRolePageVoMap pageVoMap;
    private final RbacRoleMapper mapper;
    private final RbacRolePermissionMapper rbacRolePermissionMapper;
    private final RbacPermissionMapper permissionMapper;
    private final RbacRolePermissionMapper rolePermissionMapper;
    private final IRbacRolePermissionService rolePermissionService;


    @Override
    public void save(RbacRoleVo vo) throws RuntimeException {
        RbacRoleEntity entity = voMap.toEntity(vo);
        // 数据校验
        this.checkData(entity);
        entity.setRoleSysCode(generateCode(null, vo.getParentRoleId(), false));
        // 数据保存
        boolean result = super.save(entity);
        if (!result) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "保存数据失败");
        }
        RbacRolePermissionVo permissionVo = new RbacRolePermissionVo();
        permissionVo.setRoleId(entity.getRoleId());
        permissionVo.setPermissionIds(vo.getPermissionIds());
        rolePermissionService.save(permissionVo);
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
            LambdaQueryWrapper<RbacRoleEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.and(v -> v.isNull(RbacRoleEntity::getParentRoleId).or().eq(RbacRoleEntity::getParentRoleId, ""))
                    .orderByDesc(RbacRoleEntity::getRoleSysCode)
                    .last("LIMIT 1");
            RbacRoleEntity one = this.getOne(lambdaQueryWrapper);
            code = CodeUtil.getSubYouBianCode(null, Objects.isNull(one) ? null : one.getRoleSysCode());
        } else {
            // 获取上级code
            RbacRoleDto byId = this.getById(newParentId);
            // 获取本级最大code
            LambdaQueryWrapper<RbacRoleEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.like(RbacRoleEntity::getRoleSysCode, byId.getRoleSysCode() + "____")
                    .orderByDesc(RbacRoleEntity::getRoleSysCode)
                    .last("LIMIT 1");
            RbacRoleEntity one = this.getOne(lambdaQueryWrapper);
            code = CodeUtil.getSubYouBianCode(byId.getRoleSysCode(), Objects.isNull(one) ? null : one.getRoleSysCode());
        }
        return code;
    }


    /**
     * 数据校验
     *
     * @param entity ${@link RbacRoleEntity}
     * @author zxiaozhou
     * @date 2021-01-19 14:33
     */
    public void checkData(RbacRoleEntity entity) {
        // 验证用户名是否重复
        LambdaQueryWrapper<RbacRoleEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RbacRoleEntity::getRoleCode, entity.getRoleCode());
        if (StringUtils.isNotBlank(entity.getRoleId())) {
            lambdaQueryWrapper.ne(RbacRoleEntity::getRoleId, entity.getRoleId());
        }
        RbacRoleEntity one = this.getOne(lambdaQueryWrapper);
        if (Objects.nonNull(one)) {
            throw new ResponseException(Status.VERIFICATION_FAILED, "角色编码已经存在:" + entity.getRoleCode());
        }
    }


    @Override
    public void updateById(String roleId, RbacRoleVo vo) throws RuntimeException {
        // 查询数据是否存在
        RbacRoleDto byId = this.getById(roleId);
        RbacRoleEntity entity = voMap.toEntity(vo);
        entity.setRoleSysCode(generateCode(byId.getParentRoleId(), vo.getParentRoleId(), true));
        entity.setRoleId(roleId);
        // 数据校验
        this.checkData(entity);
        // 数据保存
        boolean result = super.updateById(entity);
        if (!result) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "更新数据失败");
        }
        // 如果是超级管理员则不需要绑定权限(默认有全部页面按钮权限)
        if (!SUPER_ROLE.equals(byId.getRoleCode())) {
            RbacRolePermissionVo permissionVo = new RbacRolePermissionVo();
            permissionVo.setRoleId(entity.getRoleId());
            permissionVo.setPermissionIds(vo.getPermissionIds());
//            rolePermissionService.updatePermission(permissionVo);
        }
        // 修改下级角色编码
        if (StringUtils.isNotBlank(entity.getRoleCode()) && !byId.getRoleSysCode().equals(entity.getRoleCode())) {
            LambdaQueryWrapper<RbacRoleEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.likeRight(RbacRoleEntity::getRoleSysCode, byId.getRoleSysCode());
            List<RbacRoleEntity> list = this.list(lambdaQueryWrapper);
            if (CollectionUtil.isNotEmpty(list)) {
                List<RbacRoleEntity> waitUpdateList = new ArrayList<>();
                list.forEach(v -> {
                    RbacRoleEntity newEntity = new RbacRoleEntity();
                    newEntity.setRoleId(v.getRoleId());
                    newEntity.setRoleSysCode(v.getRoleSysCode().replaceFirst(byId.getRoleSysCode(), entity.getRoleSysCode()));
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
    public PageDto<RbacRolePageDto> pageByModel(RbacRolePageVo vo) throws RuntimeException {
        IPage<RbacRolePageDto> rbacRolePageDtoIPage = mapper.pageByModel(vo.getPage(), vo);
        List<RbacRolePageDto> records = rbacRolePageDtoIPage.getRecords();
        getMenuActions(records);
        return new PageDto<>(rbacRolePageDtoIPage, records);
    }


    /**
     * 获取有效的菜单按钮信息
     *
     * @param records ${@link List<RbacRolePageDto>} 查询结果
     * @author zxiaozhou
     * @date 2020-10-09 17:36
     */
    private void getMenuActions(List<RbacRolePageDto> records) {
        if (CollectionUtil.isNotEmpty(records)) {
            final RbacRolePageDto[] superRole = new RbacRolePageDto[1];
            Set<String> roleIds = new HashSet<>();
            records.forEach(v -> {
                if (!SUPER_ROLE.equals(v.getRoleCode())) {
                    roleIds.add(v.getRoleId());
                } else {
                    superRole[0] = v;
                }
            });
            // 处理普通角色
            RbacRolePermissionQueryVo queryVo = new RbacRolePermissionQueryVo();
            queryVo.setType(5);
            queryVo.setRoleIds(roleIds);
            List<RbacRolePermissionDto> rbacRolePermissionDtos = rbacRolePermissionMapper.selectListByModel(queryVo);
            // 处理超级管理员角色
            if (Objects.nonNull(superRole[0])) {
                RbacRolePageDto rbacRolePageDto = superRole[0];
                LambdaQueryWrapper<RbacPermissionEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.and(v -> v.eq(RbacPermissionEntity::getPermissionType, 1).or().eq(RbacPermissionEntity::getPermissionType, 2))
                        .eq(RbacPermissionEntity::getPermissionStatus, 1);
                List<RbacPermissionEntity> rbacPermissionEntities = permissionMapper.selectList(lambdaQueryWrapper);
                if (CollectionUtil.isNotEmpty(rbacPermissionEntities)) {
                    List<RbacRolePermissionDto> superRbacRolePermissionDtos = ConvertUtil.listAToListB(rbacPermissionEntities, RbacRolePermissionDto.class);
                    superRbacRolePermissionDtos.forEach(v -> {
                        v.setRoleCode(rbacRolePageDto.getRoleCode());
                        v.setRoleId(rbacRolePageDto.getRoleId());
                        v.setRoleName(rbacRolePageDto.getRoleName());
                    });
                    rbacRolePermissionDtos.addAll(superRbacRolePermissionDtos);
                }
            }
            // 组装菜单角色信息
            if (CollectionUtil.isNotEmpty(rbacRolePermissionDtos)) {
                List<RbacRolePageDto.RbacRolePermissionInfoDto> permissionInfos = new ArrayList<>();
                List<RbacRolePageDto.Action> actions = new ArrayList<>();
                rbacRolePermissionDtos.forEach(v -> {
                    if (v.getPermissionType() == PermissionType.SUB_MENU.getType()) {
                        RbacRolePageDto.RbacRolePermissionInfoDto map = ConvertUtil.map(v, RbacRolePageDto.RbacRolePermissionInfoDto.class);
                        permissionInfos.add(map);
                    } else if (v.getPermissionType() == PermissionType.BOTTOM.getType()) {
                        RbacRolePageDto.Action map = ConvertUtil.map(v, RbacRolePageDto.Action.class);
                        actions.add(map);
                    }
                });
                if (CollectionUtil.isNotEmpty(actions) && CollectionUtil.isNotEmpty(permissionInfos)) {
                    permissionInfos.forEach(v -> {
                        Set<RbacRolePageDto.Action> finalActions = new HashSet<>();
                        String permissionId = v.getPermissionId();
                        actions.forEach(sv -> {
                            if (permissionId.equals(sv.getParentId())) {
                                finalActions.add(sv);
                            }
                        });
                        if (CollectionUtil.isNotEmpty(finalActions)) {
                            v.setActions(finalActions);
                        }
                    });
                }
                if (CollectionUtil.isNotEmpty(permissionInfos)) {
                    records.forEach(v -> {
                        Set<RbacRolePageDto.RbacRolePermissionInfoDto> finalPermissionInfos = new HashSet<>();
                        String roleId = v.getRoleId();
                        permissionInfos.forEach(sv -> {
                            if (roleId.equals(sv.getRoleId())) {
                                finalPermissionInfos.add(sv);
                            }
                        });
                        if (CollectionUtil.isNotEmpty(finalPermissionInfos)) {
                            v.setPermissionInfos(finalPermissionInfos);
                        }
                    });
                }
            }
        }
    }


    @Override
    public RbacRoleDto getById(String roleId) throws RuntimeException {
        RbacRoleEntity byId = super.getById(roleId);
        if (Objects.isNull(byId)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "为找到符合条件数据");
        }
        RbacRoleDto rbacRoleDto = dtoMap.toDto(byId);
        Set<String> roleIds = new HashSet<>();
        roleIds.add(rbacRoleDto.getRoleId());
        // 获取有效的菜单、按钮权限信息
        List<RbacRolePageDto> records = new ArrayList<>();
        RbacRolePageDto rolePageDto = ConvertUtil.map(rbacRoleDto, RbacRolePageDto.class);
        records.add(rolePageDto);
        getMenuActions(records);
        Set<RbacRolePageDto.RbacRolePermissionInfoDto> permissionInfos = records.get(0).getPermissionInfos();
        if (CollectionUtil.isNotEmpty(permissionInfos)) {
            List<RbacRoleDto.RbacRolePermissionInfoDto> rbacRolePermissionInfoDtos = ConvertUtil.listAToListB(new ArrayList<>(permissionInfos), RbacRoleDto.RbacRolePermissionInfoDto.class);
            rbacRoleDto.setPermissionInfos(new HashSet<>(rbacRolePermissionInfoDtos));
        }
        // 获取所有有效的目录、菜单、按钮权限id(超级管理默认所有)
        List<RbacRolePermissionDto> rbacRolePermissionDtos = new ArrayList<>();
        if (SUPER_ROLE.equals(rbacRoleDto.getRoleCode())) {
            LambdaQueryWrapper<RbacPermissionEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(RbacPermissionEntity::getPermissionStatus, 1);
            List<RbacPermissionEntity> rbacPermissionEntities = permissionMapper.selectList(lambdaQueryWrapper);
            if (CollectionUtil.isNotEmpty(rbacPermissionEntities)) {
                rbacRolePermissionDtos = ConvertUtil.listAToListB(rbacPermissionEntities, RbacRolePermissionDto.class);
                rbacRolePermissionDtos.forEach(v -> {
                    v.setRoleCode(rbacRoleDto.getRoleCode());
                    v.setRoleId(rbacRoleDto.getRoleId());
                    v.setRoleName(rbacRoleDto.getRoleName());
                });
            }
        } else {
            RbacRolePermissionQueryVo queryVo = new RbacRolePermissionQueryVo();
            queryVo.setType(0);
            queryVo.setRoleIds(roleIds);
            rbacRolePermissionDtos = rbacRolePermissionMapper.selectListByModel(queryVo);
        }
        if (CollectionUtil.isNotEmpty(rbacRolePermissionDtos)) {
            Set<String> permissionAllIds = new HashSet<>();
            rbacRolePermissionDtos.forEach(v -> permissionAllIds.add(v.getPermissionId()));
            rbacRoleDto.setPermissionAllIds(permissionAllIds);
        }
        return rbacRoleDto;
    }


    @Override
    public void deleteById(String roleId) throws RuntimeException {
        // 查询数据是否存在
        RbacRoleDto byId = this.getById(roleId);
        if (byId.getEnableDelete() == 0) {
            throw new ResponseException(Status.VERIFICATION_FAILED, "当前角色不可删除");
        }
        boolean b = this.removeById(roleId);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "删除数据失败");
        }
        // 删除角色关联信息
        rolePermissionService.deleteRolePermission(roleId);
    }


    @Override
    public void updateStatus(String roleId, Integer status) throws RuntimeException {
        // 查询数据是否存在
        RbacRoleDto byId = this.getById(roleId);
        // 如果是超级管理员永远启用
        if (SUPER_ROLE.equals(byId.getRoleCode())) {
            throw new ResponseException(Status.VERIFICATION_FAILED, "超级管理员角色状态不可操作，永远启用");
        }
        RbacRoleEntity entity = new RbacRoleEntity();
        entity.setRoleId(roleId);
        entity.setRoleStatus(status);
        boolean b = this.updateById(entity);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "角色" + (status == 0 ? "禁用" : "启用") + "失败");
        }
    }


    @Override
    public List<RbacRoleEffectiveDto> getEffectiveRoles() throws RuntimeException {
        LambdaQueryWrapper<RbacRoleEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RbacRoleEntity::getRoleStatus, 1);
        List<RbacRoleEntity> list = this.list(lambdaQueryWrapper);
        if (CollectionUtil.isEmpty(list)) {
            return Collections.emptyList();
        }
        return effectiveDtoMap.toDto(list);
    }
}