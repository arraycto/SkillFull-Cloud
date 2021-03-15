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
 * 消息模板添加或修改Request
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:57:53
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class ManageTemplateVo implements Serializable {
    private static final long serialVersionUID = -44292257755849837L;

    @ApiModelProperty(name = "templateName", value = "模板名称", required = true)
    @NotBlankOrNull(message = "模板名称不能为空")
    private String templateName;

    @ApiModelProperty(name = "templateCode", value = "模板code", required = true)
    @NotBlankOrNull(message = "模板code不能为空")
    private String templateCode;

    @ApiModelProperty(name = "templateType", value = "模板类型", required = true)
    @NotBlankOrNull(message = "模板类型不能为空")
    private String templateType;

    @ApiModelProperty(name = "templateContent", value = "模板内容", required = true)
    @NotBlankOrNull(message = "模板内容不能为空")
    private String templateContent;

    @ApiModelProperty(name = "templateTestJson", value = "模板测试json", required = true)
    @NotBlankOrNull(message = "模板测试json不能为空")
    private String templateTestJson;

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