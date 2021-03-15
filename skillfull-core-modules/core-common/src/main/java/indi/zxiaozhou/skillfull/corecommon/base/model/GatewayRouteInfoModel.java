// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.corecommon.base.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 动态路由信息
 *
 * @author zxiaozhou
 * @date 2020-09-12 16:04:47
 * @since JDK11
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class GatewayRouteInfoModel implements Serializable {
    private static final long serialVersionUID = 550568918804547444L;

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

    @ApiModelProperty(name = "metadata", value = "路由元数据")
    private Map<String, Object> metadata;

    @ApiModelProperty(name = "loadBalancerType", value = "负载均衡器类型:0-普通,1-ws,2-wss,当选的负载均衡器时必填")
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

    @ApiModelProperty(name = "routePredicates", value = "路由断言")
    private List<RoutePredicate> routePredicates = Collections.emptyList();

    @ApiModelProperty(name = "routeFilters", value = "路由过滤器")
    private List<RouteFilter> routeFilters = Collections.emptyList();


    @Data
    public static class RoutePredicate implements Serializable {
        private static final long serialVersionUID = 409866466459197284L;

        @ApiModelProperty(name = "predicateType", value = "断言类型")
        private String predicateType;

        @ApiModelProperty(name = "remark", value = "备注")
        private String remark;

        @ApiModelProperty(name = "rules", value = "断言规则")
        private Set<Rule> rules = Collections.emptySet();
    }


    @Data
    public static class RouteFilter implements Serializable {
        private static final long serialVersionUID = 849919338654780669L;

        @ApiModelProperty(name = "filterType", value = "过滤器类型")
        private String filterType;

        @ApiModelProperty(name = "rules", value = "过滤器规则")
        private Set<Rule> rules = Collections.emptySet();

        @ApiModelProperty(name = "remark", value = "备注")
        private String remark;
    }


    @Data
    @EqualsAndHashCode
    public static class Rule implements Serializable {
        private static final long serialVersionUID = -5627914053223267340L;

        @ApiModelProperty(name = "ruleName", value = "规则名称")
        private String ruleName;

        @ApiModelProperty(name = "ruleValue", value = "规则值")
        private String ruleValue;
    }

}