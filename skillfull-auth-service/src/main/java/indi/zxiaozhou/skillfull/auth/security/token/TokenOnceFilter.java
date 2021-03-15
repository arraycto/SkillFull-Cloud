// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.security.token;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import indi.zxiaozhou.skillfull.auth.modules.user.service.model.TokenInfo;
import indi.zxiaozhou.skillfull.corecommon.base.model.BaseTokenModel;
import indi.zxiaozhou.skillfull.corecommon.base.model.UserTokenClaimSubModel;
import indi.zxiaozhou.skillfull.coremvc.utils.ContextHolderUtils;
import indi.zxiaozhou.skillfull.coremvc.utils.CoreMvcUtils;
import indi.zxiaozhou.skillfull.coremvc.utils.ServletUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 处理security上下文
 *
 * @author zxiaozhou
 * @date 2020-07-17 18:08
 * @since JDK11
 */
@Slf4j
@RequiredArgsConstructor
@Component
@Order(1)
public class TokenOnceFilter extends OncePerRequestFilter {
    private final TokenStore tokenStore;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = ServletUtils.getToken(request);
        // 设置授权上下文
        LoginSuccessAuthenticationToken authentication;
        BaseTokenModel systemBaseInfo = CoreMvcUtils.getSystemBaseInfo();
        String userId = null;
        String newToken = null;
        if (StrUtil.isNotBlank(token)) {
            authentication = tokenStore.getAuthentication();
            if (Objects.nonNull(authentication)) {
                userId = authentication.getPrincipal().getUserAndAuthModel().getUserId();
                SecurityContextHolder.getContext().setAuthentication(authentication);
                // 时间到达更换token时间,提前创建并弄进响应头
                if (ContextHolderUtils.getExpires() <= systemBaseInfo.getTokenDetectInSeconds()) {
                    TokenInfo tokenInfo = tokenStore.updateAuthentication(authentication, token);
                    newToken = URLEncoder.encode(JSONObject.toJSONString(tokenInfo), StandardCharsets.UTF_8);
                    response.addHeader(CoreMvcUtils.getSystemBaseInfo().getTokenHeaderStartWith(), JSONObject.toJSONString(tokenInfo));
                }
            }
        }
        filterChain.doFilter(request, response);
        // 如果请求成功并且需要更换token,则删除旧token(如果提前删除出现响应失败前端无法替换)
        if (StringUtils.isNotBlank(token) && StringUtils.isNotBlank(newToken) && response.getStatus() == HttpStatus.OK.value()) {
            UserTokenClaimSubModel userTokenSubInfoModel = ContextHolderUtils.getUserTokenSubInfoModel();
            if (Objects.nonNull(userTokenSubInfoModel)) {
                tokenStore.removeToken(userTokenSubInfoModel.getSystemId(), userId);
            }
        }
    }
}
