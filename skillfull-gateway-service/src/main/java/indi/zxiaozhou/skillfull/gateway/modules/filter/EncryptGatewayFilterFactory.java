// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.gateway.modules.filter;

import com.google.common.base.Joiner;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import static org.springframework.cloud.gateway.support.GatewayToStringStyler.filterToStringCreator;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR;

/**
 * 加密过滤器
 *
 * @author zxiaozhou
 * @date 2020-09-11 22:34
 * @since JDK11
 */
@Slf4j
@Component
public class EncryptGatewayFilterFactory extends AbstractGatewayFilterFactory<EncryptGatewayFilterFactory.Config> {

    private static Joiner joiner = Joiner.on("");

    @Override
    public GatewayFilter apply(Config config) {
        AuthorizeGatewayFilter gatewayFilter = new AuthorizeGatewayFilter(config);
        gatewayFilter.setFactory(this);
        return gatewayFilter;
    }


    public EncryptGatewayFilterFactory() {
        super(EncryptGatewayFilterFactory.Config.class);
    }


    private class AuthorizeGatewayFilter implements GatewayFilter, Ordered {

        private final Config config;
        private GatewayFilterFactory<EncryptGatewayFilterFactory.Config> gatewayFilterFactory;

        public AuthorizeGatewayFilter(Config config) {
            this.config = config;
        }

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            ServerHttpResponse originalResponse = exchange.getResponse();
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            ServerHttpResponseDecorator response = new ServerHttpResponseDecorator(originalResponse) {
                @Override
                public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {

                    // 获取ContentType，判断是否返回JSON格式数据
                    String originalResponseContentType = exchange.getAttribute(ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR);
                    if (Objects.equals(getStatusCode(), HttpStatus.OK) && body instanceof Flux) {
                        Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                        return super.writeWith(fluxBody.buffer().map(dataBuffers -> {//解决返回体分段传输
                            List<String> list = Lists.newArrayList();
                            dataBuffers.forEach(dataBuffer -> {
                                try {
                                    byte[] content = new byte[dataBuffer.readableByteCount()];
                                    dataBuffer.read(content);
                                    DataBufferUtils.release(dataBuffer);

                                    list.add(new String(content, StandardCharsets.UTF_8));
                                } catch (Exception e) {
                                    log.info("动态加载API加密规则失败，失败原因：{}", Throwables.getStackTraceAsString(e));
                                }
                            });
                            String responseData = joiner.join(list) + "oiuoiuoiuoiu";
                            log.info("------------WrapperResponseGlobalFilter------------>writeWith:{}", responseData);
                            // 二次处理（加密/过滤等）如果不需要做二次处理可直接跳过下行
                            byte[] uppedContent = new String(responseData.getBytes(), StandardCharsets.UTF_8).getBytes();
                            originalResponse.getHeaders().setContentLength(uppedContent.length);
                            return bufferFactory.wrap(uppedContent);
                        }));
                    }
                    log.info("------------WrapperResponseGlobalFilter------------>writeWith:{}", "sdfsddddddd");
                    return super.writeWith(body);
                }

                @Override
                public Mono<Void> writeAndFlushWith(Publisher<? extends Publisher<? extends DataBuffer>> body) {
                    return writeWith(Flux.from(body).flatMapSequential(p -> p));
                }
            };
            return chain.filter(exchange.mutate().response(response).build());
        }

        @Override
        public int getOrder() {
            return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER - 1;
        }

        @Override
        public String toString() {
            Object obj = (this.gatewayFilterFactory != null) ? this.gatewayFilterFactory : this;
            return filterToStringCreator(obj)
                    .append("New content type", " config.getNewContentType()")
                    .append("In class", " config.getInClass()")
                    .append("Out class", "config.getOutClass()").toString();
        }

        public void setFactory(GatewayFilterFactory<EncryptGatewayFilterFactory.Config> gatewayFilterFactory) {
            this.gatewayFilterFactory = gatewayFilterFactory;
        }
    }


    public static class Config {
        // 控制是否开启认证
        private boolean enabled;

        public Config() {
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}
