// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.user.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

/**
 * 用户所有信息
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
public class UserInfoDto implements Serializable {
    private static final long serialVersionUID = -5725052195449698245L;

    @ApiModelProperty(name = "userName", value = "用户id")
    private String userId;

    @ApiModelProperty(name = "userName", value = "用户名")
    private String userName;

    @ApiModelProperty(name = "nickName", value = "用户昵称")
    private String nickName;

    @ApiModelProperty(name = "shortProfile", value = "个人简介")
    private String shortProfile;

    @ApiModelProperty(name = "realName", value = "真实姓名")
    private String realName;

    @ApiModelProperty(name = "avatar", value = "头像")
    private String avatar;

    @ApiModelProperty(name = "birthday", value = "生日")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate birthday;

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

    @ApiModelProperty(name = "orgIds", value = "负责部门")
    private String orgIds;

    @ApiModelProperty(name = "permissionActionSet", value = "用户按钮信息")
    private Set<PermissionAction> permissionActionSet;

    @ApiModelProperty(name = "roles", value = "用户所属角色")
    private Set<Role> roles;


    @Data
    public static class PermissionAction {
        @ApiModelProperty(name = "permissionId", value = "权限id(对应相应路由菜单id)")
        private String permissionId;

        @ApiModelProperty(name = "permissionName", value = "权限名称(对应相应路由菜单名称)")
        @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
        private String permissionName;

        @ApiModelProperty(name = "actions", value = "按钮权限")
        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        private Set<Action> actions;

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

    @Data
    public static class Action {
        @ApiModelProperty(name = "action", value = "指令")
        private String action;

        @ApiModelProperty(name = "describe", value = "描述")
        @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
        private String describe;

        @ApiModelProperty(name = "defaultCheck", value = "默认是否检测:true-检测,false-不检查")
        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        private boolean defaultCheck;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Action)) {
                return false;
            }
            Action action1 = (Action) o;
            return Objects.equals(getAction(), action1.getAction());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getAction());
        }
    }


    @Data
    public static class Role {
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

}

