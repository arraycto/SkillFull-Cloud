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
 * 路由分页查询Response
 *
 * @author zxiaozhou
 * @date 2020-09-12 16:33:36
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class ManageRoutePageDto implements Serializable {
    private static final long serialVersionUID = -97942354487339043L;

    @ApiModelProperty(name = "routeId", value = "路由id")
    private String routeId;

    @ApiModelProperty(name = "routeCode", value = "路由编码(唯一)")
    private String routeCode;

    @ApiModelProperty(name = "routeName", value = "路由名称")
    private String routeName;

    @ApiModelProperty(name = "url", value = "路由url地址,当选择非负载均衡器时必填")
    private String url;

    @ApiModelProperty(name = "isLoadBalancer", value = "是否负载均衡器:0-不是,1-是，默认0。选择均衡器时服务名必填，url不填")
    private Integer isLoadBalancer;

    @ApiModelProperty(name = "loadBalancerType", value = "负载均衡器类型:0-普通,1-ws,2-wss")
    private Integer loadBalancerType;

    @ApiModelProperty(name = "serviceName", value = "服务名,当选择负载均衡器时使用必填")
    private String serviceName;

    @ApiModelProperty(name = "routeOrder", value = "路由排序,越小越靠前，默认0")
    private Integer routeOrder;

    @ApiModelProperty(name = "routeState", value = "路由状态:0-禁用,1-启用。默认0")
    private Integer routeState;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "createUserName", value = "创建用户姓名")
    private String createUserName;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime createTime;

}