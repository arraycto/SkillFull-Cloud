// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.gateway.modules.manage.service;

import indi.zxiaozhou.skillfull.corecommon.base.model.GatewayRouteInfoModel;
import indi.zxiaozhou.skillfull.gateway.modules.manage.controller.vo.GatewayRouteVo;
import indi.zxiaozhou.skillfull.gateway.modules.manage.service.dto.GatewayRouteDto;
import reactor.core.publisher.Flux;

/**
 * 动态路由服务接口
 *
 * @author zxiaozhou
 * @date 2020-09-10 22:41
 * @since JDK11
 */
public interface IDynamicRouteService {
    /**
     * 添加路由
     *
     * @param vo        ${@link GatewayRouteVo} 保存参数
     * @param isRefresh ${@link Boolean} 是否刷新路由:true-刷新,false-不刷新
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-10 22:45
     */
    void addRoute(GatewayRouteInfoModel vo, boolean isRefresh) throws RuntimeException;


    /**
     * 更新路由
     *
     * @param vo        ${@link GatewayRouteVo} 跟新参数
     * @param isRefresh ${@link Boolean} 是否刷新路由:true-刷新,false-不刷新
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-10 22:45
     */
    void updateRoute(GatewayRouteInfoModel vo, boolean isRefresh) throws RuntimeException;


    /**
     * 删除路由
     *
     * @param routeId   ${@link String} 路由id
     * @param isRefresh ${@link Boolean} 是否刷新路由:true-刷新,false-不刷新
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-11 11:35
     */
    void deleteRoute(String routeId, boolean isRefresh) throws RuntimeException;


    /**
     * 查询路由
     *
     * @return Flux<GatewayRouteDto>${@link  Flux<GatewayRouteDto>}
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-10 22:49
     */
    Flux<GatewayRouteDto> getRoutes() throws RuntimeException;


    /**
     * 加载或刷新路由
     *
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-13 00:39
     */
    void loadRoute() throws RuntimeException;

}
