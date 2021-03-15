// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.core.handler;

import indi.zxiaozhou.skillfull.corecommon.constant.UrlPrefix;
import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import indi.zxiaozhou.skillfull.message.core.annotation.WebSocketEndpoint;
import org.springframework.beans.BeansException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * websocket handle自动注册
 *
 * @author zxiaozhou
 * @date 2021-01-08 12:12
 * @since JDK11
 */
public class WebSocketHandlerMapping extends SimpleUrlHandlerMapping {
    private final Map<String, WebSocketHandler> handlerMap = new LinkedHashMap<>();

    public WebSocketHandlerMapping() {
        super();
    }

    /**
     * Register WebSocket handlers annotated by @WebSocketMapping
     *
     * @throws BeansException
     */
    @Override
    public void initApplicationContext() throws BeansException {
        Map<String, Object> beanMap = obtainApplicationContext()
                .getBeansWithAnnotation(WebSocketEndpoint.class);
        beanMap.values().forEach(bean -> {
            if (!(bean instanceof WebSocketHandler)) {
                throw new ResponseException(String.format("Controller [%s] doesn't implement WebSocketHandler interface.", bean.getClass().getName()));
            }
            WebSocketEndpoint annotation = AnnotationUtils.getAnnotation(bean.getClass(), WebSocketEndpoint.class);
            // webSocketMapping 映射到管道中
            handlerMap.put(UrlPrefix.MESSAGE + Objects.requireNonNull(annotation).value(), (WebSocketHandler) bean);
        });
        super.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.setUrlMap(handlerMap);
        super.initApplicationContext();
    }
}
