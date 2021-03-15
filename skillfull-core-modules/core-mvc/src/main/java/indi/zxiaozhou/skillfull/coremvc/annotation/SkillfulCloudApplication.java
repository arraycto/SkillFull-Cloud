// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.coremvc.annotation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.lang.annotation.*;

/**
 * 自定义启动注解
 *
 * @author zxiaozhou
 * @date 2021-01-12 17:16
 * @since JDK11
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootApplication(scanBasePackages = "indi.zxiaozhou.skillfull")
@EnableCircuitBreaker
@EnableAsync
@EnableOpenApi
@EnableCaching
@EnableFeignClients(basePackages = "indi.zxiaozhou.skillfull")
@EnableDiscoveryClient
@MapperScan("indi.zxiaozhou.skillfull.*.modules.*.mapper")
@EnableTransactionManagement
public @interface SkillfulCloudApplication {
}
