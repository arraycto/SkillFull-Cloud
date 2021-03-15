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

import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotBlankOrNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 角色-权限表添加或修改Request
 *
 * @author zxiaozhou
 * @date 2020-10-08 13:29:14
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class RbacRolePermissionVo implements Serializable {
    private static final long serialVersionUID = 766404491615690831L;

    @ApiModelProperty(name = "roleId", value = "角色id", required = true)
    @NotBlankOrNull(message = "角色id不能为空")
    private String roleId;

    @ApiModelProperty(name = "permissionIds", value = "权限信息")
    private List<String> permissionIds;

}