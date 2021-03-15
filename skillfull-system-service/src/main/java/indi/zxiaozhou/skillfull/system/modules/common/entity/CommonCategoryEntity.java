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
 * 分类字典表(CommonCategory)Entity
 *
 * @author zxiaozhou
 * @date 2021-02-12 20:26:08
 * @since JDK1.8
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_common_category")
public class CommonCategoryEntity extends BaseEntity {
    private static final long serialVersionUID = 860571896373764480L;

    @TableId
    private String categoryId;

    @ApiModelProperty(name = "parentId", value = "父级id")
    private String parentId;

    @ApiModelProperty(name = "categoryName", value = "分类名称")
    private String categoryName;

    @ApiModelProperty(name = "categoryCommonCode", value = "分类统一编码")
    private String categoryCommonCode;

    @ApiModelProperty(name = "categoryCode", value = "分类编码")
    private String categoryCode;

    @ApiModelProperty(name = "isParent", value = "是否父节:0-不是，1-是，默认0")
    private Integer isParent;

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

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
}