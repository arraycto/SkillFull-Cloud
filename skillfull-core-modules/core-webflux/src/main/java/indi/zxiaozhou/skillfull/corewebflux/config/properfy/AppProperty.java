// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.corewebflux.config.properfy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * app节点配置信息
 *
 * @author zxiaozhou
 * @date 2020-06-29 00:25
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperty implements Serializable {
    private static final long serialVersionUID = 713575253040294540L;

    /**
     * 运行环境
     */
    @Value("${spring.profiles.active}")
    private String active;

    /**
     * 服务名称
     */
    @Value("${spring.application.name}")
    private String serviceName;

    /**
     * 当前配置文件路径
     */
    @Value(value = "classpath:application-${spring.profiles.active}.yml")
    private Resource resource;

    /**
     * 是否生成外置配置文件
     */
    private boolean createOutConf;

    /**
     * 授权tokenKey
     */
    private String accessTokenKey;
}
