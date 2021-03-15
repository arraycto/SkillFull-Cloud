// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.common.controller.vo;

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
 * 用户-代理人表添加或修改Request
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:40:30
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class CommonUserAgentVo implements Serializable {
    private static final long serialVersionUID = 870985150890654456L;

    @ApiModelProperty(name = "userId", value = "用户名id", required = true)
    @NotBlankOrNull(message = "用户名id不能为空")
    private String userId;

    @ApiModelProperty(name = "agentUserId", value = "代理人用户id", required = true)
    @NotBlankOrNull(message = "代理人用户id不能为空")
    private String agentUserId;

    @ApiModelProperty(name = "agentStartTime", value = "代理开始时间", required = true)
    @NotBlankOrNull(message = "代理开始时间不能为空")
    private LocalDateTime agentStartTime;

    @ApiModelProperty(name = "agentEndTime", value = "代理结束时间", required = true)
    @NotBlankOrNull(message = "代理结束时间不能为空")
    private LocalDateTime agentEndTime;

    @ApiModelProperty(name = "agentStatus", value = "状态：0-无效，1-有效", required = true)
    @NotBlankOrNull(message = "状态：0-无效，1-有效不能为空")
    private String agentStatus;

    @ApiModelProperty(name = "uniqueHelp", value = "唯一索引帮助字段,默认1，如果删除该值为主键", required = true)
    @NotBlankOrNull(message = "唯一索引帮助字段,默认1，如果删除该值为主键不能为空")
    private String uniqueHelp;

    @ApiModelProperty(name = "remark", value = "备注", required = true)
    @NotBlankOrNull(message = "备注不能为空")
    private String remark;

    @ApiModelProperty(name = "createAreaCode", value = "创建区域编码", required = true)
    @NotBlankOrNull(message = "创建区域编码不能为空")
    private String createAreaCode;

    @ApiModelProperty(name = "createPositionCode", value = "创建职位编码", required = true)
    @NotBlankOrNull(message = "创建职位编码不能为空")
    private String createPositionCode;

    @ApiModelProperty(name = "createOrgSysCode", value = "创建机构系统编码", required = true)
    @NotBlankOrNull(message = "创建机构系统编码不能为空")
    private String createOrgSysCode;

    @ApiModelProperty(name = "createSystemCode", value = "创建系统编码", required = true)
    @NotBlankOrNull(message = "创建系统编码不能为空")
    private String createSystemCode;

    @ApiModelProperty(name = "createUserId", value = "创建用户id", required = true)
    @NotBlankOrNull(message = "创建用户id不能为空")
    private String createUserId;

    @ApiModelProperty(name = "createUserName", value = "创建用户姓名", required = true)
    @NotBlankOrNull(message = "创建用户姓名不能为空")
    private String createUserName;

    @ApiModelProperty(name = "createTenantId", value = "创建租户id", required = true)
    @NotBlankOrNull(message = "创建租户id不能为空")
    private String createTenantId;

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