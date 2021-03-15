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
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 区域表添加或修改Request
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:25:03
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class CommonAreaVo implements Serializable {
    private static final long serialVersionUID = 255355653140939408L;

    @ApiModelProperty(name = "areaId", value = "区域id,12位长度", required = true)
    @NotBlankOrNull(message = "区域id不能为空")
    @Length(min = 12, max = 12, message = "区域id必须12位,每3位代表一个级别")
    private String areaId;

    @ApiModelProperty(name = "parentId", value = "上级区域id")
    private String parentId;

    @ApiModelProperty(name = "prePinYin", value = "区域名称拼音的第一个字母", required = true)
    @NotBlankOrNull(message = "区域名称拼音的第一个字母不能为空")
    private String prePinYin;

    @ApiModelProperty(name = "simplePy", value = "首字母简拼", required = true)
    @NotBlankOrNull(message = "首字母简拼不能为空")
    private String simplePy;

    @ApiModelProperty(name = "pinYin", value = "区域名称拼音", required = true)
    @NotBlankOrNull(message = "区域名称拼音不能为空")
    private String pinYin;

    @ApiModelProperty(name = "provinceId", value = "所属省级id")
    private String provinceId;

    @ApiModelProperty(name = "simpleName", value = "中文简称", required = true)
    @NotBlankOrNull(message = "中文简称不能为空")
    private String simpleName;

    @ApiModelProperty(name = "areaLevel", value = "级别：1为省级，2为市级，3为县级, 4为乡, 5为村,4为乡,5为村", required = true)
    @NotBlankOrNull(message = "级别：1为省级，2为市级，3为县级, 4为乡, 5为村不能为空")
    private Integer areaLevel;

    @ApiModelProperty(name = "areaName", value = "区域名称", required = true)
    @NotBlankOrNull(message = "区域名称不能为空")
    private String areaName;

    @ApiModelProperty(name = "areaCode", value = "区号", required = true)
    @NotBlankOrNull(message = "区号不能为空")
    private String areaCode;

    @ApiModelProperty(name = "cityId", value = "所属城市id")
    private String cityId;

    @ApiModelProperty(name = "lon", value = "本区域经度")
    private String lon;

    @ApiModelProperty(name = "lat", value = "本区域纬度")
    private String lat;

    @ApiModelProperty(name = "zipCode", value = "邮编")
    private String zipCode;

    @ApiModelProperty(name = "autoBind", value = "绑定方式:0-手动,1-自动。默认0")
    private Integer autoBind = 0;

    @ApiModelProperty(name = "countyId", value = "区县id")
    private String countyId;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
}