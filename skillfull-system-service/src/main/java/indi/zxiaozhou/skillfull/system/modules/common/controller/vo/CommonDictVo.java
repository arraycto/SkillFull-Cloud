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
 * 数据字典表添加或修改Request
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:25:17
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class CommonDictVo implements Serializable {
    private static final long serialVersionUID = -18634770323379386L;

    @ApiModelProperty(name = "dictName", value = "字典名称", required = true)
    @NotBlankOrNull(message = "字典名称不能为空")
    private String dictName;

    @ApiModelProperty(name = "dictCode", value = "字典编码", required = true)
    @NotBlankOrNull(message = "字典编码不能为空")
    private String dictCode;

    @ApiModelProperty(name = "dictType", value = "字典类型：0-字符串,1-数字。默认0", required = true)
    @NotBlankOrNull(message = "字典类型：0-字符串,1-数字。默认0不能为空")
    private String dictType;

    @ApiModelProperty(name = "dictStatus", value = "字典状态：1启用，0禁用，默认0")
    private Integer dictStatus = 0;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
}