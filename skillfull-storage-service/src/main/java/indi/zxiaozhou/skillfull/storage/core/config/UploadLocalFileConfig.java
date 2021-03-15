// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.storage.core.config;

import indi.zxiaozhou.skillfull.storage.core.config.properties.LocalFileProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 文件本地映射路径配置
 *
 * @author zxiaozhou
 * @date 2020-10-23 13:15
 * @since JDK11
 */
@Configuration
@Slf4j
@RequiredArgsConstructor
public class UploadLocalFileConfig implements WebMvcConfigurer {
    private final LocalFileProperty property;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(property.getVirtualMapping() + "/**")
                .addResourceLocations("file:" + property.getUploadFolder() + "/");
    }

}
