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

/**
 * 角色关联关系表条件查询Request
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:37:26
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel
public class RbacCorrelateRoleQueryVo implements Serializable {
    private static final long serialVersionUID = 445607211876731671L;

    @ApiModelProperty(name = "correlateId", value = "关联id")
    private String correlateId;

    @ApiModelProperty(name = "correlateType", value = "关联类型：1-组织机构,2-职位,3-个人")
    private Integer correlateType;
}