// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.modules.manage.controller.vo;

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
 * 消息发送记录表添加或修改Request
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:57:22
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class ManageSendRecordVo implements Serializable {
    private static final long serialVersionUID = -11773879112506232L;

    @ApiModelProperty(name = "esTitle", value = "消息标题", required = true)
    @NotBlankOrNull(message = "消息标题不能为空")
    private String esTitle;

    @ApiModelProperty(name = "esType", value = "推送方式：1短信 2邮件 3微信", required = true)
    @NotBlankOrNull(message = "推送方式：1短信 2邮件 3微信不能为空")
    private Integer esType;

    @ApiModelProperty(name = "esReceiver", value = "接收人", required = true)
    @NotBlankOrNull(message = "接收人不能为空")
    private String esReceiver;

    @ApiModelProperty(name = "esParam", value = "推送所需参数Json格式", required = true)
    @NotBlankOrNull(message = "推送所需参数Json格式不能为空")
    private String esParam;

    @ApiModelProperty(name = "esContent", value = "推送内容", required = true)
    @NotBlankOrNull(message = "推送内容不能为空")
    private String esContent;

    @ApiModelProperty(name = "esSendTime", value = "推送时间", required = true)
    @NotBlankOrNull(message = "推送时间不能为空")
    private LocalDateTime esSendTime;

    @ApiModelProperty(name = "esSendStatus", value = "推送状态 0未推送 1推送成功 2推送失败", required = true)
    @NotBlankOrNull(message = "推送状态 0未推送 1推送成功 2推送失败不能为空")
    private String esSendStatus;

    @ApiModelProperty(name = "esSendNum", value = "发送次数 超过5次不再发送", required = true)
    @NotBlankOrNull(message = "发送次数 超过5次不再发送不能为空")
    private Integer esSendNum;

    @ApiModelProperty(name = "esResult", value = "推送失败原因", required = true)
    @NotBlankOrNull(message = "推送失败原因不能为空")
    private String esResult;

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

    @ApiModelProperty(name = "remark", value = "备注", required = true)
    @NotBlankOrNull(message = "备注不能为空")
    private String remark;

    @ApiModelProperty(name = "delFlag", value = "删除状态:0-正常,1-已删除,默认0", required = true)
    @NotBlankOrNull(message = "删除状态:0-正常,1-已删除,默认0不能为空")
    private Integer delFlag;

}