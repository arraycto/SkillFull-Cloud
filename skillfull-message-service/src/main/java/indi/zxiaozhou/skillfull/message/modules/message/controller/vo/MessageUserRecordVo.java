// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.modules.message.controller.vo;

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
 * 用户消息记录添加或修改Request
 *
 * @author zxiaozhou
 * @date 2021-01-26 16:48:11
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class MessageUserRecordVo implements Serializable {
    private static final long serialVersionUID = 962689459668963274L;

    @ApiModelProperty(name = "title", value = "标题", required = true)
    @NotBlankOrNull(message = "标题不能为空")
    private String title;

    @ApiModelProperty(name = "msgAbstract", value = "摘要", required = true)
    @NotBlankOrNull(message = "摘要不能为空")
    private String msgAbstract;

    @ApiModelProperty(name = "msgContent", value = "内容", required = true)
    @NotBlankOrNull(message = "内容不能为空")
    private String msgContent;

    @ApiModelProperty(name = "senderUserName", value = "发布人姓名", required = true)
    @NotBlankOrNull(message = "发布人姓名不能为空")
    private String senderUserName;

    @ApiModelProperty(name = "senderUserId", value = "发布人", required = true)
    @NotBlankOrNull(message = "发布人不能为空")
    private String senderUserId;

    @ApiModelProperty(name = "sendTime", value = "发布时间", required = true)
    @NotBlankOrNull(message = "发布时间不能为空")
    private LocalDateTime sendTime;

    @ApiModelProperty(name = "readStatus", value = "阅读状态:0-未阅读,1-阅读。默认0", required = true)
    @NotBlankOrNull(message = "阅读状态:0-未阅读,1-阅读。默认0不能为空")
    private Integer readStatus;

    @ApiModelProperty(name = "userId", value = "消息用户id", required = true)
    @NotBlankOrNull(message = "消息用户id不能为空")
    private String userId;

    @ApiModelProperty(name = "msgType", value = "消息类型:0-系统公告,1-代办事项", required = true)
    @NotBlankOrNull(message = "消息类型:0-系统公告,1-代办事项不能为空")
    private Integer msgType;

    @ApiModelProperty(name = "pageUrl", value = "页面url", required = true)
    @NotBlankOrNull(message = "页面url不能为空")
    private String pageUrl;

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