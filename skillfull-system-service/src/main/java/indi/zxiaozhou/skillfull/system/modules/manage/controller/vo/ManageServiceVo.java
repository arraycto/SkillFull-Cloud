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


import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotBlankOrNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 服务管理添加或修改Request
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
public class ManageServiceVo implements Serializable {
    private static final long serialVersionUID = 953586953931623600L;

    @ApiModelProperty(name = "serviceName", value = "服务名,与路由表服务名自动关联，两边数据没有先后顺序", required = true)
    @NotBlankOrNull(message = "服务名不能为空")
    private String serviceName;

    @ApiModelProperty(name = "isLoadBalancer", value = "是否负载均衡器:0-不是,1-时，默认0。选择均衡器时服务名必填，url不填", required = true)
    @NotBlankOrNull(message = "服务是否负载均衡不能为空")
    private Integer isLoadBalancer;

    @ApiModelProperty(name = "subscribeChange", value = "是否监听服务变化:0-不订阅,1-订阅,默认0")
    private Integer subscribeChange = 0;

    @ApiModelProperty(name = "serviceState", value = "服务状态:0-禁用,1-启用。默认0")
    private Integer serviceState;

    @ApiModelProperty(name = "noticeChange", value = "是否发送变化通知:0-不通知,1-通知。默认0")
    private Integer noticeChange = 0;

    @ApiModelProperty(name = "swaggerTag", value = "swagger tag显示名称")
    private String swaggerTag;

    @ApiModelProperty(name = "swaggerDocUri", value = "swagger doc uri,即凭借上网关ip信息就能访问的uri")
    private String swaggerDocUri;

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
}