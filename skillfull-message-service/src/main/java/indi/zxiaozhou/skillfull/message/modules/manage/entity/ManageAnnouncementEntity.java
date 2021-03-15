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
 * 系统通告管理(ManageAnnouncement)Entity
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:56:55
 * @since JDK1.8
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("msg_manage_announcement")
public class ManageAnnouncementEntity extends BaseEntity {
    private static final long serialVersionUID = -88093429293335345L;

    @TableId
    private String anntId;

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

    @ApiModelProperty(name = "receiveType", value = "接收对象类型：1-指定用户,2-指定区域,3-指定组织,4-指定角色,5-所有")
    private Integer receiveType;

    @ApiModelProperty(name = "receiveInfos", value = "接收对象")
    private String receiveInfos;

    @ApiModelProperty(name = "sendType", value = "发布方式：0-手动,1-自动")
    private Integer sendType;

    @ApiModelProperty(name = "finalySendTime", value = "最终发布时间")
    private LocalDateTime finalySendTime;

    @ApiModelProperty(name = "sendTime", value = "发布时间")
    private LocalDateTime sendTime;

    @ApiModelProperty(name = "cancelTime", value = "撤销时间")
    private LocalDateTime cancelTime;

    @ApiModelProperty(name = "sendStatus", value = "发布状态：0未发布，1已发布，2已撤销，默认0")
    private Integer sendStatus;

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