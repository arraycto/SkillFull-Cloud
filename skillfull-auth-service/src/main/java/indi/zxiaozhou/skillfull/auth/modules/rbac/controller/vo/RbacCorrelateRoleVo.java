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
 * 角色关联关系表添加或修改Request
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:37:26
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class RbacCorrelateRoleVo implements Serializable {
    private static final long serialVersionUID = -22834766477398883L;

    @ApiModelProperty(name = "correlateId", value = "关联id", required = true)
    @NotBlankOrNull(message = "关联id不能为空")
    private String correlateId;

    @ApiModelProperty(name = "correlateType", value = "关联类型：1-组织机构,2-职位,3-个人", required = true)
    @NotBlankOrNull(message = "关联类型：1-组织机构,2-职位,3-个人不能为空")
    private Integer correlateType;

    @ApiModelProperty(name = "roleIds", value = "角色id")
    private List<String> roleIds;
}