// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.security.token.store;


import cn.hutool.core.collection.CollectionUtil;
import indi.zxiaozhou.skillfull.auth.core.constant.LoginType;
import indi.zxiaozhou.skillfull.auth.modules.user.service.model.CustomUserDetails;
import indi.zxiaozhou.skillfull.auth.modules.user.service.model.TokenInfo;
import indi.zxiaozhou.skillfull.auth.security.config.properties.SecurityProperties;
import indi.zxiaozhou.skillfull.auth.security.token.LoginSuccessAuthenticationToken;
import indi.zxiaozhou.skillfull.auth.security.token.TokenStore;
import indi.zxiaozhou.skillfull.corecommon.base.model.UserAndAuthModel;
import indi.zxiaozhou.skillfull.corecommon.base.model.UserTokenClaimSubModel;
import indi.zxiaozhou.skillfull.corecommon.base.model.UserTokenSecurityModel;
import indi.zxiaozhou.skillfull.coremvc.utils.ContextHolderUtils;
import indi.zxiaozhou.skillfull.coremvc.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static indi.zxiaozhou.skillfull.corecommon.constant.SysBaseConstant.USER_LOGIN_PREFIX;
import static indi.zxiaozhou.skillfull.corecommon.constant.SysBaseConstant.USER_TOKEN_SECURITY_PREFIX;


/**
 * redis存储
 *
 * @author zxiaozhou
 * @date 2020-07-17 15:39
 * @since JDK11
 */
@Slf4j
public class RedisTokenStore implements TokenStore {
    private final RedisTemplate<String, Object> redisTemplate;
    private final SecurityProperties properties;


    public RedisTokenStore(@NotNull RedisTemplate<String, Object> redisTemplate,
                           SecurityProperties properties) {
        this.redisTemplate = redisTemplate;
        this.properties = properties;
    }


    @Override
    public LoginSuccessAuthenticationToken createAuthentication(Authentication authentication, CustomUserDetails userDetails) {
        // 生成token密钥信息
        UserTokenSecurityModel userTokenSecurityModel = this.createUserTokenSecurityModel(properties.getTokenValidityInSeconds());
        UserTokenClaimSubModel userTokenSubInfoModel = this.createUserTokenSubInfoModel(userDetails.getUserAndAuthModel().getUserId(), properties);
        // 创建token
        LoginSuccessAuthenticationToken token = this.createToken(authentication, userDetails, userTokenSubInfoModel, userTokenSecurityModel, properties);
        UserAndAuthModel userAndAuthModel = token.getPrincipal().getUserAndAuthModel();
        // 如果用户登录方式为单设备登录，则需要清楚已经登录的信息(如果该数据为空，则使用系统全局设置)
        boolean oneEquipmentLogin;
        Integer loginType = userAndAuthModel.getLoginType();
        if (Objects.nonNull(loginType)) {
            oneEquipmentLogin = loginType == LoginType.ONE_EQUIPMENT.getType();
        } else {
            oneEquipmentLogin = properties.isOneEquipmentLogin();
        }
        if (oneEquipmentLogin) {
            // 清空现有登录信息
            System.out.println("等待清空用户信息");
        }
        // 创建用户在线信息
        UserAndAuthModel.AuthUserOnlineInfo onlineUserInfo = new UserAndAuthModel.AuthUserOnlineInfo();
        onlineUserInfo.setLoginTime(LocalDateTime.now());
        onlineUserInfo.setCredentials(authentication.getCredentials());
        onlineUserInfo.setLoginIp(ServletUtils.getIpAddr());
        onlineUserInfo.setLoginEquipment(ServletUtils.getUserAgent());
        userAndAuthModel.setOnlineInfo(onlineUserInfo);
        this.saveLoginInfo(userTokenSecurityModel, userAndAuthModel, userTokenSubInfoModel);
        return token;
    }


    private void saveLoginInfo(UserTokenSecurityModel userTokenSecurityModel, UserAndAuthModel userAndAuthModel, UserTokenClaimSubModel userTokenSubInfoModel) {
        this.save(USER_LOGIN_PREFIX + userAndAuthModel.getUserId() + "_" + userTokenSubInfoModel.getSystemId(), userAndAuthModel, userTokenSecurityModel.getTokenValidityInSeconds() + 60);
        // 存储用户token解密信息(比所有信息时间都长)
        this.save(USER_TOKEN_SECURITY_PREFIX + userTokenSubInfoModel.getSystemId(), userTokenSecurityModel, userTokenSecurityModel.getTokenValidityInSeconds() + 300);
    }


