// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * 角色-权限表条件查询Request
 *
 * @author zxiaozhou
 * @date 2020-10-08 13:29:16
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel
public class RbacRolePermissionQueryVo implements Serializable {
    private static final long serialVersionUID = 476761397812212288L;

    @ApiModelProperty(name = "roleIds", value = "角色ids")
    private Set<String> roleIds;

    @ApiModelProperty(name = "roleCodes", value = "角色编码")
    private Set<String> roleCodes;

    @ApiModelProperty(name = "type", value = "查询类型:0-所有,1-目录,2-菜单,3-按钮,4-目录菜单,5-菜单按钮。默认0")
    private Integer type = 0;
}