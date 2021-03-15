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

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;

/**
 * 角色表添加或修改Request
 *
 * @author zxiaozhou
 * @date 2020-10-08 13:44:02
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class RbacRoleVo implements Serializable {
    private static final long serialVersionUID = 857600664841730673L;

    @ApiModelProperty(name = "roleName", value = "角色名称", required = true)
    @NotBlankOrNull(message = "角色名称不能为空")
    private String roleName;

    @ApiModelProperty(name = "roleCode", value = "角色编码", required = true)
    @NotBlankOrNull(message = "角色编码不能为空")
    private String roleCode;

    @ApiModelProperty(name = "roleStatus", value = "角色状态:0-禁用,1-启用,默认0", required = true)
    @NotBlankOrNull(message = "角色状态不能为空")
    @Min(value = 0, message = "角色状态只能为0、1")
    @Max(value = 1, message = "角色状态只能为0、1")
    private Integer roleStatus;

    @ApiModelProperty(name = "autoBind", value = "绑定方式:0-手动,1-自动。默认0", required = true)
    @NotBlankOrNull(message = "绑定方式不能为空")
    @Min(value = 0, message = "绑定方式只能为0、1")
    @Max(value = 1, message = "绑定方式只能为0、1")
    private Integer autoBind;

    @ApiModelProperty(name = "parentRoleId", value = "上级角色id", hidden = true)
    private String parentRoleId;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "permissionIds", value = "权限信息")
    private List<String> permissionIds;
}