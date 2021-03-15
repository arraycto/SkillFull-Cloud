// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.corewebflux.utils;

import cn.hutool.core.collection.CollectionUtil;
import indi.zxiaozhou.skillfull.corecommon.base.model.BaseTokenModel;
import indi.zxiaozhou.skillfull.corecommon.utils.CoreCommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.HandshakeInfo;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * core mvc servlet util
 *
 * @author zxiaozhou
 * @date 2020-10-07 09:24
 * @since JDK11
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ServletUtils {
    private static ServletUtils utils;
    private final static String UNKNOWN = "unknown";
    public static final ThreadLocal<ServerHttpRequest> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 本地线程保存ServerHttpRequest
     *
     * @param request ${@link ServerHttpRequest}
     * @author zxiaozhou
     * @date 2021-02-04 10:32
     */
    public static void setServerHttpRequest(ServerHttpRequest request) {
        THREAD_LOCAL.set(request);
    }

    /**
     * 本地线程获取ServerHttpRequest
     *
     * @return ServerHttpRequest ${@link ServerHttpRequest}
     * @author zxiaozhou
     * @date 2021-02-04 10:32
     */
    public static ServerHttpRequest getServerHttpRequest() {
        return THREAD_LOCAL.get();
    }


    /**
     * 本地线程变量删除数据
     *
     * @return ServerHttpRequest ${@link ServerHttpRequest}
     * @author zxiaozhou
     * @date 2021-02-04 10:32
     */
    public static void removeServerHttpRequest() {
        THREAD_LOCAL.remove();
    }


    /**
     * 获取请求头token
     *
     * @param handshakeInfo ${@link HandshakeInfo}
     * @return String ${@link String}
     * @author zxiaozhou
     * @date 2020-10-07 09:56
     */
    public static String getToken(HandshakeInfo handshakeInfo) {
        BaseTokenModel systemBaseInfo = CoreWebFluxUtils.getSystemBaseInfo();
        if (Objects.nonNull(systemBaseInfo)) {
            HttpHeaders headers = handshakeInfo.getHeaders();
            String token = headers.getFirst(systemBaseInfo.getTokenHeaderKey());
            if (StringUtils.isNotBlank(token)) {
                if (StringUtils.isNotBlank(systemBaseInfo.getTokenHeaderStartWith())) {
                    token = token.replaceFirst(systemBaseInfo.getTokenHeaderStartWith(), "");
                }
                return token;
            } else {
                String query = handshakeInfo.getUri().getQuery();
                Map<String, String> queryMap = CoreCommonUtils.getQueryMap(query);
                if (CollectionUtil.isNotEmpty(queryMap)) {
                    token = queryMap.get(systemBaseInfo.getTokenQueryKey());
                    if (StringUtils.isNotBlank(token)) {
                        return token;
                    }
                }
            }
        }
        return "";
    }

    /**
     * 获取token
     *
     * @return String ${@link String}
     * @author zxiaozhou
     * @date 2021-02-04 10:53
     */
    public static String getToken() {
        return getToken(getServerHttpRequest());
    }


    /**
     * 获取请求头token
     *
     * @return String ${@link String}
     * @author zxiaozhou
     * @date 2020-10-07 09:56
     */
    public static String getToken(ServerHttpRequest request) {
        BaseTokenModel systemBaseInfo = CoreWebFluxUtils.getSystemBaseInfo();
        if (Objects.nonNull(systemBaseInfo)) {
            HttpHeaders headers = request.getHeaders();
            String token = headers.getFirst(systemBaseInfo.getTokenHeaderKey());
            if (StringUtils.isNotBlank(token)) {
                if (StringUtils.isNotBlank(systemBaseInfo.getTokenHeaderStartWith())) {
                    token = token.replaceFirst(systemBaseInfo.getTokenHeaderStartWith(), "");
                }
                return token;
            } else {
                String query = request.getPath().toString();
                Map<String, String> queryMap = CoreCommonUtils.getQueryMap(query);
                if (CollectionUtil.isNotEmpty(queryMap)) {
                    token = queryMap.get(systemBaseInfo.getTokenQueryKey());
                    if (StringUtils.isNotBlank(token)) {
                        return token;
                    }
                }
            }
        }
        Map<String, String> map = new HashMap<>();

        return "";
    }


    @PostConstruct
    private void init() {
        utils = this;
    }
}
