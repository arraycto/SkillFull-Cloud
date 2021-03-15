// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.modules.message.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * 用户消息记录分页查询Response
 *
 * @author zxiaozhou
 * @date 2021-01-26 16:48:25
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class MessageUserRecordPageDto implements Serializable {
    private static final long serialVersionUID = 613215260014507143L;

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

    @ApiModelProperty(name = "otherInfo", value = "三方业务其他信息")
    private String otherInfo;

    @ApiModelProperty(name = "busiState", value = "三方业务状态")
    private Integer busiState;

    @ApiModelProperty(name = "busiId", value = "三方业务id")
    private String busiId;

    @ApiModelProperty(name = "iconType", value = "图标类型：0-系统自带,1-自定义")
    private Integer iconType;

    @ApiModelProperty(name = "icon", value = "内容")
    private String icon;

    @ApiModelProperty(name = "sendTime", value = "发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime sendTime;

    @ApiModelProperty(name = "readStatus", value = "阅读状态:0-未阅读,1-阅读。默认0")
    private Integer readStatus;

    @ApiModelProperty(name = "userId", value = "消息用户id")
    private String userId;

    @ApiModelProperty(name = "msgType", value = "消息类型:0-系统公告,1-代办事项")
    private Integer msgType;

    @ApiModelProperty(name = "pageUrl", value = "页面url")
    private String pageUrl;

    @ApiModelProperty(name = "createUserName", value = "创建用户姓名")
    private String createUserName;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
}