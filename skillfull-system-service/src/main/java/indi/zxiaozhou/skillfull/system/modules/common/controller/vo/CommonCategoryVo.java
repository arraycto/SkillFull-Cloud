// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.common.controller.vo;

import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotBlankOrNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 分类字典表添加或修改Request
 *
 * @author zxiaozhou
 * @date 2021-01-07 23:40:01
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class CommonCategoryVo implements Serializable {
    private static final long serialVersionUID = 348923460711945337L;

    @ApiModelProperty(name = "parentId", value = "父级id")
    private String parentId;

    @ApiModelProperty(name = "categoryName", value = "分类名称", required = true)
    @NotBlankOrNull(message = "分类名称不能为空")
    private String categoryName;

    @ApiModelProperty(name = "categoryCommonCode", value = "分类统一编码", required = true)
    @NotBlankOrNull(message = "分类统一编码不能为空")
    private String categoryCommonCode;

    @ApiModelProperty(name = "categoryCode", value = "分类编码", required = true)
    @NotBlankOrNull(message = "分类编码不能为空")
    private String categoryCode;

    @ApiModelProperty(name = "isParent", value = "是否父节:0-不是，1-是，默认0", required = true)
    @NotBlankOrNull(message = "是否父节:0-不是，1-是，默认0不能为空")
    private Integer isParent;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
}