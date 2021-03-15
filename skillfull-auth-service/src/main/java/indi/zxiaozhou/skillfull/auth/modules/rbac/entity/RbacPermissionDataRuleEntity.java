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
 * 权限数据填值规则表(RbacPermissionDataRule)Entity
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:25:58
 * @since JDK1.8
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("auth_rbac_permission_data_rule")
public class RbacPermissionDataRuleEntity extends BaseEntity {
    private static final long serialVersionUID = 252773593576614992L;

    @TableId
    private String permissionDataRuleId;

    @ApiModelProperty(name = "correlationId", value = "关联业务id:角色id或按钮权限id")
    private String correlationId;

    @ApiModelProperty(name = "ruleName", value = "规则名称")
    private String ruleName;

    @ApiModelProperty(name = "ruleColumn", value = "字段")
    private String ruleColumn;

    @ApiModelProperty(name = "ruleConditions", value = "条件")
    private String ruleConditions;

    @ApiModelProperty(name = "ruleValue", value = "规则值")
    private String ruleValue;

    @ApiModelProperty(name = "ruleStatus", value = "规则状态:0-无效，1-有效。默认0")
    private Integer ruleStatus;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

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