// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.coremvc.config;

import indi.zxiaozhou.skillfull.corecommon.base.model.BaseTokenModel;
import indi.zxiaozhou.skillfull.coremvc.config.properties.AppProperty;
import indi.zxiaozhou.skillfull.coremvc.config.properties.SwaggerProperty;
import indi.zxiaozhou.skillfull.coremvc.utils.CoreMvcUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Optional.ofNullable;

/**
 * swagger配置
 *
 * @author zxiaozhou
 * @date 2020-07-08 18:37
 * @since JDK11
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class Swagger3Config implements WebMvcConfigurer {
    private final SwaggerProperty swaggerProperty;
    private final AppProperty appProperty;


    /**
     * 默认api分组
     *
     * @return Docket ${@link Docket}
     * @author zxiaozhou
     * @date 2020-07-06 14:52
     */
    @Bean
    public Docket defaultGroup() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(basePackage(swaggerProperty.getScanPackages()))
                .apis(Swagger3Config::isControllerHidden)
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(this.securitySchemes())
                .securityContexts(this.securityContexts());
    }


    /**
     * 查询controller是否隐藏
     *
     * @param input ${@link RequestHandler}
     * @author zxiaozhou
     * @date 2020-07-20 12:48
     */
    protected static boolean isControllerHidden(RequestHandler input) {
        java.util.Optional<Api> controllerAnnotation = input.findControllerAnnotation(Api.class);
        if (controllerAnnotation.isPresent()) {
            Api api = controllerAnnotation.get();
            return !api.hidden();
        }
        return false;
    }


    /**
     * api文档的详细信息
     *
     * @return ApiInfo
     */
    protected ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //大标题
                .title(appProperty.getServiceName() + "服务接口文档")
                // 版本号
                .version(swaggerProperty.getVersion())
                // 描述
                .description(appProperty.getServiceName() + "服务API接口")
                .build();
    }


    /**
     * 多包扫描判断
     *
     * @param scanPackages ${@link Set<String>} 包路径
     * @return Boolean> ${@link Boolean>}
     * @author zxiaozhou
     * @date 2020-08-30 13:14
     */
    @SuppressWarnings("deprecation")
    private static Predicate<RequestHandler> basePackage(final Set<String> scanPackages) {
        return input -> ofNullable(input.declaringClass()).map(handlerPackage(scanPackages)).orElse(false);
    }


    private static Function<Class<?>, Boolean> handlerPackage(final Set<String> scanPackages) {
        return input -> {
            for (String basePackage : scanPackages) {
                boolean b = ClassUtils.getPackageName(input).startsWith(basePackage);
                if (b) {
                    return true;
                }
            }
            return false;
        };
    }


    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> list = new ArrayList<>();
        BaseTokenModel systemBaseInfo = CoreMvcUtils.getSystemBaseInfo();
        list.add(new ApiKey(systemBaseInfo.getTokenHeaderKey(), systemBaseInfo.getTokenHeaderKey(), "header"));
        return list;
    }


    @SuppressWarnings("deprecation")
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> list = new ArrayList<>();
        list.add(SecurityContext.builder()
                .securityReferences(this.defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build());
        return list;
    }


    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> list = new ArrayList<>();
        BaseTokenModel systemBaseInfo = CoreMvcUtils.getSystemBaseInfo();
        list.add(new SecurityReference(systemBaseInfo.getTokenHeaderKey(), authorizationScopes));
        return list;
    }

}