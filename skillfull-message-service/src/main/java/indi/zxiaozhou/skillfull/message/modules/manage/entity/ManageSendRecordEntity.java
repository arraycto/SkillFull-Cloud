// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.modules.manage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.zxiaozhou.skillfull.message.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 消息发送记录表(ManageSendRecord)Entity
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:57:20
 * @since JDK1.8
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("msg_manage_send_record")
public class ManageSendRecordEntity extends BaseEntity {
    private static final long serialVersionUID = -45113290965189282L;

    @TableId
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

    @ApiModelProperty(name = "createTenantId", value = "创建租户id")
    private String createTenantId;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
}