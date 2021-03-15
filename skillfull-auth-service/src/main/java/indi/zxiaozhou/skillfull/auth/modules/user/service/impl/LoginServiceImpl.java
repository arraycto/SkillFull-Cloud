// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.zxiaozhou.skillfull.auth.core.constant.PermissionType;
import indi.zxiaozhou.skillfull.auth.modules.rbac.entity.RbacPermissionEntity;
import indi.zxiaozhou.skillfull.auth.modules.rbac.entity.RbacUserEntity;
import indi.zxiaozhou.skillfull.auth.modules.rbac.mapper.RbacPermissionMapper;
import indi.zxiaozhou.skillfull.auth.modules.rbac.mapper.RbacUserMapper;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.ActionPermissionDto;
import indi.zxiaozhou.skillfull.auth.modules.user.controller.vo.OnlineUserPageVo;
import indi.zxiaozhou.skillfull.auth.modules.user.service.ILoginService;
import indi.zxiaozhou.skillfull.auth.modules.user.service.dto.OnlineUserInfoDto;
import indi.zxiaozhou.skillfull.auth.modules.user.service.dto.RouterInfoDto;
import indi.zxiaozhou.skillfull.auth.modules.user.service.dto.UserInfoDto;
import indi.zxiaozhou.skillfull.auth.modules.user.service.mapstruct.PermissionToInfoDtoMap;
import indi.zxiaozhou.skillfull.auth.modules.user.service.mapstruct.PermissionToMetaDtoMap;
import indi.zxiaozhou.skillfull.auth.modules.user.service.mapstruct.UserInfoToOnlineUserInfoDtoMap;
import indi.zxiaozhou.skillfull.auth.security.token.TokenStore;
import indi.zxiaozhou.skillfull.corecommon.base.model.UserAndAuthModel;
import indi.zxiaozhou.skillfull.corecommon.constant.SysBaseConstant;
import indi.zxiaozhou.skillfull.corecommon.constant.SysBaseType;
import indi.zxiaozhou.skillfull.corecommon.utils.ConvertUtil;
import indi.zxiaozhou.skillfull.corecommon.utils.CoreCommonUtils;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.coremvc.utils.ContextHolderUtils;
import indi.zxiaozhou.skillfull.coremvc.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * 登录相关业务层接口实现
 *
 * @author zxiaozhou
 * @date 2020-07-17 20:23
 * @since JDK11
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl extends ServiceImpl<RbacUserMapper, RbacUserEntity> implements ILoginService {
    private final RbacPermissionMapper rbacPermissionMapper;
    private final RbacUserMapper rbacUserMapper;
    private final TokenStore tokenStore;
    private final PermissionToInfoDtoMap toInfoDtoMap;
    private final PermissionToMetaDtoMap toMetaDtoMap;
    private final UserInfoToOnlineUserInfoDtoMap userInfoDtoMap;
    private final RedisTemplate<String, Object> redisTemplate;


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public UserAndAuthModel getPermissionInfo(RbacUserEntity rbacUserEntity, String orgId, String systemId) throws RuntimeException {
        // 获取用户信息
        UserAndAuthModel userAndAuthModel = ConvertUtil.map(rbacUserEntity, UserAndAuthModel.class);
        // 获取用户角色
        Set<UserAndAuthModel.Role> roles = new HashSet<>(1);
        UserAndAuthModel.Role role = new UserAndAuthModel.Role();
        role.setRoleCode(SysBaseType.SUPER_ROLE.getType());
        role.setRoleName(SysBaseType.SUPER_ROLE.getTypeDescribe());
        roles.add(role);
        userAndAuthModel.setRoles(roles);
        // 获取按钮权限信息
        List<ActionPermissionDto> permissionDtoList = rbacPermissionMapper.getActions();
        if (CollectionUtil.isNotEmpty(permissionDtoList)) {
            // 获取按钮分组信息
            Map<String, List<ActionPermissionDto>> middleList = new HashMap<>(16);
            permissionDtoList.forEach(v -> {
                List<ActionPermissionDto> permissionEntities = middleList.get(v.getPermissionId());
                if (CollectionUtil.isEmpty(permissionEntities)) {
                    permissionEntities = new ArrayList<>();
                }
                permissionEntities.add(v);
                middleList.put(v.getPermissionId(), permissionEntities);
            });

            // 分组信息转为目标按钮权限信息
            Set<UserAndAuthModel.PermissionAction> permissionActionSet = new HashSet<>();
            middleList.forEach((k, v) -> {
                UserAndAuthModel.PermissionAction permission = new UserAndAuthModel.PermissionAction();
                ActionPermissionDto actionPermissionDto = v.get(0);
                permission.setPermissionId(actionPermissionDto.getPermissionId());
                permission.setPermissionName(actionPermissionDto.getPermissionName());
                Set<UserAndAuthModel.Action> actionSet = new HashSet<>();
                v.forEach(sv -> {
                    String[] actions = sv.getActions().split(",");
                    for (String strAction : actions) {
                        UserAndAuthModel.Action action = new UserAndAuthModel.Action();
                        action.setDefaultCheck(Objects.nonNull(sv.getButtonStrategy()) && sv.getButtonStrategy() == 1);
                        action.setDescribe(sv.getTitle());
                        action.setCheckActionRequest(sv.getCheckActionRequest());
                        action.setActionsUri(sv.getActionsUri());
                        action.setAction(strAction);
                        actionSet.add(action);
                    }
                });
                permission.setActions(actionSet);
                permissionActionSet.add(permission);
            });
            userAndAuthModel.setPermissionActionSet(permissionActionSet);
        }
        // 获取用户数据权限信息
        return userAndAuthModel;
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public List<RouterInfoDto> getRouterInfo() throws RuntimeException {
        // 查询有效的菜单
        LambdaQueryWrapper<RbacPermissionEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RbacPermissionEntity::getPermissionStatus, 1)
                .ne(RbacPermissionEntity::getPermissionType, PermissionType.BOTTOM.getType())
                .orderByAsc(RbacPermissionEntity::getSortNo)
                .orderByDesc(RbacPermissionEntity::getCreateTime);
        List<RbacPermissionEntity> permissionEntities = rbacPermissionMapper.selectList(queryWrapper);
        // 组装菜单信息
        if (CollectionUtil.isNotEmpty(permissionEntities)) {
            List<RouterInfoDto> routerInfoDtoList = new ArrayList<>(16);
            permissionEntities.forEach(v -> {
                RouterInfoDto routerInfoDto = toInfoDtoMap.toDto(v);
                RouterInfoDto.Meta meta = toMetaDtoMap.toDto(v);
                Set<String> permissions = new HashSet<>();
                permissions.add(v.getPermissionId());
                meta.setPermissions(permissions);
                routerInfoDto.setMeta(meta);
                routerInfoDtoList.add(routerInfoDto);
            });
            return routerInfoDtoList;
        }
        return Collections.emptyList();
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public UserInfoDto getUserInfo() throws RuntimeException {
        // 查询用户信息
        RbacUserEntity rbacUserEntity = rbacUserMapper.selectById(ContextHolderUtils.getUserAndAuthModel().getUserId());
        UserAndAuthModel permissionInfo = this.getPermissionInfo(rbacUserEntity, null, null);
        // 更新缓存用户权限信息
        tokenStore.updateUserAndAuth(permissionInfo);
        // 过滤掉编辑控制的按钮权限
        Set<UserAndAuthModel.PermissionAction> permissionActionSet = permissionInfo.getPermissionActionSet();
        Set<UserAndAuthModel.PermissionAction> finalPermissionActionSet = new HashSet<>();
        if (CollectionUtil.isNotEmpty(permissionActionSet)) {
            permissionActionSet.forEach(v -> {
                Set<UserAndAuthModel.Action> actions = v.getActions();
                if (CollectionUtil.isNotEmpty(actions)) {
                    Set<UserAndAuthModel.Action> finalActions = new HashSet<>();
                    actions.forEach(sv -> {
                        if (sv.getDefaultCheck()) {
                            finalActions.add(sv);
                        }
                    });
                    if (CollectionUtil.isNotEmpty(finalActions)) {
                        v.setActions(finalActions);
                        finalPermissionActionSet.add(v);
                    }
                }
            });
        }
        if (CollectionUtil.isNotEmpty(finalPermissionActionSet)) {
            permissionInfo.setPermissionActionSet(finalPermissionActionSet);
        } else {
            permissionInfo.setPermissionActionSet(Collections.emptySet());
        }
        // 转换为前端需要的信息
        return ConvertUtil.map(permissionInfo, UserInfoDto.class);
    }


    @Override
    public String refreshToken() throws RuntimeException {
        return null;
    }


    @Override
    public PageDto<OnlineUserInfoDto> selectOnlinePage(OnlineUserPageVo vo) {
        PageDto<String> keysForPage = RedisUtils.findKeysForPage(SysBaseConstant.USER_LOGIN_PREFIX, vo.getCurrent(), vo.getSize());
        List<String> records = keysForPage.getRecords();
        if (CollectionUtil.isEmpty(records)) {
            return new PageDto<>(0L, vo.getCurrent(), vo.getSize(), null);
        }
        List<Object> objects = redisTemplate.opsForValue().multiGet(records);
        List<UserAndAuthModel> userAndAuthModels = JSONArray.parseArray(CoreCommonUtils.objectToJsonStr(objects), UserAndAuthModel.class);
        List<OnlineUserInfoDto> collect = userAndAuthModels.stream().map(v -> {
            OnlineUserInfoDto dto = userInfoDtoMap.toEntity(v);
            UserAndAuthModel.AuthUserOnlineInfo onlineInfo = v.getOnlineInfo();
            dto.setLoginIp(onlineInfo.getLoginIp());
            dto.setLoginEquipment(onlineInfo.getLoginEquipment());
            dto.setLoginTime(onlineInfo.getLoginTime());
            dto.setCurrentRefreshTokenTime(onlineInfo.getCurrentRefreshTokenTime());
            dto.setLoginUnique(SysBaseConstant.USER_LOGIN_PREFIX + dto.getUserId() + "_" + onlineInfo.getSystemId());
            return dto;
        }).collect(Collectors.toList());
        return new PageDto<>(keysForPage.getTotal(), keysForPage.getCurrent(), keysForPage.getSize(), collect);
    }


    @Override
    public void kickOut(String loginUnique) {
        // 直接设置缓存用户信息过期时间为2秒，触发消息中心发送客户端用户下线通知
        Boolean aBoolean = redisTemplate.hasKey(loginUnique);
        if (Objects.nonNull(aBoolean) && aBoolean) {
            redisTemplate.expire(loginUnique, 2, TimeUnit.SECONDS);
            String[] s = loginUnique.split("_");
            String systemId = s[s.length - 1];
            redisTemplate.delete(SysBaseConstant.USER_TOKEN_SECURITY_PREFIX + systemId);
        }
    }
}
