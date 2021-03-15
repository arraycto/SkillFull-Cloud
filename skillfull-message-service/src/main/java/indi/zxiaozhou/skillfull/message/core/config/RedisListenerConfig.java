// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.core.config;

import indi.zxiaozhou.skillfull.message.core.config.model.WebSocketChartModel;
import indi.zxiaozhou.skillfull.message.core.config.model.WebSocketMsgModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.util.concurrent.ConcurrentHashMap;

/**
 * redis过期监听
 *
 * @author zxiaozhou
 * @date 2021-01-25 21:20
 * @since JDK11
 */
@Configuration
@RequiredArgsConstructor
public class RedisListenerConfig {
    private final RedisConnectionFactory redisConnectionFactory;
    private final ConcurrentHashMap<String, WebSocketChartModel> senderChartMap;
    private final ConcurrentHashMap<String, WebSocketMsgModel> senderMsgMap;


    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer() {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        return redisMessageListenerContainer;
    }


    @Bean
    public LoginExpiredListener keyExpiredListener() {
        return new LoginExpiredListener(this.redisMessageListenerContainer(), senderChartMap, senderMsgMap);
    }
}
