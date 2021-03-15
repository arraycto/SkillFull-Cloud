// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.corewebflux.utils;

import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;

/**
 * @author zxiaozhou
 */
public class ReactiveRequestContextHolder {
    public static final Class<ServerHttpRequest> CONTEXT_KEY = ServerHttpRequest.class;
  

    public static Mono<ServerHttpRequest> getRequest() {
        return Mono.subscriberContext().map(ctx -> ctx.get(CONTEXT_KEY));
    }
}
