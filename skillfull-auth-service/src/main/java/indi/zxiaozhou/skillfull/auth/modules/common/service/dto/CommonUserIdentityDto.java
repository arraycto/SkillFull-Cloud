// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.common.service.dto;

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
 * 实名信息表查询Response
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:40:51
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class CommonUserIdentityDto implements Serializable {
    private static final long serialVersionUID = -79207906579286095L;

    @ApiModelProperty(name = "identityId", value = "实名信息id")
    private String identityId;

    @ApiModelProperty(name = "userId", value = "用户id")
    private String userId;

    @ApiModelProperty(name = "realName", value = "真实姓名")
    private String realName;

    @ApiModelProperty(name = "sex", value = "性别:0-默认未知,1-男,2-女,默认0")
    private Integer sex;

    @ApiModelProperty(name = "nationality", value = "名族")
    private String nationality;

    @ApiModelProperty(name = "idCard", value = "身份证件号码")
    private String idCard;

    @ApiModelProperty(name = "idCardIssue", value = "身份证件发证机关")
    private String idCardIssue;

    @ApiModelProperty(name = "idCardEffective", value = "身份证书有效期开始")
    private LocalDateTime idCardEffective;

    @ApiModelProperty(name = "idCardEffectiveEnd", value = "身份证有效期结束")
    private LocalDateTime idCardEffectiveEnd;

    @ApiModelProperty(name = "positivePhoto", value = "正面照")
    private String positivePhoto;

    @ApiModelProperty(name = "backPhoto", value = "反面照")
    private String backPhoto;

    @ApiModelProperty(name = "handheldPhoto", value = "证件手持照")
    private String handheldPhoto;

    @ApiModelProperty(name = "identityStatus", value = "实名状态:0-待审核,1-审核中，2-无效(审核失败)，3-有效(审核成功),默认0")
    private Integer identityStatus;

    @ApiModelProperty(name = "auditStartTime", value = "审核开始时间")
    private LocalDateTime auditStartTime;

    @ApiModelProperty(name = "auditEndTime", value = "审核结束时间")
    private LocalDateTime auditEndTime;

    @ApiModelProperty(name = "bankCardPositive", value = "银行卡正面")
    private String bankCardPositive;

    @ApiModelProperty(name = "bankCardBack", value = "银行卡反面")
    private String bankCardBack;

    @ApiModelProperty(name = "bankCardNum", value = "银行卡号")
    private String bankCardNum;

    @ApiModelProperty(name = "bankReservePhone", value = "银行预留手机号码")
    private String bankReservePhone;

    @ApiModelProperty(name = "belongArea", value = "银行卡归属地")
    private String belongArea;

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