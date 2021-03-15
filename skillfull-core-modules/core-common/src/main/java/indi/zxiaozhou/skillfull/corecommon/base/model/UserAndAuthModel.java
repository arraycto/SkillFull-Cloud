// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.corecommon.base.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * 用户信息与权限信息
 *
 * @author zxiaozhou
 * @date 2020-07-01 03:21
 * @since JDK11
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class UserAndAuthModel implements Serializable {
    private static final long serialVersionUID = -5725052195449698245L;

    @ApiModelProperty(name = "userName", value = "用户id")
    private String userId;

    @ApiModelProperty(name = "userName", value = "用户名")
    private String userName;

    @ApiModelProperty(name = "nickName", value = "用户昵称")
    private String nickName;

    @ApiModelProperty(name = "realName", value = "真实姓名")
    private String realName;

    @ApiModelProperty(name = "userStatus", value = "状态:0-未激活,1-正常,2-冻结,默认1")
    private Integer userStatus;

    @ApiModelProperty(name = "loginFailErrorNum", value = "连续登录错误次数")
    private Integer loginFailErrorNum;

    @ApiModelProperty(name = "avatar", value = "头像")
    private String avatar;

    @ApiModelProperty(name = "password", value = "密码")
    @JsonIgnore
    private String password;

    @ApiModelProperty(name = "salt", value = "密码盐")
    @JsonIgnore
    private String salt;

    @ApiModelProperty(name = "birthday", value = "生日")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate birthday;

    @ApiModelProperty(name = "shortProfile", value = "个人简介")
    private String shortProfile;

    @ApiModelProperty(name = "sex", value = "性别:0-默认未知,1-男,2-女,默认0")
    private Integer sex;

    @ApiModelProperty(name = "email", value = "邮件")
    private String email;

    @ApiModelProperty(name = "phone", value = "电话号码")
    private String phone;

    @ApiModelProperty(name = "currentDepartId", value = "当前部门id")
    private String currentDepartId;

    @ApiModelProperty(name = "currentDepartName", value = "当前部门名称")
    private String currentDepartName;

    @ApiModelProperty(name = "currentDepartCode", value = "当前部门code")
    private String currentDepartCode;

    @ApiModelProperty(name = "currentAreaId", value = "当前区域id(即区域编码)")
    private String currentAreaId;

    @ApiModelProperty(name = "currentAreaName", value = "当前区域名称名称")
    private String currentAreaName;

    @ApiModelProperty(name = "currentWholeName", value = "当前区域完整名称")
    private String currentWholeName;

    @ApiModelProperty(name = "currentPositionId", value = "当前职位id")
    private String currentPositionId;

    @ApiModelProperty(name = "currentPositionCode", value = "当前职位名称")
    private String currentPositionCode;

    @ApiModelProperty(name = "currentPositionName", value = "当前职位名称")
    private String currentPositionName;

    @ApiModelProperty(name = "currentSystemId", value = "当前系统id")
    private String currentSystemId;

    @ApiModelProperty(name = "currentSystemName", value = "当前系统名称")
    private String currentSystemName;

    @ApiModelProperty(name = "currentSystemCode", value = "当前系统编码")
    private String currentSystemCode;

    @ApiModelProperty(name = "orgIds", value = "负责部门")
    private String orgIds;

    @ApiModelProperty(name = "loginType", value = "登录类型:0-单设备登录,1-多设备登录")
    private Integer loginType;

    @ApiModelProperty(name = "permissionActionSet", value = "用户按钮信息")
    private Set<PermissionAction> permissionActionSet = Collections.emptySet();

    @ApiModelProperty(name = "roles", value = "用户所属角色")
    private Set<Role> roles = Collections.emptySet();

    @ApiModelProperty(name = "permissionDataSet", value = "用户数据权限")
    private Set<Object> permissionDataSet = Collections.emptySet();

    @ApiModelProperty(name = "AgentInfo", value = "代理人信息")
    private Set<AgentInfo> agentInfos;

    @ApiModelProperty(name = "onlineInfo", value = "登录信息")
    private AuthUserOnlineInfo onlineInfo;


    @Getter
    @Setter
    @ToString
    public static class PermissionAction implements Serializable {
        private static final long serialVersionUID = 3099026993219578523L;

        @ApiModelProperty(name = "permissionId", value = "权限id(对应相应路由菜单id)")
        private String permissionId;

        @ApiModelProperty(name = "permissionName", value = "权限名称(对应相应路由菜单名称)")
        @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
        private String permissionName;

        @ApiModelProperty(name = "actions", value = "按钮权限")
        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        private Set<Action> actions = Collections.emptySet();

        @ApiModelProperty(name = "actionsUri", value = "按钮权限后端对应uri(相对地址,check_action_request为true时必填)")
        private String actionsUri;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof PermissionAction)) {
                return false;
            }
            PermissionAction that = (PermissionAction) o;
            return Objects.equals(getPermissionId(), that.getPermissionId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getPermissionId());
        }
    }

    @Getter
    @Setter
    @ToString
    public static class Action implements Serializable {
        private static final long serialVersionUID = -3148648474688359998L;

        @ApiModelProperty(name = "action", value = "指令")
        private String action;

        @ApiModelProperty(name = "describe", value = "描述")
        @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
        private String describe;

        @ApiModelProperty(name = "actionsUri", value = "按钮权限后端对应uri(相对地址,check_action_request为true时必填)")
        private String actionsUri;

        @ApiModelProperty(name = "checkActionRequest", value = "按钮权限是否校验后端，true-校验,false-不校验(boolean类型,当校验时actions_uri必填)")
        private Boolean checkActionRequest;

        @ApiModelProperty(name = "defaultCheck", value = "默认是否检测:true-检测,false-不检查")
        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        private Boolean defaultCheck;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Action)) {
                return false;
            }
            Action action1 = (Action) o;
            return Objects.equals(getAction(), action1.getAction()) && Objects.equals(getActionsUri(), action1.getActionsUri()) && Objects.equals(getCheckActionRequest(), action1.getCheckActionRequest()) && Objects.equals(getDefaultCheck(), action1.getDefaultCheck());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getAction(), getActionsUri(), getCheckActionRequest(), getDefaultCheck());
        }
    }


    @Getter
    @Setter
    @ToString
    public static class Role implements Serializable {
        private static final long serialVersionUID = 4071105391782937997L;
        @ApiModelProperty(name = "roleName", value = "角色名称")
        private String roleName;

        @ApiModelProperty(name = "roleCode", value = "角色编码")
        private String roleCode;

        @ApiModelProperty(name = "roleLevel", value = "角色级别：越小权限越大")
        private Integer roleLevel;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Role)) {
                return false;
            }
            Role role = (Role) o;
            return Objects.equals(getRoleCode(), role.getRoleCode());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getRoleCode());
        }
    }

    @Getter
    @Setter
    @ToString
    public static class AgentInfo implements Serializable {
        private static final long serialVersionUID = -2447072397405849742L;

        @ApiModelProperty(name = "userName", value = "用户id")
        private String userId;

        @ApiModelProperty(name = "userName", value = "用户名")
        private String userName;

        @ApiModelProperty(name = "realName", value = "真实姓名")
        private String realName;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof AgentInfo)) {
                return false;
            }
            AgentInfo agentInfo = (AgentInfo) o;
            return Objects.equals(getUserId(), agentInfo.getUserId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getUserId());
        }
    }


    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    public static class AuthUserOnlineInfo implements Serializable {
        private static final long serialVersionUID = -3529509184883905229L;

        @ApiModelProperty(name = "loginIp", value = "登录ip")
        private String loginIp;

        @ApiModelProperty(name = "systemId", value = "登录系统标识")
        private String systemId;

        @ApiModelProperty(name = "loginEquipment", value = "最近登录设备信息")
        private String loginEquipment;

        @ApiModelProperty(name = "loginTime", value = "登录时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime loginTime;

        @ApiModelProperty(name = "currentRefreshTokenTime", value = "最近一次刷新token时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime currentRefreshTokenTime;

        @ApiModelProperty(name = "credentials", value = "security credentials")
        private Object credentials;
    }
}

