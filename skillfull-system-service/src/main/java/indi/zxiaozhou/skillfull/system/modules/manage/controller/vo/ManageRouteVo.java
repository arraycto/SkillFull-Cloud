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
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotNullSize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 路由添加或修改Request
 *
 * @author zxiaozhou
 * @date 2020-09-12 16:04:47
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class ManageRouteVo implements Serializable {
    private static final long serialVersionUID = -22693036079181214L;

    @ApiModelProperty(name = "routeCode", value = "路由编码(唯一)", required = true)
    @NotBlankOrNull(message = "路由编码(唯一)不能为空")
    private String routeCode;

    @ApiModelProperty(name = "routeName", value = "路由名称", required = true)
    @NotBlankOrNull(message = "路由名称不能为空")
    private String routeName;

    @ApiModelProperty(name = "serviceId", value = "服务id", required = true)
    @NotBlankOrNull(message = "服务id不能为空")
    private String serviceId;

    @ApiModelProperty(name = "routeState", value = "路由状态:0-禁用,1-启用。默认0", required = true)
    @NotBlankOrNull(message = "路由状态不能为空")
    @Min(value = 0, message = "路由状态只能为0、1")
    @Max(value = 1, message = "路由状态只能为0、1")
    private Integer routeState;

    @ApiModelProperty(name = "url", value = "路由url地址,当选择非负载均衡器时必填")
    private String url;

    @ApiModelProperty(name = "isLoadBalancer", value = "是否负载均衡器:0-不是,1-是，默认0。选择均衡器时服务名必填，url不填", required = true)
    @NotBlankOrNull(message = "是否负载均衡器:0-不是,1-是，默认0。选择均衡器时服务名必填，url不填不能为空")
    private Integer isLoadBalancer;

    @ApiModelProperty(name = "loadBalancerType", value = "负载均衡器类型:0-普通,1-ws,2-wss,当选的负载均衡器时必填")
    private Integer loadBalancerType;

    @ApiModelProperty(name = "serviceName", value = "服务名,当选择负载均衡器时使用必填")
    private String serviceName;

    @ApiModelProperty(name = "metadata", value = "路由元数据")
    private Map<String, Object> metadata;

    @ApiModelProperty(name = "routeOrder", value = "路由排序,越小越靠前，默认0")
    private Integer routeOrder = 0;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "routePredicates", value = "路由断言", required = true)
    @NotNullSize(message = "路由断言不能为空")
    @Valid
    private List<ManageRoutePredicate> routePredicates;

    @ApiModelProperty(name = "routeFilters", value = "路由过滤器")
    @Valid
    private List<ManageRouteFilter> routeFilters;


    @Data
    public static class ManageRoutePredicate implements Serializable {
        private static final long serialVersionUID = 409866466459197284L;

        @ApiModelProperty(name = "predicateType", value = "断言类型", required = true)
        @NotBlankOrNull(message = "断言类型不能为空")
        private String predicateType;

        @ApiModelProperty(name = "remark", value = "备注")
        private String remark;

        @ApiModelProperty(name = "rules", value = "断言规则", required = true)
        @NotNullSize(message = "断言规则不能为空")
        @Valid
        private Set<Rule> rules;
    }


    @Data
    public static class ManageRouteFilter implements Serializable {
        private static final long serialVersionUID = 849919338654780669L;

        @ApiModelProperty(name = "filterType", value = "过滤器类型", required = true)
        @NotBlankOrNull(message = "过滤器类型不能为空")
        private String filterType;

        @ApiModelProperty(name = "rules", value = "过滤器规则", required = true)
        @NotNullSize(message = "过滤器规则不能为空")
        @Valid
        private Set<Rule> rules;

        @ApiModelProperty(name = "remark", value = "备注")
        private String remark;
    }


    @Data
    @EqualsAndHashCode
    public static class Rule implements Serializable {
        private static final long serialVersionUID = -5627914053223267340L;

        @ApiModelProperty(name = "ruleName", value = "规则名称", required = true)
        @NotBlankOrNull(message = "规则名称不能为空")
        private String ruleName;

        @ApiModelProperty(name = "ruleValue", value = "规则值", required = true)
        @NotBlankOrNull(message = "规则值不能为空")
        private String ruleValue;
    }
}