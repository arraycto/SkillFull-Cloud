// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.security.authorization.weixin;

import cn.hutool.http.Method;
import indi.zxiaozhou.skillfull.corecommon.annotation.AutoLog;
import indi.zxiaozhou.skillfull.coremvc.utils.BodyReaderRequestWrapper;
import indi.zxiaozhou.skillfull.coremvc.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static indi.zxiaozhou.skillfull.corecommon.constant.CommonConstant.WEI_XIN_LOGIN;

/**
 * 图片验证码
 *
 * @author zxiaozhou
 * @date 2020-06-29 02:44
 * @since JDK11
 */
@Slf4j
public class WeiXinAuthenticationProcessFilter extends AbstractAuthenticationProcessingFilter {
    private boolean postOnly = true;
    private final static String LOGIN_URI = WEI_XIN_LOGIN;

    public WeiXinAuthenticationProcessFilter() {
        super(new AntPathRequestMatcher(LOGIN_URI, "POST"));
    }


    @Override
    @AutoLog(note = "微信登录", type = AutoLog.LOGIN)
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !Method.POST.name().equals(request.getMethod())) {
            throw new AuthenticationServiceException("请求方法不允许: " + request.getMethod());
        } else {
            WeiXinAuthenticationToken token = new WeiXinAuthenticationToken(this.getWxInfo(request));
            this.setDetails(request, token);
            return this.getAuthenticationManager().authenticate(token);
        }
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        // 重写request防止读取数据流中数据下传丢失
        if (!Method.GET.name().equals(request.getMethod())) {
            String contentType = request.getContentType();
            if (StringUtils.isNotBlank(contentType) && contentType.toLowerCase().contains(MediaType.APPLICATION_JSON_VALUE.toLowerCase())) {
                BodyReaderRequestWrapper bodyReaderRequestWrapper = ServletUtils.cloneRequest(request);
                if (Objects.nonNull(bodyReaderRequestWrapper)) {
                    request = bodyReaderRequestWrapper;
                }
            }
        }
        super.doFilter(request, res, chain);
    }


    @Nullable
    protected String getWxInfo(HttpServletRequest request) {
        String stringBody = "";
        if (!Method.GET.name().equals(request.getMethod())) {
            if (request instanceof BodyReaderRequestWrapper) {
                BodyReaderRequestWrapper bodyReaderRequestWrapper = (BodyReaderRequestWrapper) request;
                try {
                    stringBody = bodyReaderRequestWrapper.getStringBody();
                } catch (Exception e) {
                    stringBody = "";
                }
            }
        }
        return stringBody;
    }


    protected void setDetails(HttpServletRequest request, WeiXinAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }
}
