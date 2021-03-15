// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.manage.service.dto;

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
 * 服务管理分页查询Response
 *
 * @author zxiaozhou
 * @date 2020-09-13 17:50:23
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class ManageServicePageDto implements Serializable {
    private static final long serialVersionUID = 909501226969316307L;

    @ApiModelProperty(name = "serviceId", value = "路由id")
    private String serviceId;

    @ApiModelProperty(name = "serviceName", value = "服务名,与路由表服务名自动关联，两边数据没有先后顺序")
    private String serviceName;

    @ApiModelProperty(name = "serviceState", value = "服务状态:0-禁用,1-启用。默认0")
    private Integer serviceState;

    @ApiModelProperty(name = "subscribeChange", value = "是否监听服务变化:0-不订阅,1-订阅,默认0")
    private Integer subscribeChange;

    @ApiModelProperty(name = "swaggerDocUri", value = "swagger doc uri,即凭借上网关ip信息就能访问的uri")
    private String swaggerDocUri;

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

    @ApiModelProperty(name = "createTime", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(name = "instanceNum", value = "实例总数")
    private int instanceNum = 0;

    @ApiModelProperty(name = "healthyNum", value = "健康实例数量")
    private int healthyNum = 0;

    @ApiModelProperty(name = "unhealthyNum", value = "不健康数量")
    private int unhealthyNum = 0;
}