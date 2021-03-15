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

import indi.zxiaozhou.skillfull.coremvc.base.controller.vo.BasePageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 数据字典配置项表分页查询Request
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:25:25
 * @since JDK11
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel
public class CommonDictItemPageVo extends BasePageVo {
    private static final long serialVersionUID = 853710730536860187L;

    @ApiModelProperty(name = "itemText", value = "字典项名称")
    private String itemText;

    @ApiModelProperty(name = "itemStatus", value = "字典项状态：1启用，0不启用，默认0")
    private Integer itemStatus;

    @ApiModelProperty(name = "dictId", value = "字典id", required = true)
    @NotBlank(message = "字典id不能为空")
    private String dictId;
}