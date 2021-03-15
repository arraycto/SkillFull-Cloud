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

import indi.zxiaozhou.skillfull.auth.modules.user.service.model.CustomUserDetails;
import indi.zxiaozhou.skillfull.auth.modules.user.service.model.TokenInfo;
import indi.zxiaozhou.skillfull.auth.security.config.properties.SecurityProperties;
import indi.zxiaozhou.skillfull.corecommon.base.model.UserAndAuthModel;
import indi.zxiaozhou.skillfull.corecommon.base.model.UserTokenClaimSubModel;
import indi.zxiaozhou.skillfull.corecommon.base.model.UserTokenSecurityModel;
import indi.zxiaozhou.skillfull.corecommon.utils.ConvertUtil;
import indi.zxiaozhou.skillfull.corecommon.utils.TokenUtils;
import indi.zxiaozhou.skillfull.corecommon.utils.encryption.RSAUtils;
import indi.zxiaozhou.skillfull.coremvc.utils.CoreMvcStringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;

import java.util.Date;
import java.util.Map;

/**
 * token存储
 *
 * @author zxiaozhou
 * @date 2020-07-17 14:36
 * @since JDK11
 */
public interface TokenStore {
    GrantedAuthoritiesMapper AUTHORITIES_MAPPER = new NullAuthoritiesMapper();

    /**
     * 创建token
     *
     * @param authentication         ${@link Authentication}
     * @param userDetails            ${@link CustomUserDetails}
     * @param userTokenSubInfoModel  ${@link UserTokenClaimSubModel}
     * @param userTokenSecurityModel ${@link UserTokenSecurityModel}
     * @param properties             ${@link SecurityProperties}
     * @return LoginSuccessAuthenticationToken ${@link LoginSuccessAuthenticationToken}
     * @author zxiaozhou
     * @date 2020-09-29 00:41
     */
    default LoginSuccessAuthenticationToken createToken(Authentication authentication,
                                                        CustomUserDetails userDetails,
                                                        UserTokenClaimSubModel userTokenSubInfoModel,
                                                        UserTokenSecurityModel userTokenSecurityModel,
                                                        SecurityProperties properties) {
        // 生成token
        String token = TokenUtils.createToken(userTokenSubInfoModel, userTokenSecurityModel);
        // 创建token信息
        TokenInfo tokenInfo = ConvertUtil.map(properties, TokenInfo.class);
        tokenInfo.setExpiresTime(userTokenSecurityModel.getTokenValidityInSeconds());
        tokenInfo.setToken(token);
        tokenInfo.setExpiresAt(userTokenSecurityModel.getExpiresAt());
        userDetails.setTokenInfo(tokenInfo);
        // 构建登录成功token
        LoginSuccessAuthenticationToken loginSuccessAuthentication = new LoginSuccessAuthenticationToken(
                userDetails,
                authentication.getDetails(),
                AUTHORITIES_MAPPER.mapAuthorities(userDetails.getAuthorities()));
        loginSuccessAuthentication.setDetails(authentication.getDetails());
        return loginSuccessAuthentication;
    }

    /**
     * 创建用户token密钥信息
     *
     * @param tokenValidityInSeconds ${@link Long} 过期时间
     * @return UserTokenSecurityModel ${@link UserTokenSecurityModel}
     * @author zxiaozhou
     * @date 2020-10-20 22:11
     */
    default UserTokenSecurityModel createUserTokenSecurityModel(long tokenValidityInSeconds) {
        Map<String, String> security = RSAUtils.getX509AndPKCS8Key();
        UserTokenSecurityModel securityModel = new UserTokenSecurityModel();
        securityModel.setTokenValidityInSeconds(tokenValidityInSeconds);
        securityModel.setExpiresAt(new Date(System.currentTimeMillis() + tokenValidityInSeconds * 1000));
        securityModel.setPrivateKey(security.get("privateKey"));
        securityModel.setPublicKey(security.get("publicKey"));
        return securityModel;
    }


