// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.core.config;


import indi.zxiaozhou.skillfull.system.modules.manage.service.IManageRouteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 以jar包启动时创建外置配置文件
 *
 * @author zxiaozhou
 * @date 2019-04-16 10:38
 * @since JDK11
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class RouterStartConfig implements ApplicationRunner {
    private final IManageRouteService routeService;


    @Override
    public void run(ApplicationArguments args) {
        // 路由信息写入redis并通知网关
        log.debug("------------StartConfig------------>loadRoute:{}", "开始加载路由信息");
        routeService.refreshRoute();
    }

}
