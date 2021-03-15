// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.corecommon.constant.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 常量字典Model
 *
 * @author zxiaozhou
 * @date 2020-09-12 16:56
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class ConstantDictModel implements Serializable {
    private static final long serialVersionUID = 3970903709928893198L;

    @ApiModelProperty(name = "type", value = "类型")
    private String type;

    @ApiModelProperty(name = "typeDescribe", value = "类型描述")
    private String typeDescribe;

    @ApiModelProperty(name = "typeName", value = "类型名称")
    private String typeName;
}
