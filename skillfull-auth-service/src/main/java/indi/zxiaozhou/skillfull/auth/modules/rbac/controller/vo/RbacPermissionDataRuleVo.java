// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo;

import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotBlankOrNull;
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
 * 权限数据填值规则表添加或修改Request
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:41:27
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class RbacPermissionDataRuleVo implements Serializable {
    private static final long serialVersionUID = 528887218332889337L;

    @ApiModelProperty(name = "correlationId", value = "关联业务id:角色id或按钮权限id", required = true)
    @NotBlankOrNull(message = "关联业务id:角色id或按钮权限id不能为空")
    private String correlationId;

    @ApiModelProperty(name = "ruleName", value = "规则名称", required = true)
    @NotBlankOrNull(message = "规则名称不能为空")
    private String ruleName;

    @ApiModelProperty(name = "ruleColumn", value = "字段", required = true)
    @NotBlankOrNull(message = "字段不能为空")
    private String ruleColumn;

    @ApiModelProperty(name = "ruleConditions", value = "条件", required = true)
    @NotBlankOrNull(message = "条件不能为空")
    private String ruleConditions;

    @ApiModelProperty(name = "ruleValue", value = "规则值", required = true)
    @NotBlankOrNull(message = "规则值不能为空")
    private String ruleValue;

    @ApiModelProperty(name = "ruleStatus", value = "规则状态:0-无效，1-有效。默认0", required = true)
    @NotBlankOrNull(message = "规则状态:0-无效，1-有效。默认0不能为空")
    private Integer ruleStatus;

    @ApiModelProperty(name = "remark", value = "备注", required = true)
    @NotBlankOrNull(message = "备注不能为空")
    private String remark;

    @ApiModelProperty(name = "createUserId", value = "创建用户id", required = true)
    @NotBlankOrNull(message = "创建用户id不能为空")
    private String createUserId;

    @ApiModelProperty(name = "createUserName", value = "创建用户姓名", required = true)
    @NotBlankOrNull(message = "创建用户姓名不能为空")
    private String createUserName;

    @ApiModelProperty(name = "createTime", value = "创建时间", required = true)
    @NotBlankOrNull(message = "创建时间不能为空")
    private LocalDateTime createTime;

    @ApiModelProperty(name = "updateUserId", value = "更新用户id", required = true)
    @NotBlankOrNull(message = "更新用户id不能为空")
    private String updateUserId;

    @ApiModelProperty(name = "updateUserName", value = "更新用户姓名", required = true)
    @NotBlankOrNull(message = "更新用户姓名不能为空")
    private String updateUserName;

    @ApiModelProperty(name = "updateTime", value = "更新时间", required = true)
    @NotBlankOrNull(message = "更新时间不能为空")
    private LocalDateTime updateTime;

    @ApiModelProperty(name = "delFlag", value = "删除状态:0-正常,1-已删除,默认0", required = true)
    @NotBlankOrNull(message = "删除状态:0-正常,1-已删除,默认0不能为空")
    private Integer delFlag;

}