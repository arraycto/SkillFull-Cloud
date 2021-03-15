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
 * 区域表查询Response
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:25:03
 * @since JDK11
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class CommonAreaDto implements Serializable {
    private static final long serialVersionUID = -97880700851357588L;

    @ApiModelProperty(name = "areaId", value = "区域id")
    private String areaId;

    @ApiModelProperty(name = "parentId", value = "上级区域id")
    private String parentId;

    @ApiModelProperty(name = "prePinYin", value = "区域名称拼音的第一个字母")
    private String prePinYin;

    @ApiModelProperty(name = "simplePy", value = "首字母简拼")
    private String simplePy;

    @ApiModelProperty(name = "pinYin", value = "区域名称拼音")
    private String pinYin;

    @ApiModelProperty(name = "wholeName", value = "完整名称")
    private String wholeName;

    @ApiModelProperty(name = "provinceId", value = "所属省级id")
    private String provinceId;

    @ApiModelProperty(name = "simpleName", value = "中文简称")
    private String simpleName;

    @ApiModelProperty(name = "areaLevel", value = "级别：1为省级，2为市级，3为县级, 4为乡, 5为村")
    private Integer areaLevel;

    @ApiModelProperty(name = "areaName", value = "区域名称")
    private String areaName;

    @ApiModelProperty(name = "areaCode", value = "区号")
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
    private Integer autoBind;

    @ApiModelProperty(name = "countyId", value = "区县id")
    private String countyId;

    @ApiModelProperty(name = "remark", value = "备注/说明")
    private String remark;

    @ApiModelProperty(name = "createUserName", value = "创建用户姓名")
    private String createUserName;

    @ApiModelProperty(name = "hasChildren", value = "是否有子节点,默认false")
    private boolean hasChildren;
}