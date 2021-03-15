// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.gateway.modules.manage.controller.vo;


import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotBlankOrNull;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotNullSize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 路由入参
 *
 * @author zxiaozhou
 * @date 2020-09-10 22:43
 * @since JDK11
 */
@Data
public class GatewayRouteVo implements Serializable {
    private static final long serialVersionUID = -5358228979136756020L;

    @ApiModelProperty(name = "routeCode", value = "路由编码(唯一)", required = true)
    @NotBlankOrNull(message = "路由编码(唯一)不能为空")
    private String routeCode;

    @ApiModelProperty(name = "url", value = "路由url地址,当选择非负载均衡器时必填")
    private String url;

    @ApiModelProperty(name = "isLoadBalancer", value = "是否负载均衡器:0-不是,1-是，默认0。选择均衡器时服务名必填，url不填", required = true)
    @NotBlankOrNull(message = "请确定是否负载均衡器")
    private Integer isLoadBalancer = 0;

    @ApiModelProperty(name = "loadBalancerType", value = "负载均衡器类型:0-普通,1-ws,2-wss,当选的负载均衡器时必填")
    private Integer loadBalancerType;

    @ApiModelProperty(name = "serviceName", value = "服务名,当选择负载均衡器时使用必填")
    private String serviceName;

    @ApiModelProperty(name = "routeOrder", value = "路由排序,越小越靠前，默认0")
    private Integer routeOrder = 0;

    @ApiModelProperty(name = "metadata", value = "路由元数据")
    private Map<String, Object> metadata;

    @ApiModelProperty(name = "routePredicates", value = "路由断言", required = true)
    @NotNullSize(message = "路由断言不能为空")
    @Valid
    private List<RoutePredicate> routePredicates;

    @ApiModelProperty(name = "routeFilters", value = "路由过滤器")
    @Valid
    private List<RouteFilter> routeFilters;

    @Data
    public static class RoutePredicate implements Serializable {
        private static final long serialVersionUID = -67116125178064715L;

        @ApiModelProperty(name = "predicateType", value = "断言类型", required = true)
        @NotBlankOrNull(message = "断言类型不能为空")
        private String predicateType;

        @ApiModelProperty(name = "rules", value = "断言规则", required = true)
        @NotNullSize(message = "断言规则不能为空")
        @Valid
        private Set<Rule> rules;
    }


    @Data
    public static class RouteFilter implements Serializable {
        private static final long serialVersionUID = 5625992009673170739L;

        @ApiModelProperty(name = "filterType", value = "过滤器类型", required = true)
        @NotBlankOrNull(message = "过滤器类型不能为空")
        private String filterType;

        @ApiModelProperty(name = "rules", value = "过滤器规则", required = true)
        @NotNullSize(message = "过滤器规则不能为空")
        @Valid
        private Set<Rule> rules;
    }


    @Data
    @EqualsAndHashCode
    public static class Rule {
        @ApiModelProperty(name = "ruleName", value = "规则名称", required = true)
        @NotBlankOrNull(message = "规则名称不能为空")
        private String ruleName;

        @ApiModelProperty(name = "ruleValue", value = "规则值", required = true)
        @NotBlankOrNull(message = "规则值不能为空")
        private String ruleValue;
    }
}
