// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.zxiaozhou.skillfull.coremvc.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 区域表(CommonArea)Entity
 *
 * @author zxiaozhou
 * @date 2021-02-12 20:25:30
 * @since JDK1.8
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_common_area")
public class CommonAreaEntity extends BaseEntity {
    private static final long serialVersionUID = -64204422877964486L;

    @TableId
    private String areaId;

    @ApiModelProperty(name = "provinceId", value = "所属省级id")
    private String provinceId;

    @ApiModelProperty(name = "simpleName", value = "中文简称")
    private String simpleName;

    @ApiModelProperty(name = "areaLevel", value = "区域级别:1为省级，2为市级，3为县级")
    private Integer areaLevel;

    @ApiModelProperty(name = "areaName", value = "区域名称")
    private String areaName;

    @ApiModelProperty(name = "areaCode", value = "区号")
    private String areaCode;

    @ApiModelProperty(name = "cityId", value = "所属城市id")
    private String cityId;

    @ApiModelProperty(name = "parentId", value = "上级区域id")
    private String parentId;

    @ApiModelProperty(name = "lon", value = "本区域经度")
    private String lon;

    @ApiModelProperty(name = "lat", value = "本区域纬度")
    private String lat;

    @ApiModelProperty(name = "zipCode", value = "邮编")
    private String zipCode;

    @ApiModelProperty(name = "wholeName", value = "完整名称")
    private String wholeName;

    @ApiModelProperty(name = "prePinYin", value = "区域名称拼音的第一个字母")
    private String prePinYin;

    @ApiModelProperty(name = "pinYin", value = "名称全拼")
    private String pinYin;

    @ApiModelProperty(name = "simplePy", value = "首字母简拼")
    private String simplePy;

    @ApiModelProperty(name = "countyId", value = "区县id")
    private String countyId;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "autoBind", value = "绑定方式:0-手动,1-自动。默认0")
    private Integer autoBind;

    @ApiModelProperty(name = "createAreaCode", value = "创建区域编码")
    private String createAreaCode;

    @ApiModelProperty(name = "createPositionCode", value = "创建职位编码")
    private String createPositionCode;

    @ApiModelProperty(name = "createOrgSysCode", value = "创建机构系统编码")
    private String createOrgSysCode;

    @ApiModelProperty(name = "createSystemCode", value = "创建系统编码")
    private String createSystemCode;

    @ApiModelProperty(name = "createTenantId", value = "创建租户id")
    private String createTenantId;
}