    @Override
    public TokenInfo updateAuthentication(LoginSuccessAuthenticationToken loginSuccessAuthentication, String oldToken) {
        CustomUserDetails principal = loginSuccessAuthentication.getPrincipal();
        UserAndAuthModel userAndAuthModel = principal.getUserAndAuthModel();
        // 更新token刷新纪录
        UserAndAuthModel.AuthUserOnlineInfo onlineInfo = userAndAuthModel.getOnlineInfo();
        onlineInfo.setCurrentRefreshTokenTime(LocalDateTime.now());
        userAndAuthModel.setOnlineInfo(onlineInfo);
        // 计算现在有效时间
        long expires = ContextHolderUtils.getExpires() + properties.getTokenRenewInSeconds();
        UserTokenClaimSubModel userTokenSubInfoModel = this.createUserTokenSubInfoModel(principal.getUserAndAuthModel().getUserId(), properties);
        UserTokenSecurityModel userTokenSecurityModel = this.createUserTokenSecurityModel(expires);
        LoginSuccessAuthenticationToken newToken = this.createToken(loginSuccessAuthentication, principal, userTokenSubInfoModel, userTokenSecurityModel, properties);
        TokenInfo tokenInfo = newToken.getPrincipal().getTokenInfo();
        this.saveLoginInfo(userTokenSecurityModel, userAndAuthModel, userTokenSubInfoModel);
        return tokenInfo;
    }


    @Override
    public void updateUserAndAuth(UserAndAuthModel userAndAuthModel) {
        UserTokenClaimSubModel userTokenSubInfoModel = ContextHolderUtils.getUserTokenSubInfoModel();
        userAndAuthModel.setOnlineInfo(ContextHolderUtils.getUserAndAuthModel().getOnlineInfo());
        long expireTime = getExpireTime(USER_LOGIN_PREFIX + userAndAuthModel.getUserId() + "_" + userTokenSubInfoModel.getSystemId());
        this.save(USER_LOGIN_PREFIX + userAndAuthModel.getUserId() + "_" + userTokenSubInfoModel.getSystemId(), userAndAuthModel, expireTime);
    }


    @Override
    public LoginSuccessAuthenticationToken getAuthentication() {
        try {
            if (!ContextHolderUtils.checkUserTokenAndTime()) {
                return null;
            }
            UserAndAuthModel userAndAuthModel = ContextHolderUtils.getUserAndAuthModel();
            if (Objects.nonNull(userAndAuthModel)) {
                UserAndAuthModel.AuthUserOnlineInfo onlineInfo = userAndAuthModel.getOnlineInfo();
                CustomUserDetails customUserDetails = new CustomUserDetails(userAndAuthModel);
                return new LoginSuccessAuthenticationToken(customUserDetails, onlineInfo.getCredentials(), customUserDetails.getAuthorities());
            }
            return null;
        } catch (Exception e) {
            log.error("------------RedisTokenStore------获取用户信息失败------>getAuthentication:{}", e.getMessage());
            return null;
        }
    }


    @Override
    public TokenInfo autoLogin(UserAndAuthModel userAndAuthModel) {
        CustomUserDetails userDetails = new CustomUserDetails(userAndAuthModel);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginSuccessAuthenticationToken loginSuccessAuthenticationToken = this.createAuthentication(authentication, userDetails);
        return loginSuccessAuthenticationToken.getPrincipal().getTokenInfo();
    }


    @Override
    public void removeToken(String systemId, String userId) {
        if (StringUtils.isNotBlank(systemId)) {
            redisTemplate.delete(USER_TOKEN_SECURITY_PREFIX + systemId);
        }
        if (StringUtils.isNotBlank(userId)) {
            redisTemplate.delete(USER_LOGIN_PREFIX + userId + "_" + systemId);
        }
    }


    @Override
    public void removeDataLike(String key, String... ignoreKey) {
        Set<String> keys = redisTemplate.keys(key);
        Set<String> waitDeleteKeys = new HashSet<>();
        if (CollectionUtil.isNotEmpty(keys) && ArrayUtils.getLength(ignoreKey) > 0) {
            Set<String> finalWaitDeleteKeys = waitDeleteKeys;
            keys.forEach(v -> {
                boolean isIgnoreKey = false;
                for (String k : ignoreKey) {
                    if (v.equals(k)) {
                        isIgnoreKey = true;
                        break;
                    }
                }
                if (!isIgnoreKey) {
                    finalWaitDeleteKeys.add(v);
                }
            });
        } else {
            waitDeleteKeys = keys;
        }
        if (CollectionUtil.isNotEmpty(waitDeleteKeys)) {
            waitDeleteKeys.forEach(this::removeData);
        }
    }


    @Override
    public void save(String key, Object data, long validTime) {
        redisTemplate.opsForValue().set(key, data, validTime, TimeUnit.SECONDS);
    }


    @Override
    public Object getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    @Override
    public void removeData(String key) {
        redisTemplate.delete(key);
    }


    @Override
    public long getExpireTime(String key) {
        Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        if (Objects.isNull(expire)) {
            expire = 0L;
        }
        return expire;
    }
}
