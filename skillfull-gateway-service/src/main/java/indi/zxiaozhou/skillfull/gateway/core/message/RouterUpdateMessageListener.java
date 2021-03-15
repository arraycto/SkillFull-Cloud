// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.gateway.core.message;

import indi.zxiaozhou.skillfull.gateway.modules.manage.service.IDynamicRouteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * 路由信息
 *
 * @author zxiaozhou
 * @date 2020-09-13 01:28
 * @since JDK11
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RouterUpdateMessageListener {
    private final IDynamicRouteService routeService;

    @StreamListener(RouteUpdateProcessor.PROCESS_ROUTER_UPDATE)
    public void receive(String message) {
        log.info("------------RouterMessageListener------收到路由变动消息------>receive:{}", message);
        routeService.loadRoute();
    }

}
