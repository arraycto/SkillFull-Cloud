// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.modules.message.entity;

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
 * 用户消息记录(MessageUserRecord)Entity
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:54:04
 * @since JDK1.8
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("msg_message_user_record")
public class MessageUserRecordEntity extends BaseEntity {
    private static final long serialVersionUID = 579244319225918329L;

    @TableId
    private String msgId;

    @ApiModelProperty(name = "title", value = "标题")
    private String title;

    @ApiModelProperty(name = "msgAbstract", value = "摘要")
    private String msgAbstract;

    @ApiModelProperty(name = "msgContent", value = "内容")
    private String msgContent;

    @ApiModelProperty(name = "busiState", value = "三方业务状态")
    private Integer busiState;

    @ApiModelProperty(name = "busiId", value = "三方业务id")
    private String busiId;

    @ApiModelProperty(name = "iconType", value = "图标类型：0-系统自带,1-自定义")
    private Integer iconType;

    @ApiModelProperty(name = "icon", value = "内容")
    private String icon;

    @ApiModelProperty(name = "senderUserName", value = "发布人姓名")
    private String senderUserName;

    @ApiModelProperty(name = "senderUserId", value = "发布人")
    private String senderUserId;

    @ApiModelProperty(name = "otherInfo", value = "三方业务其他信息")
    private String otherInfo;

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

    @ApiModelProperty(name = "createTenantId", value = "创建租户id")
    private String createTenantId;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
}