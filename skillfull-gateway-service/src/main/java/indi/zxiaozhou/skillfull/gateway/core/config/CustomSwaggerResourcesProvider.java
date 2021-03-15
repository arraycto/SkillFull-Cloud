// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.gateway.core.config;

import cn.hutool.core.collection.CollectionUtil;
import indi.zxiaozhou.skillfull.gateway.modules.manage.service.ISwaggerService;
import indi.zxiaozhou.skillfull.gateway.modules.manage.service.dto.SwaggerInfoDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

import static cn.hutool.core.util.StrUtil.SLASH;

/**
 * 获取具体服务apidocs
 *
 * @author zxiaozhou
 * @date 2020-09-14 01:33
 * @since JDK11
 */
@Component
@RequiredArgsConstructor
@Qualifier
@Primary
public class CustomSwaggerResourcesProvider implements SwaggerResourcesProvider {
    private final ISwaggerService iSwaggerService;

    @Value("${spring.application.name}")
    private String applicationName;


    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        // 远程系统中心配置swagger
        List<SwaggerInfoDto> swaggerInfo = iSwaggerService.getSwaggerInfo();
        if (CollectionUtil.isNotEmpty(swaggerInfo)) {
            swaggerInfo.forEach(v -> {
                String swaggerDocUri = v.getSwaggerDocUri();
                if (StringUtils.isNotBlank(swaggerDocUri)) {
                    SwaggerResource swaggerResource = new SwaggerResource();
                    if (!swaggerDocUri.startsWith(SLASH)) {
                        swaggerDocUri = SLASH + swaggerDocUri;
                    }
                    swaggerResource.setUrl(swaggerDocUri);
                    swaggerResource.setName(StringUtils.isNotBlank(v.getSwaggerTag()) ? v.getSwaggerTag() : v.getServiceName());
                    resources.add(swaggerResource);
                }
            });
        }
        // 网关自身swagger
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setUrl("/v3/api-docs");
        swaggerResource.setName(applicationName + "(网关服务)");
        resources.add(swaggerResource);
        return resources;
    }

}
