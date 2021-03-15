// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 权限数据填值规则表查询Response
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:41:27
 * @since JDK11
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class RbacPermissionDataRuleDto implements Serializable {
    private static final long serialVersionUID = -32211465525119130L;

    @ApiModelProperty(name = "permissionDataRuleId", value = "填值规则id")
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

    @ApiModelProperty(name = "createUserId", value = "创建用户id")
    private String createUserId;

    @ApiModelProperty(name = "createUserName", value = "创建用户姓名")
    private String createUserName;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(name = "updateUserId", value = "更新用户id")
    private String updateUserId;

    @ApiModelProperty(name = "updateUserName", value = "更新用户姓名")
    private String updateUserName;

    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(name = "delFlag", value = "删除状态:0-正常,1-已删除,默认0")
    private Integer delFlag;

}