// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.coremvc.config.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * swagger配置
 *
 * @author zxiaozhou
 * @date 2020-08-30 15:33
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "springfox")
public class SwaggerProperty {
    /**
     * 扫包路径
     */
    private Set<String> scanPackages;

    /**
     * 版本号
     */
    @Value("${spring.application.version}")
    private String version;
}
