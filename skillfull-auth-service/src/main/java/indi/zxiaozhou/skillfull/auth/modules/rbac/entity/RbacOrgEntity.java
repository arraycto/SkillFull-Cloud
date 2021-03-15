// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.rbac.entity;

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
 * 组织表(RbacOrg)Entity
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:25:16
 * @since JDK1.8
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("auth_rbac_org")
public class RbacOrgEntity extends BaseEntity {
    private static final long serialVersionUID = -92219863066522144L;

    @TableId
    private String orgId;

    @ApiModelProperty(name = "parentId", value = "父级组织id")
    private String parentId;

    @ApiModelProperty(name = "orgName", value = "组织名称")
    private String orgName;

    @ApiModelProperty(name = "orgNameEn", value = "英文名")
    private String orgNameEn;

    @ApiModelProperty(name = "orgNameAbbr", value = "缩写")
    private String orgNameAbbr;

    @ApiModelProperty(name = "orgOrder", value = "排序")
    private Integer orgOrder;

    @ApiModelProperty(name = "orgType", value = "组织机构类型：1-公司,2-部门")
    private Integer orgType;

    @ApiModelProperty(name = "orgCode", value = "组织编码")
    private String orgCode;

    @ApiModelProperty(name = "orgSysCode", value = "组织编码(系统)")
    private String orgSysCode;

    @ApiModelProperty(name = "orgStatus", value = "组织状态：0-无效，1-有效，默认0")
    private Integer orgStatus;

    @ApiModelProperty(name = "autoBind", value = "绑定方式:0-手动,1-自动。默认0")
    private Integer autoBind;

    @ApiModelProperty(name = "phone", value = "手机号")
    private String phone;

    @ApiModelProperty(name = "fax", value = "传真")
    private String fax;

    @ApiModelProperty(name = "address", value = "地址")
    private String address;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "uniqueHelp", value = "唯一索引帮助字段,默认1，如果删除该值为主键")
    private String uniqueHelp;

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