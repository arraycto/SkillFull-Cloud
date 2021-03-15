// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.coremvc.utils;

import indi.zxiaozhou.skillfull.corecommon.base.model.AuthUserEncryptionInfoModel;
import indi.zxiaozhou.skillfull.corecommon.base.model.UserAndAuthModel;
import indi.zxiaozhou.skillfull.corecommon.base.model.UserTokenClaimSubModel;
import indi.zxiaozhou.skillfull.corecommon.base.model.UserTokenSecurityModel;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import indi.zxiaozhou.skillfull.corecommon.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.Session;
import java.util.Objects;

import static indi.zxiaozhou.skillfull.corecommon.constant.SysBaseConstant.*;

/**
 * 上下文持有者
 *
 * @author zxiaozhou
 * @date 2020-10-07 09:07
 * @since JDK11
 */
@Component
@RequiredArgsConstructor
public class ContextHolderUtils {
    private static ContextHolderUtils utils;
    private final RedisTemplate<String, Object> redisTemplate;


    /**
     * 获取登录用户信息
     *
     * @return UserAndAuthModel ${@link UserAndAuthModel}
     * @author zxiaozhou
     * @date 2020-10-07 09:08
     */
    public static UserAndAuthModel getUserAndAuthModel() {
        String token = ServletUtils.getToken();
        return getUserAndAuthModel(token);
    }


    /**
     * 获取登录用户信息
     *
     * @return UserAndAuthModel ${@link UserAndAuthModel}
     * @author zxiaozhou
     * @date 2020-10-07 09:08
     */
    public static UserAndAuthModel getUserAndAuthModel(String token) {
        UserTokenClaimSubModel tokenSubInfo = TokenUtils.getTokenSubInfoNoDecryption(token);
        UserTokenSecurityModel tokenSecurityModel = getTokenSecurityModel(tokenSubInfo.getSystemId());
        if (Objects.isNull(tokenSecurityModel)) {
            throw new ResponseException(Status.TOKEN_EXPIRED);
        }
        // 校验token
        checkUserTokenAndTime(token, tokenSecurityModel);
        // 解密token用户信息
        TokenUtils.decryptionTokenSubInfo(tokenSubInfo, tokenSecurityModel);
        // 获取用户信息
        Object data = utils.redisTemplate.opsForValue().get(USER_LOGIN_PREFIX + tokenSubInfo.getUserId() + "_" + tokenSubInfo.getSystemId());
        if (Objects.nonNull(data)) {
            return (UserAndAuthModel) data;
        }
        throw new ResponseException(Status.TOKEN_EXPIRED);
    }


    /**
     * 获取用户id
     *
     * @return String ${@link String}
     * @author zxiaozhou
     * @date 2020-10-22 20:35
     */
    public static String getUserId() {
        return getUserAndAuthModel().getUserId();
    }


    /**
     * 获取用户真实姓名
     *
     * @return String ${@link String}
     * @author zxiaozhou
     * @date 2020-10-22 20:36
     */
    public static String getRealName() {
        return getUserAndAuthModel().getRealName();
    }


    /**
     * 获取用户token 解密信息
     *
     * @return UserTokenSecurityModel ${@link UserTokenSecurityModel}
     * @author zxiaozhou
     * @date 2020-10-20 21:27
     */
    public static UserTokenSecurityModel getUserTokenSecurityModel(String token) {
        try {
            UserTokenClaimSubModel tokenSubInfo = TokenUtils.getTokenSubInfoNoDecryption(token);
            return getTokenSecurityModel(tokenSubInfo.getSystemId());
        } catch (Exception e) {
            throw new ResponseException(Status.TOKEN_EXPIRED);
        }

    }


    /**
     * 获取用户token 解密信息
     *
     * @return UserTokenSecurityModel ${@link UserTokenSecurityModel}
     * @author zxiaozhou
     * @date 2020-10-20 21:27
     */
    public static UserTokenSecurityModel getUserTokenSecurityModel() {
        String token = ServletUtils.getToken();
        return getUserTokenSecurityModel(token);
    }


    /**
     * 获取token信息
     *
     * @return UserTokenClaimSubModel ${@link UserTokenClaimSubModel}
     * @author zxiaozhou
     * @date 2020-10-20 21:24
     */
    public static UserTokenClaimSubModel getUserTokenSubInfoModel() {
        String token = ServletUtils.getToken();
        return getUserTokenSubInfoModel(token);
    }


    /**
     * 获取token信息
     *
     * @return UserTokenClaimSubModel ${@link UserTokenClaimSubModel}
     * @author zxiaozhou
     * @date 2020-10-20 21:24
     */
    public static UserTokenClaimSubModel getUserTokenSubInfoModel(Session session) {
        String token = ServletUtils.getToken(session);
        return getUserTokenSubInfoModel(token);
    }


    /**
     * 获取token信息
     *
     * @return UserTokenClaimSubModel ${@link UserTokenClaimSubModel}
     * @author zxiaozhou
     * @date 2020-10-20 21:24
     */
    public static UserTokenClaimSubModel getUserTokenSubInfoModel(String token) {
        try {
            UserTokenClaimSubModel tokenSubInfo = TokenUtils.getTokenSubInfoNoDecryption(token);
            UserTokenSecurityModel userTokenSecurityModel = getUserTokenSecurityModel(token);
            checkUserTokenAndTime(token, userTokenSecurityModel);
            TokenUtils.decryptionTokenSubInfo(tokenSubInfo, userTokenSecurityModel);
            return tokenSubInfo;
        } catch (Exception e) {
            throw new ResponseException(Status.TOKEN_EXPIRED);
        }
    }


