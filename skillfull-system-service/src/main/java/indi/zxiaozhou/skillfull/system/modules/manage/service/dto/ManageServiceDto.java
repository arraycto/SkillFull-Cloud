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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 服务管理查询Response
 *
 * @author zxiaozhou
 * @date 2020-09-13 17:50:23
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class ManageServiceDto implements Serializable {
    private static final long serialVersionUID = -87795593004622078L;

    @ApiModelProperty(name = "serviceId", value = "路由id")
    private String serviceId;

    @ApiModelProperty(name = "serviceName", value = "服务名,与路由表服务名自动关联，两边数据没有先后顺序")
    private String serviceName;

    @ApiModelProperty(name = "isLoadBalancer", value = "是否负载均衡器:0-不是,1-是，默认0。选择均衡器时监听信息才可以使用")
    private Integer isLoadBalancer;

    @ApiModelProperty(name = "swaggerTag", value = "swagger tag显示名称")
    private String swaggerTag;

    @ApiModelProperty(name = "swaggerDocUri", value = "swagger doc uri,即凭借上网关ip信息就能访问的uri")
    private String swaggerDocUri;

    @ApiModelProperty(name = "serviceState", value = "服务状态:0-禁用,1-启用。默认0")
    private Integer serviceState;

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

    @ApiModelProperty(name = "serviceInstanceDto", value = "服务实例信息")
    private ServiceInstanceDto serviceInstanceDto;

    @ApiModelProperty(name = "routeList", value = "路由详细信息")
    private List<ManageRouteDto> routeList;
}