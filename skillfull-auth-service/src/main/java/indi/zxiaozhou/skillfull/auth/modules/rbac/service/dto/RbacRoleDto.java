// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * 角色表查询Response
 *
 * @author zxiaozhou
 * @date 2020-10-08 13:44:02
 * @since JDK11
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class RbacRoleDto implements Serializable {
    private static final long serialVersionUID = -17389117269525927L;

    @ApiModelProperty(name = "roleId", value = "角色id")
    private String roleId;

    @ApiModelProperty(name = "roleName", value = "角色名称")
    private String roleName;

    @ApiModelProperty(name = "roleCode", value = "角色编码")
    private String roleCode;

    @ApiModelProperty(name = "roleStatus", value = "角色状态:0-禁用,1-启用,默认0")
    private Integer roleStatus;

    @ApiModelProperty(name = "enableDelete", value = "是否可删除:0-不可删除,1-可删除。默认1(用户系统内置数据不可删除)")
    private Integer enableDelete;

    @ApiModelProperty(name = "autoBind", value = "绑定方式:0-手动,1-自动。默认0")
    private Integer autoBind;

    @ApiModelProperty(name = "roleSysCode", value = "角色系统编码(系统自动创建)")
    private String roleSysCode;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "createUserName", value = "创建用户姓名")
    private String createUserName;

    @ApiModelProperty(name = "parentRoleId", value = "上级角色id", hidden = true)
    private String parentRoleId;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;

    @ApiModelProperty(name = "permissionInfos", value = "按钮绑定权限")
    private Set<RbacRolePermissionInfoDto> permissionInfos = Collections.emptySet();

    @ApiModelProperty(name = "permissionAllIds", value = "所有关联的角色权限信息")
    private Set<String> permissionAllIds = Collections.emptySet();


    @Data
    public static class RbacRolePermissionInfoDto implements Serializable {
        private static final long serialVersionUID = -63399996952662860L;
        
        @ApiModelProperty(name = "roleCode", value = "角色编码")
        private String roleCode;

        @ApiModelProperty(name = "roleSysCode", value = "角色系统编码(系统自动创建)")
        private String roleSysCode;

        @ApiModelProperty(name = "roleId", value = "角色id")
        private String roleId;

        @ApiModelProperty(name = "permissionId", value = "权限id")
        private String permissionId;

        @ApiModelProperty(name = "title", value = "菜单名称")
        private String title;

        @ApiModelProperty(name = "actions", value = "菜单按钮权限")
        private Set<Action> actions = Collections.emptySet();

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof RbacRolePermissionInfoDto)) {
                return false;
            }
            RbacRolePermissionInfoDto that = (RbacRolePermissionInfoDto) o;
            return Objects.equals(getPermissionId(), that.getPermissionId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getPermissionId());
        }
    }

    @Data
    public static class Action implements Serializable {
        private static final long serialVersionUID = 6588630894991792617L;

        @ApiModelProperty(name = "permissionId", value = "权限id")
        private String permissionId;

        @ApiModelProperty(name = "actions", value = "按钮权限编码，例如：“sys:schedule:list”,多个逗号隔开")
        private String actions;

        @ApiModelProperty(name = "title", value = "菜单名称")
        private String title;

        @ApiModelProperty(name = "parentId", value = "父id")
        private String parentId;
    }

}