// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.manage.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 服务管理条件查询Request
 *
 * @author zxiaozhou
 * @date 2020-09-13 17:50:24
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel
public class ManageServiceQueryVo implements Serializable {
    private static final long serialVersionUID = 898859732445055460L;

    @ApiModelProperty(name = "serviceId", value = "路由id")
    private String serviceId;

    @ApiModelProperty(name = "serviceName", value = "服务名,与路由表服务名自动关联，两边数据没有先后顺序")
    private String serviceName;

    @ApiModelProperty(name = "subscribeChange", value = "是否监听服务变化:0-不订阅,1-订阅,默认0")
    private Integer subscribeChange;

    @ApiModelProperty(name = "noticeChange", value = "是否发送变化通知:0-不通知,1-通知。默认0")
    private Integer noticeChange;

    @ApiModelProperty(name = "noticeType", value = "通知类型:0-邮件,1-短信,2-微信消息，当选择监听服务变化并且通知时必填")
    private Integer noticeType;

    @ApiModelProperty(name = "noticeTemplateId", value = "通知模板id，当选择监听服务变化并且通知时必填")
    private String noticeTemplateId;

    @ApiModelProperty(name = "headUserName", value = "负责人姓名，当选择监听服务变化并且通知时必填")
    private String headUserName;

    @ApiModelProperty(name = "headUserId", value = "负责人用户id，当选择监听服务变化并且通知时必填")
    private String headUserId;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "uniqueHelp", value = "唯一索引帮助字段,默认1，如果删除该值为主键")
    private String uniqueHelp;

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