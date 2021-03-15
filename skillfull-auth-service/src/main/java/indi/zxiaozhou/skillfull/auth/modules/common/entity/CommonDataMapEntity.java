// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.common.entity;

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
 * 数据映射表(CommonDataMap)Entity
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:40:07
 * @since JDK1.8
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("auth_common_data_map")
public class CommonDataMapEntity extends BaseEntity {
    private static final long serialVersionUID = -38616152540190173L;

    @TableId
    private String dataMapId;

    @ApiModelProperty(name = "mapOriginalType", value = "映射原类型:1-系统数据，2-区域数据，3-组织数据，4-用户组数据，5-个人数据")
    private Integer mapOriginalType;

    @ApiModelProperty(name = "originalId", value = "映射原类型id")
    private String originalId;

    @ApiModelProperty(name = "targetMapType", value = "映射目标类型:1-系统数据，2-区域数据，3-组织数据，4-用户组数据，5-个人数据")
    private Integer targetMapType;

    @ApiModelProperty(name = "targetId", value = "映射目标类型id")
    private String targetId;

    @ApiModelProperty(name = "operationType", value = "数据操作类型:1.公有话，2.私有化，3.目标映射")
    private Integer operationType;

    @ApiModelProperty(name = "permissionDataRuleMutex", value = "权限添置规则互斥：0.取消填制规则,1. 按原按钮填值规则，默认1")
    private Integer permissionDataRuleMutex;

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