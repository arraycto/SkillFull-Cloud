// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.modules.manage.service.dto;

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
 * 消息发送记录表查询Response
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:57:16
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class ManageSendRecordDto implements Serializable {
    private static final long serialVersionUID = 697975456300918848L;

    @ApiModelProperty(name = "esId", value = "消息id")
    private String esId;

    @ApiModelProperty(name = "esTitle", value = "消息标题")
    private String esTitle;

    @ApiModelProperty(name = "esType", value = "推送方式：1短信 2邮件 3微信")
    private Integer esType;

    @ApiModelProperty(name = "esReceiver", value = "接收人")
    private String esReceiver;

    @ApiModelProperty(name = "esParam", value = "推送所需参数Json格式")
    private String esParam;

    @ApiModelProperty(name = "esContent", value = "推送内容")
    private String esContent;

    @ApiModelProperty(name = "esSendTime", value = "推送时间")
    private LocalDateTime esSendTime;

    @ApiModelProperty(name = "esSendStatus", value = "推送状态 0未推送 1推送成功 2推送失败")
    private String esSendStatus;

    @ApiModelProperty(name = "esSendNum", value = "发送次数 超过5次不再发送")
    private Integer esSendNum;

    @ApiModelProperty(name = "esResult", value = "推送失败原因")
    private String esResult;

    @ApiModelProperty(name = "createAreaCode", value = "创建区域编码")
    private String createAreaCode;

    @ApiModelProperty(name = "createPositionCode", value = "创建职位编码")
    private String createPositionCode;

    @ApiModelProperty(name = "createOrgSysCode", value = "创建机构系统编码")
    private String createOrgSysCode;

    @ApiModelProperty(name = "createSystemCode", value = "创建系统编码")
    private String createSystemCode;

    @ApiModelProperty(name = "createUserId", value = "创建用户id")
    private String createUserId;

    @ApiModelProperty(name = "createUserName", value = "创建用户姓名")
    private String createUserName;

    @ApiModelProperty(name = "createTenantId", value = "创建租户id")
    private String createTenantId;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(name = "updateUserId", value = "更新用户id")
    private String updateUserId;

    @ApiModelProperty(name = "updateUserName", value = "更新用户姓名")
    private String updateUserName;

    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "delFlag", value = "删除状态:0-正常,1-已删除,默认0")
    private Integer delFlag;

}