    /**
     * 获取用户token加解密信息
     *
     * @param systemId ${@link UserTokenClaimSubModel} String 系统标识
     * @return UserTokenSecurityModel ${@link UserTokenSecurityModel} token解密信息
     * @author zxiaozhou
     * @date 2020-10-20 18:02
     */
    public static UserTokenSecurityModel getTokenSecurityModel(String systemId) {
        Object data = utils.redisTemplate.opsForValue().get(USER_TOKEN_SECURITY_PREFIX + systemId);
        if (Objects.nonNull(data)) {
            return (UserTokenSecurityModel) data;
        }
        throw new ResponseException(Status.TOKEN_EXPIRED);
    }


    /**
     * 获取用户数据加解密信息
     *
     * @param systemId ${@link String} 系统标识id
     * @return AuthUserEncryptionInfoModel ${@link AuthUserEncryptionInfoModel} 用户数据加解密信息
     * @author zxiaozhou
     * @date 2020-10-20 18:02
     */
    public static AuthUserEncryptionInfoModel getUserEncryptionInfoModel(String systemId) {
        Object data = utils.redisTemplate.opsForValue().get(USER_DATA_SECURITY_PREFIX + systemId);
        if (Objects.nonNull(data)) {
            return (AuthUserEncryptionInfoModel) data;
        }
        throw new ResponseException(Status.ERROR, "获取用户数据解密信息失败");
    }


    /**
     * 校验用户token是否有效
     *
     * @param token              ${@link String} token
     * @param tokenSecurityModel ${@link UserTokenSecurityModel} token解密信息
     * @author zxiaozhou
     * @date 2020-10-20 18:03
     */
    public static void checkUserToken(String token, UserTokenSecurityModel tokenSecurityModel) {
        boolean verify = TokenUtils.verify(token, tokenSecurityModel);
        if (!verify) {
            throw new ResponseException(Status.TOKEN_EXPIRED, "token无效");
        }
    }


    /**
     * 获取token过期时间
     *
     * @param token    ${@link String} token
     * @param security ${@link UserTokenSecurityModel} token加密信息
     * @return 剩余到期时间(单位毫秒)
     */
    public static long getExpires(String token, UserTokenSecurityModel security) {
        return TokenUtils.getExpires(token, security);
    }


    /**
     * 获取token过期时间
     *
     * @author zxiaozhou
     * @date 2020-10-20 20:56
     */
    public static long getExpires() {
        String token = ServletUtils.getToken();
        return getExpires(token);
    }


    /**
     * 获取token过期时间
     *
     * @author zxiaozhou
     * @date 2020-10-20 20:56
     */
    public static long getExpires(String token) {
        UserTokenClaimSubModel tokenSubInfo = TokenUtils.getTokenSubInfoNoDecryption(token);
        UserTokenSecurityModel tokenSecurityModel = getTokenSecurityModel(tokenSubInfo.getSystemId());
        return getExpires(token, tokenSecurityModel);
    }

    /**
     * 校验用户token是否有效
     *
     * @author zxiaozhou
     * @date 2020-10-20 20:46
     */
    public static boolean checkUserToken() {
        String token = ServletUtils.getToken();
        return checkUserToken(token);
    }


    /**
     * 校验用户token是否有效
     *
     * @author zxiaozhou
     * @date 2020-10-20 20:46
     */
    public static boolean checkUserToken(String token) {
        UserTokenClaimSubModel tokenSubInfo = TokenUtils.getTokenSubInfoNoDecryption(token);
        UserTokenSecurityModel tokenSecurityModel = getTokenSecurityModel(tokenSubInfo.getSystemId());
        return TokenUtils.verify(token, tokenSecurityModel);
    }


    /**
     * 校验用户token是否有效(同时校验过期时间)
     *
     * @param token              ${@link String} token
     * @param tokenSecurityModel ${@link UserTokenSecurityModel} token解密信息
     * @author zxiaozhou
     * @date 2020-10-20 18:03
     */
    public static void checkUserTokenAndTime(String token, UserTokenSecurityModel tokenSecurityModel) {
        boolean b = TokenUtils.verifyAndTime(token, tokenSecurityModel);
        if (!b) {
            throw new ResponseException(Status.TOKEN_EXPIRED);
        }
    }


    /**
     * 校验用户token是否有效(同时校验过期时间)
     *
     * @author zxiaozhou
     * @date 2020-10-20 20:46
     */
    public static boolean checkUserTokenAndTime(String token) {
        UserTokenClaimSubModel tokenSubInfo = TokenUtils.getTokenSubInfoNoDecryption(token);
        UserTokenSecurityModel tokenSecurityModel = getTokenSecurityModel(tokenSubInfo.getSystemId());
        return TokenUtils.verifyAndTime(token, tokenSecurityModel);
    }


    /**
     * 校验用户token是否有效(同时校验过期时间)
     *
     * @author zxiaozhou
     * @date 2020-10-20 20:46
     */
    public static boolean checkUserTokenAndTime() {
        String token = ServletUtils.getToken();
        return checkUserTokenAndTime(token);
    }


    @PostConstruct
    private void init() {
        utils = this;
    }
}
