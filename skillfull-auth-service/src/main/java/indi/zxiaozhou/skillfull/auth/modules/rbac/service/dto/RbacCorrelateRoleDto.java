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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 角色关联关系表查询Response
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:37:25
 * @since JDK11
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class RbacCorrelateRoleDto implements Serializable {
    private static final long serialVersionUID = -37629361385793917L;

    @ApiModelProperty(name = "correlateRoleId", value = "角色关联关系id")
    private String correlateRoleId;

    @ApiModelProperty(name = "correlateId", value = "关联id")
    private String correlateId;

    @ApiModelProperty(name = "correlateType", value = "关联类型：1-组织机构,2-职位,3-个人")
    private Integer correlateType;

    @ApiModelProperty(name = "roleId", value = "角色id")
    private String roleId;

    @ApiModelProperty(name = "roleName", value = "角色名称")
    private String roleName;
}