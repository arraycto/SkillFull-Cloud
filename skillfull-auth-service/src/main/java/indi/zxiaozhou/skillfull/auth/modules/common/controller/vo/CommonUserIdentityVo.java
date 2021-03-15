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
 * 实名信息表添加或修改Request
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:41:05
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class CommonUserIdentityVo implements Serializable {
    private static final long serialVersionUID = 503749592157446756L;

    @ApiModelProperty(name = "userId", value = "用户id", required = true)
    @NotBlankOrNull(message = "用户id不能为空")
    private String userId;

    @ApiModelProperty(name = "realName", value = "真实姓名", required = true)
    @NotBlankOrNull(message = "真实姓名不能为空")
    private String realName;

    @ApiModelProperty(name = "sex", value = "性别:0-默认未知,1-男,2-女,默认0", required = true)
    @NotBlankOrNull(message = "性别:0-默认未知,1-男,2-女,默认0不能为空")
    private Integer sex;

    @ApiModelProperty(name = "nationality", value = "名族", required = true)
    @NotBlankOrNull(message = "名族不能为空")
    private String nationality;

    @ApiModelProperty(name = "idCard", value = "身份证件号码", required = true)
    @NotBlankOrNull(message = "身份证件号码不能为空")
    private String idCard;

    @ApiModelProperty(name = "idCardIssue", value = "身份证件发证机关", required = true)
    @NotBlankOrNull(message = "身份证件发证机关不能为空")
    private String idCardIssue;

    @ApiModelProperty(name = "idCardEffective", value = "身份证书有效期开始", required = true)
    @NotBlankOrNull(message = "身份证书有效期开始不能为空")
    private LocalDateTime idCardEffective;

    @ApiModelProperty(name = "idCardEffectiveEnd", value = "身份证有效期结束", required = true)
    @NotBlankOrNull(message = "身份证有效期结束不能为空")
    private LocalDateTime idCardEffectiveEnd;

    @ApiModelProperty(name = "positivePhoto", value = "正面照", required = true)
    @NotBlankOrNull(message = "正面照不能为空")
    private String positivePhoto;

    @ApiModelProperty(name = "backPhoto", value = "反面照", required = true)
    @NotBlankOrNull(message = "反面照不能为空")
    private String backPhoto;

    @ApiModelProperty(name = "handheldPhoto", value = "证件手持照", required = true)
    @NotBlankOrNull(message = "证件手持照不能为空")
    private String handheldPhoto;

    @ApiModelProperty(name = "identityStatus", value = "实名状态:0-待审核,1-审核中，2-无效(审核失败)，3-有效(审核成功),默认0", required = true)
    @NotBlankOrNull(message = "实名状态:0-待审核,1-审核中，2-无效(审核失败)，3-有效(审核成功),默认0不能为空")
    private Integer identityStatus;

    @ApiModelProperty(name = "auditStartTime", value = "审核开始时间", required = true)
    @NotBlankOrNull(message = "审核开始时间不能为空")
    private LocalDateTime auditStartTime;

    @ApiModelProperty(name = "auditEndTime", value = "审核结束时间", required = true)
    @NotBlankOrNull(message = "审核结束时间不能为空")
    private LocalDateTime auditEndTime;

    @ApiModelProperty(name = "bankCardPositive", value = "银行卡正面", required = true)
    @NotBlankOrNull(message = "银行卡正面不能为空")
    private String bankCardPositive;

    @ApiModelProperty(name = "bankCardBack", value = "银行卡反面", required = true)
    @NotBlankOrNull(message = "银行卡反面不能为空")
    private String bankCardBack;

    @ApiModelProperty(name = "bankCardNum", value = "银行卡号", required = true)
    @NotBlankOrNull(message = "银行卡号不能为空")
    private String bankCardNum;

    @ApiModelProperty(name = "bankReservePhone", value = "银行预留手机号码", required = true)
    @NotBlankOrNull(message = "银行预留手机号码不能为空")
    private String bankReservePhone;

    @ApiModelProperty(name = "belongArea", value = "银行卡归属地", required = true)
    @NotBlankOrNull(message = "银行卡归属地不能为空")
    private String belongArea;

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

    @ApiModelProperty(name = "createTenantId", value = "创建租户id", required = true)
    @NotBlankOrNull(message = "创建租户id不能为空")
    private String createTenantId;

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