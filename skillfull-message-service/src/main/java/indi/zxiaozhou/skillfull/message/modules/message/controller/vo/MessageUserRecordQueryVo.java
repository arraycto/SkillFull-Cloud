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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户消息记录条件查询Request
 *
 * @author zxiaozhou
 * @date 2021-01-26 16:48:31
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel
public class MessageUserRecordQueryVo implements Serializable {
    private static final long serialVersionUID = 766984209071961214L;

    @ApiModelProperty(name = "msgId", value = "消息记录id")
    private String msgId;

    @ApiModelProperty(name = "title", value = "标题")
    private String title;

    @ApiModelProperty(name = "msgAbstract", value = "摘要")
    private String msgAbstract;

    @ApiModelProperty(name = "msgContent", value = "内容")
    private String msgContent;

    @ApiModelProperty(name = "senderUserName", value = "发布人姓名")
    private String senderUserName;

    @ApiModelProperty(name = "senderUserId", value = "发布人")
    private String senderUserId;

    @ApiModelProperty(name = "sendTime", value = "发布时间")
    private LocalDateTime sendTime;

    @ApiModelProperty(name = "readStatus", value = "阅读状态:0-未阅读,1-阅读。默认0")
    private Integer readStatus;

    @ApiModelProperty(name = "userId", value = "消息用户id")
    private String userId;

    @ApiModelProperty(name = "msgType", value = "消息类型:0-系统公告,1-代办事项")
    private Integer msgType;

    @ApiModelProperty(name = "pageUrl", value = "页面url")
    private String pageUrl;

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