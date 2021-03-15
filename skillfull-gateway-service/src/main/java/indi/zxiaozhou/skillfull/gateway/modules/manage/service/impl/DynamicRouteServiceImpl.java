// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.gateway.modules.manage.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import indi.zxiaozhou.skillfull.corecommon.base.model.GatewayRouteInfoModel;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import indi.zxiaozhou.skillfull.gateway.core.constant.CacheConstant;
import indi.zxiaozhou.skillfull.gateway.core.constant.typeimpl.FilterType;
import indi.zxiaozhou.skillfull.gateway.core.constant.typeimpl.LbType;
import indi.zxiaozhou.skillfull.gateway.core.constant.typeimpl.PredicateType;
import indi.zxiaozhou.skillfull.gateway.core.constant.typeimpl.ProtocolType;
import indi.zxiaozhou.skillfull.gateway.modules.manage.controller.vo.GatewayRouteVo;
import indi.zxiaozhou.skillfull.gateway.modules.manage.service.IDynamicRouteService;
import indi.zxiaozhou.skillfull.gateway.modules.manage.service.dto.GatewayRouteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.*;

import static indi.zxiaozhou.skillfull.gateway.core.constant.CommonConstant.LB_PREFIX;

/**
 * 动态路由服务
 *
 * @author zxiaozhou
 * @date 2020-09-10 21:10
 * @since JDK11
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DynamicRouteServiceImpl implements IDynamicRouteService {
    private final RouteDefinitionWriter routeDefinitionWriter;
    private final ApplicationEventPublisher publisher;
    private final RouteDefinitionLocator routeDefinitionLocator;
    private final RedisTemplate<String, Object> redisTemplate;


    @Override
    public void addRoute(GatewayRouteInfoModel vo, boolean isRefresh) throws RuntimeException {
        RouteDefinition definition = getRouteDefinition(vo);
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            if (isRefresh) {
                notifyChanged();
            }
        } catch (Exception e) {
            throw new ResponseException("添加路由失败");
        }
    }


    @Override
    public void updateRoute(GatewayRouteInfoModel vo, boolean isRefresh) throws RuntimeException {
        RouteDefinition definition = getRouteDefinition(vo);
        try {
            deleteRoute(definition.getId(), false);
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            if (isRefresh) {
                notifyChanged();
            }
        } catch (Exception e) {
            throw new ResponseException("更新路由失败");
        }
    }


    @Override
    public void deleteRoute(String routeId, boolean isRefresh) throws RuntimeException {
        try {
            routeDefinitionWriter.delete(Mono.just(routeId)).subscribe();
            if (isRefresh) {
                notifyChanged();
            }
        } catch (Exception e) {
            throw new ResponseException("删除失败,服务可能不存在");
        }
    }


    @Override
    public Flux<GatewayRouteDto> getRoutes() throws RuntimeException {
        return routeDefinitionLocator.getRouteDefinitions().map(v -> {
            // 处理基础部分
            GatewayRouteDto dto = new GatewayRouteDto();
            dto.setRouteCode(v.getId());
            dto.setRouteOrder(v.getOrder());
            dto.setMetadata(v.getMetadata());
            String url = v.getUri().toString().toLowerCase();
            dto.setIsLoadBalancer(0);
            if (url.startsWith(LB_PREFIX)) {
                dto.setIsLoadBalancer(1);
                LbType startMatch = LbType.getStartMatch(url);
                if (Objects.nonNull(startMatch)) {
                    dto.setLoadBalancerType(startMatch.getType());
                    dto.setServiceName(url.replace(startMatch.getTypeName(), ""));
                }
            } else {
                dto.setUrl(url);
            }
            // 处理断言
            List<GatewayRouteDto.RoutePredicate> routePredicates = new ArrayList<>();
            List<PredicateDefinition> predicates = v.getPredicates();
            if (!CollectionUtils.isEmpty(predicates)) {
                predicates.forEach(sv -> {
                    GatewayRouteDto.RoutePredicate predicate = new GatewayRouteDto.RoutePredicate();
                    predicate.setPredicateType(sv.getName());
                    Set<GatewayRouteDto.Rule> rules = new HashSet<>();
                    Map<String, String> args = sv.getArgs();
                    if (!CollectionUtils.isEmpty(args)) {
                        args.forEach((k, ssv) -> {
                            GatewayRouteDto.Rule rule = new GatewayRouteDto.Rule();
                            rule.setRuleName(k);
                            rule.setRuleValue(ssv);
                            rules.add(rule);
                        });
                    }
                    predicate.setRules(rules);
                    routePredicates.add(predicate);
                });
                dto.setRoutePredicates(routePredicates);
            }
            // 处理过滤器
            List<GatewayRouteDto.RouteFilter> routeFilters = new ArrayList<>();
            List<FilterDefinition> filters = v.getFilters();
            if (!CollectionUtils.isEmpty(filters)) {
                filters.forEach(sv -> {
                    GatewayRouteDto.RouteFilter filter = new GatewayRouteDto.RouteFilter();
                    filter.setFilterType(sv.getName());
                    Set<GatewayRouteDto.Rule> rules = new HashSet<>();
                    Map<String, String> args = sv.getArgs();
                    if (!CollectionUtils.isEmpty(args)) {
                        args.forEach((k, ssv) -> {
                            GatewayRouteDto.Rule rule = new GatewayRouteDto.Rule();
                            rule.setRuleValue(ssv);
                            rule.setRuleName(k);
                            rules.add(rule);
                        });
                    }
                    filter.setRules(rules);
                    routeFilters.add(filter);
                });
                dto.setRouteFilters(routeFilters);
            }
            return dto;
        });
    }


    @Override
    public void loadRoute() throws RuntimeException {
        // 加载历史路由并删除所有
        Flux<RouteDefinition> routeDefinitions = routeDefinitionLocator.getRouteDefinitions();
        if (Objects.nonNull(routeDefinitions)) {
            routeDefinitions.collectList().subscribe(v -> v.forEach(sv -> deleteRoute(sv.getId(), false)));
            this.notifyChanged();
            // 加载新路由
            @SuppressWarnings("unchecked")
            List<GatewayRouteInfoModel> routeObjectInfos = (List<GatewayRouteInfoModel>) redisTemplate.opsForValue().get(CacheConstant.ROUTE_INFO_CACHE);
            if (CollectionUtil.isNotEmpty(routeObjectInfos)) {
                // 路由写入内存
                routeObjectInfos.forEach(v -> addRoute(v, false));
                this.notifyChanged();
            }
        }
    }


    /**
     * 把传递进来的参数转换成路由对象
     *
     * @param vo ${@link GatewayRouteVo}
     * @return RouteDefinition ${@link RouteDefinition}
     * @author zxiaozhou
     * @date 2020-09-10 21:15
     */
    private RouteDefinition getRouteDefinition(GatewayRouteInfoModel vo) {
        /*
         * 数据校验
         * 1.如果是负载均衡器负载均衡器类型必填,服务名必填,并且均衡器类型只能为0,1,2
         * 2.如果非负载均衡器,url必填并且开头必须是http,https,ws,wss
         */
        RouteDefinition definition = new RouteDefinition();
        definition.setId(vo.getRouteCode());
        definition.setOrder(vo.getRouteOrder());
        if (!CollectionUtils.isEmpty(vo.getMetadata())) {
            definition.setMetadata(vo.getMetadata());
        }
        // 生成uri
        URI uri;
        if (vo.getIsLoadBalancer() == 1) {
            if (Objects.isNull(vo.getLoadBalancerType()) || StringUtils.isBlank(vo.getServiceName())) {
                throw new ResponseException(Status.VERIFICATION_FAILED, "当选择负载均衡器时,均衡器类型与服务名必填");
            }
            if (!LbType.isHaveByType(vo.getLoadBalancerType())) {
                throw new ResponseException(Status.VERIFICATION_FAILED, "均衡器类型只能为:" + LbType.getAllType());
            }
            uri = URI.create(LbType.getByType(vo.getLoadBalancerType()).getTypeName() + vo.getServiceName());
        } else {
            String url = vo.getUrl();
            if (StringUtils.isBlank(url) || !ProtocolType.isHaveByType(url)) {
                throw new ResponseException(Status.VERIFICATION_FAILED, "非负载均衡器时url必填,并且只能是:" + ProtocolType.getAllType());
            }
            uri = UriComponentsBuilder.fromHttpUrl(url).build().toUri();
        }
        definition.setUri(uri);

        //设置断言
        List<PredicateDefinition> pdList = new ArrayList<>();
        List<GatewayRouteInfoModel.RoutePredicate> routePredicates = vo.getRoutePredicates();
        for (GatewayRouteInfoModel.RoutePredicate predicate : routePredicates) {
            // 验证断言类型是否存在
            if (!PredicateType.isHaveByType(predicate.getPredicateType())) {
                throw new ResponseException(Status.VERIFICATION_FAILED, "断言类型:" + predicate.getPredicateType() + "不存在,当前只能为:" + PredicateType.getAllType());
            }
            Map<String, String> args = new HashMap<>(4);
            Set<GatewayRouteInfoModel.Rule> rules = predicate.getRules();
            rules.forEach(v -> args.put(v.getRuleName(), v.getRuleValue()));
            PredicateDefinition predicateDefinition = new PredicateDefinition();
            predicateDefinition.setArgs(args);
            predicateDefinition.setName(predicate.getPredicateType());
            pdList.add(predicateDefinition);
        }
        definition.setPredicates(pdList);

        //设置过滤器
        List<FilterDefinition> filters = new ArrayList<>();
        List<GatewayRouteInfoModel.RouteFilter> routeFilters = vo.getRouteFilters();
        if (!CollectionUtils.isEmpty(routeFilters)) {
            for (GatewayRouteInfoModel.RouteFilter routeFilter : routeFilters) {
                // 验证过滤器类型是否存在
                if (!FilterType.isHaveByType(routeFilter.getFilterType())) {
                    throw new ResponseException(Status.VERIFICATION_FAILED, "过滤器类型:" + routeFilter.getFilterType() + "不存在,当前只能为:" + FilterType.getAllType());
                }
                Map<String, String> args = new HashMap<>(4);
                Set<GatewayRouteInfoModel.Rule> rules = routeFilter.getRules();
                rules.forEach(v -> args.put(v.getRuleName(), v.getRuleValue()));

                FilterDefinition filterDefinition = new FilterDefinition();
                filterDefinition.setName(routeFilter.getFilterType());
                filterDefinition.setArgs(args);
                filters.add(filterDefinition);
            }
        }
        definition.setFilters(filters);
        return definition;
    }


    /**
     * 刷新路由
     *
     * @author zxiaozhou
     * @date 2020-09-11 12:05
     */
    private void notifyChanged() {
        publisher.publishEvent(new RefreshRoutesEvent(this));
    }
}