    /**
     * 创建用户token sub信息
     *
     * @param userId     ${@link String} 用户信息
     * @param properties ${@link SecurityProperties} 环境
     * @return UserTokenClaimSubModel ${@link UserTokenClaimSubModel}
     * @author zxiaozhou
     * @date 2020-10-20 22:13
     */
    default UserTokenClaimSubModel createUserTokenSubInfoModel(String userId, SecurityProperties properties) {
        UserTokenClaimSubModel userTokenSubInfoModel = new UserTokenClaimSubModel();
        userTokenSubInfoModel.setSystemId(CoreMvcStringUtils.get32UUId());
        userTokenSubInfoModel.setUserId(userId);
        userTokenSubInfoModel.setProfilesActive(properties.getProfilesActive());
        return userTokenSubInfoModel;
    }


    /**
     * 创建授权
     *
     * @param authentication ${@link Authentication} 授权信息
     * @param userDetails    ${@link CustomUserDetails} 授权成功响应信息
     * @return LoginSuccessAuthenticationToken ${@link LoginSuccessAuthenticationToken} 登录成功授权信息
     * @author zxiaozhou
     * @date 2020-07-17 15:46
     */
    LoginSuccessAuthenticationToken createAuthentication(Authentication authentication, CustomUserDetails userDetails);


    /**
     * 更新Authentication并返回新的token
     *
     * @param tokenValue                 ${@link String} 原始token
     * @param loginSuccessAuthentication ${@link LoginSuccessAuthenticationToken}
     * @return TokenUserInfo ${@link String} 新TokenInfo信息
     * @author zxiaozhou
     * @date 2020-07-17 15:46
     */
    TokenInfo updateAuthentication(LoginSuccessAuthenticationToken loginSuccessAuthentication, String tokenValue);


    /**
     * 更新用户信息以及权限信息并返回
     *
     * @param userAndAuthModel ${@link UserAndAuthModel} 新的用户权限信息
     * @author zxiaozhou
     * @date 2020-07-17 15:46
     */
    void updateUserAndAuth(UserAndAuthModel userAndAuthModel);


    /**
     * 获取授权信息
     *
     * @return LoginSuccessAuthentication ${@link LoginSuccessAuthenticationToken} 授权信息
     * @author zxiaozhou
     * @date 2020-07-17 15:46
     */
    LoginSuccessAuthenticationToken getAuthentication();


    /**
     * 自动登录
     *
     * @param userAndAuthModel ${@link UserAndAuthModel} 待登录用户信息
     * @return TokenUserInfo ${@link TokenInfo} 登录成功token
     * @author zxiaozhou
     * @date 2020-11-02 10:56
     */
    TokenInfo autoLogin(UserAndAuthModel userAndAuthModel);


    /**
     * 删除指定token
     *
     * @param systemId ${@link String} 系统id
     * @param userId   ${@link String} 用户id
     * @author zxiaozhou
     * @date 2020-07-17 15:47
     */
    void removeToken(String systemId, String userId);


    /**
     * 通用存储
     *
     * @param data      ${@link String} 验证信息
     * @param key       ${@link String} key
     * @param validTime ${@link Long} 有效时间,单位秒
     * @author zxiaozhou
     * @date 2020-07-17 22:17
     */
    void save(String key, Object data, long validTime);


    /**
     * 获取通用存在信息
     *
     * @param key ${@link String}
     * @return String ${@link Object}
     * @author zxiaozhou
     * @date 2020-07-17 22:22
     */
    Object getData(String key);


    /**
     * 删除
     *
     * @param key ${@link String}
     * @author zxiaozhou
     * @date 2020-07-17 22:23
     */
    void removeData(String key);


    /**
     * like删除数据
     *
     * @param key       ${@link String} 待删除like key
     * @param ignoreKey ${@link String} 排除key
     * @author zxiaozhou
     * @date 2020-07-17 22:23
     */
    void removeDataLike(String key, String... ignoreKey);


    /**
     * 获取过期时间
     *
     * @param key ${@link String} 待查询key
     * @return long ${@link Long} 过期时间
     * @author zxiaozhou
     * @date 2020-11-02 11:55
     */
    long getExpireTime(String key);
}
