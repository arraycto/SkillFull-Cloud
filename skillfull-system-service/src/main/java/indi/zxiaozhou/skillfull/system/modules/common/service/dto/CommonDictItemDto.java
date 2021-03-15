// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.common.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 数据字典配置项表查询Response
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:25:24
 * @since JDK11
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class CommonDictItemDto implements Serializable {
    private static final long serialVersionUID = 126993886858081293L;

    @ApiModelProperty(name = "itemId", value = "字典项id")
    private String itemId;

    @ApiModelProperty(name = "dictId", value = "字典id")
    private String dictId;

    @ApiModelProperty(name = "itemText", value = "字典项名称")
    private String itemText;

    @ApiModelProperty(name = "itemValue", value = "字典项值")
    private String itemValue;

    @ApiModelProperty(name = "dictCode", value = "字典编码")
    private String dictCode;

    @ApiModelProperty(name = "sortOrder", value = "排序,越小越靠前,默认0")
    private Integer sortOrder;

    @ApiModelProperty(name = "dictName", value = "字典名称")
    private String dictName;

    @ApiModelProperty(name = "dictType", value = "字典类型：0-字符串,1-数字。默认0")
    private String dictType;
}