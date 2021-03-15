// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.security.config;


import indi.zxiaozhou.skillfull.auth.modules.user.service.ILoginService;
import indi.zxiaozhou.skillfull.auth.modules.user.service.impl.UserDetailsServiceImpl;
import indi.zxiaozhou.skillfull.auth.modules.user.service.impl.UserDetailsWeiXinServiceImpl;
import indi.zxiaozhou.skillfull.auth.security.authorization.picture.PictureAuthenticationProcessFilter;
import indi.zxiaozhou.skillfull.auth.security.authorization.picture.PictureAuthenticationProvider;
import indi.zxiaozhou.skillfull.auth.security.authorization.sms.SmsAuthenticationProcessFilter;
import indi.zxiaozhou.skillfull.auth.security.authorization.sms.SmsAuthenticationProvider;
import indi.zxiaozhou.skillfull.auth.security.authorization.weixin.WeiXinAuthenticationProcessFilter;
import indi.zxiaozhou.skillfull.auth.security.authorization.weixin.WeiXinAuthenticationProvider;
import indi.zxiaozhou.skillfull.auth.security.config.properties.SecurityProperties;
import indi.zxiaozhou.skillfull.auth.security.handle.*;
import indi.zxiaozhou.skillfull.auth.security.token.TokenOnceFilter;
import indi.zxiaozhou.skillfull.auth.security.token.TokenStore;
import indi.zxiaozhou.skillfull.coremvc.annotation.Anonymous;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static indi.zxiaozhou.skillfull.corecommon.constant.CommonConstant.*;

/**
 * @author Zheng Jie
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomAuthenticationEntryPoint authenticationErrorHandler;
    private final CustomLogoutSuccessHandler logoutSuccessHandler;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final ApplicationContext applicationContext;
    private final SecurityProperties properties;
    private final CustomLogoutHandler logoutHandler;
    private final TokenStore tokenStore;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final ILoginService loginService;
    private final TokenOnceFilter tokenOnceFilter;
    private UserDetailsService userDetailsService;
    private UserDetailsService wxUserDetailsService;

    @Autowired
    public void setUserDetailsService(UserDetailsServiceImpl service) {
        this.userDetailsService = service;
    }

    @Autowired
    public void setWxUserDetailsService(UserDetailsWeiXinServiceImpl service) {
        this.wxUserDetailsService = service;
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // 处理自定义注解匿名用户访问权限
        Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods();
        Set<String> anonymousUrls = new HashSet<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethodMap.entrySet()) {
            HandlerMethod handlerMethod = infoEntry.getValue();
            Anonymous anonymousAccess = handlerMethod.getMethodAnnotation(Anonymous.class);
            if (Objects.nonNull(anonymousAccess)) {
                anonymousUrls.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
            }
        }
        // 处理不同环境全局默认白名单
        if (properties.getProfilesActive().toUpperCase().equals(DEV)) {
            anonymousUrls.addAll(properties.getDevWhiteList());
        } else if (properties.getProfilesActive().toUpperCase().equals(TEST)) {
            anonymousUrls.addAll(properties.getTestWhiteList());
        } else {
            anonymousUrls.addAll(properties.getProWhiteList());
        }
        httpSecurity
                // formatter:off
                .cors()
                // 关闭 CSRF
                .and().csrf().disable()
                // 登录行为由自己实现，参考 AuthController#login
                .formLogin().disable()
                .logout().logoutUrl(LOGIN_OUT).addLogoutHandler(logoutHandler).logoutSuccessHandler(logoutSuccessHandler)
                .and()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationErrorHandler)
                .accessDeniedHandler(accessDeniedHandler)
                // 防止iframe 造成跨域
                .and()
                .headers()
                .frameOptions()
                .disable()
                // 不创建会话
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 放行OPTIONS请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 自定义匿名访问所有url放行
                .antMatchers(anonymousUrls.toArray(new String[0])).permitAll();
        // 所有请求都需要认证
        if (properties.isOpenAuth()) {
            httpSecurity.authorizeRequests().anyRequest().authenticated();
        }
        // 添加自定义登录认证
        AuthenticationManager authenticationManager = super.authenticationManagerBean();
        httpSecurity
                // token验证过滤器
                .addFilterBefore(tokenOnceFilter, UsernamePasswordAuthenticationFilter.class)
                // 图片验证码登录
                .addFilterAfter(pictureAuthenticationProcessFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(pictureAuthenticationProvider())
                // 短信登录
                .addFilterAfter(smsAuthenticationProcessFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(smsAuthenticationProvider())
                // 微信登录
                .addFilterAfter(weiXinAuthenticationProcessFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(weiXinAuthenticationProvider());
    }


    @Bean
    public PictureAuthenticationProvider pictureAuthenticationProvider() {
        return new PictureAuthenticationProvider(userDetailsService, tokenStore, properties, loginService);
    }

    public PictureAuthenticationProcessFilter pictureAuthenticationProcessFilter(AuthenticationManager authenticationManager) {
        PictureAuthenticationProcessFilter processFilter = new PictureAuthenticationProcessFilter();
        processFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        processFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        processFilter.setAuthenticationManager(authenticationManager);
        return processFilter;
    }


    @Bean
    public SmsAuthenticationProvider smsAuthenticationProvider() {
        return new SmsAuthenticationProvider(userDetailsService, tokenStore, loginService);
    }

    public SmsAuthenticationProcessFilter smsAuthenticationProcessFilter(AuthenticationManager authenticationManager) {
        SmsAuthenticationProcessFilter processFilter = new SmsAuthenticationProcessFilter();
        processFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        processFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        processFilter.setAuthenticationManager(authenticationManager);
        return processFilter;
    }


    @Bean
    public WeiXinAuthenticationProvider weiXinAuthenticationProvider() {
        return new WeiXinAuthenticationProvider(wxUserDetailsService, tokenStore);
    }

    private WeiXinAuthenticationProcessFilter weiXinAuthenticationProcessFilter(AuthenticationManager authenticationManager) {
        WeiXinAuthenticationProcessFilter processFilter = new WeiXinAuthenticationProcessFilter();
        processFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        processFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        processFilter.setAuthenticationManager(authenticationManager);
        return processFilter;
    }
}
