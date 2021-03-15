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

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 数据字典配置项表添加或修改Request
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:25:24
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class CommonDictItemVo implements Serializable {
    private static final long serialVersionUID = -38550636353943616L;

    @ApiModelProperty(name = "dictId", value = "字典id", required = true)
    @NotBlankOrNull(message = "字典id不能为空")
    private String dictId;

    @ApiModelProperty(name = "itemText", value = "字典项名称", required = true)
    @NotBlankOrNull(message = "字典项名称不能为空")
    private String itemText;

    @ApiModelProperty(name = "itemValue", value = "字典项值", required = true)
    @NotBlankOrNull(message = "字典项值不能为空")
    private String itemValue;

    @ApiModelProperty(name = "sortOrder", value = "排序,越小越靠前,默认0")
    private Integer sortOrder = 0;

    @ApiModelProperty(name = "itemStatus", value = "字典项状态：1启用，0禁用，默认0", required = true)
    @NotNull(message = "启用状态不能为空")
    private Integer itemStatus = 0;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
}