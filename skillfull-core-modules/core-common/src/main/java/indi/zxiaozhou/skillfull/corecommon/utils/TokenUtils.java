// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.corecommon.utils;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import indi.zxiaozhou.skillfull.corecommon.base.model.UserTokenClaimSubModel;
import indi.zxiaozhou.skillfull.corecommon.base.model.UserTokenSecurityModel;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import indi.zxiaozhou.skillfull.corecommon.utils.encryption.RSAUtils;
import org.apache.commons.lang3.StringUtils;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static indi.zxiaozhou.skillfull.corecommon.constant.SysBaseConstant.CLAIM_SUB_KEY;

/**
 * token工具类
 *
 * @author zxiaozhou
 * @date 2020-09-29 00:55
 * @since JDK11
 */
public class TokenUtils {
    /**
     * 检验token是否有效(同时验证是否过期)
     *
     * @param token    ${@link String} token
     * @param security ${@link UserTokenSecurityModel} token加密信息
     * @return true-有效,false-无效
     */
    public static boolean verifyAndTime(String token, UserTokenSecurityModel security) {
        try {
            JWTVerifier verifier = JWT.require(getAlgorithm(security)).build();
            DecodedJWT jwt = verifier.verify(token);
            Date expiresAt = jwt.getExpiresAt();
            Date nowDate = new Date();
            return nowDate.compareTo(expiresAt) < 0;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 检验token是否有效(不检验过期时间)
     *
     * @param token    ${@link String} token
     * @param security ${@link UserTokenSecurityModel} token加密信息
     * @return true-有效,false-无效
     */
    public static boolean verify(String token, UserTokenSecurityModel security) {
        try {
            JWTVerifier verifier = JWT.require(getAlgorithm(security)).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 创建token
     *
     * @param userTokenSubInfoModel ${@link UserTokenClaimSubModel} token sub信息
     * @param security              ${@link UserTokenSecurityModel} token加密信息
     * @return String ${@link String} 生成token
     * @author zxiaozhou
     * @date 2020-06-30 00:57
     */
    public static String createToken(UserTokenClaimSubModel userTokenSubInfoModel, UserTokenSecurityModel security) {
        // 设置头部信息
        Map<String, Object> header = new HashMap<>(2);
        header.put("Type", "Jwt");
        header.put("alg", "RSA256");
        if (StringUtils.isNotBlank(userTokenSubInfoModel.getUserId())) {
            userTokenSubInfoModel.setUserId(RSAUtils.encryptPublicKey(security.getPublicKey(), userTokenSubInfoModel.getUserId()));
        }
        return JWT.create()
                .withHeader(header)
                .withExpiresAt(security.getExpiresAt())
                .withClaim(CLAIM_SUB_KEY, CoreCommonUtils.objectToJsonStr(userTokenSubInfoModel))
                .withIssuer("auth service")
                .withIssuedAt(new Date())
                .withJWTId(IdUtil.simpleUUID())
                .sign(getAlgorithm(security));
    }


    /**
     * 获取token过期时间
     *
     * @param token    ${@link String} token
     * @param security ${@link UserTokenSecurityModel} token加密信息
     * @return 剩余到期时间(单位毫秒)
     */
    public static long getExpires(String token, UserTokenSecurityModel security) {
        try {
            Algorithm algorithm = getAlgorithm(security);
            JWTVerifier verifier = JWT.require(algorithm).build();
            Date expiresAt = verifier.verify(token).getExpiresAt();
            return expiresAt.getTime() - System.currentTimeMillis();
        } catch (Exception e) {
            return 0L;
        }
    }

    /**
     * 获取加密信息
     *
     * @param security ${@link UserTokenSecurityModel}
     * @return Algorithm ${@link Algorithm}
     * @author zxiaozhou
     * @date 2020-10-20 15:49
     */
    private static Algorithm getAlgorithm(UserTokenSecurityModel security) {
        RSAPublicKey publicKey = RSAUtils.getPublicKey(security.getPublicKey());
        RSAPrivateKey privateKey = RSAUtils.getPrivateKey(security.getPrivateKey());
        return Algorithm.RSA256(publicKey, privateKey);
    }


    /**
     * 获取jwt中编码信息
     *
     * @param token ${@link String}
     * @return DecodedJWT ${@link DecodedJWT}
     * @author zxiaozhou
     * @date 2020-10-20 16:52
     */
    public static UserTokenClaimSubModel getTokenSubInfoNoDecryption(String token) {
        if (StringUtils.isNotBlank(token)) {
            String sub;
            try {
                DecodedJWT decoded = JWT.decode(token);
                sub = decoded.getClaims().get(CLAIM_SUB_KEY).asString();
            } catch (Exception e) {
                throw new ResponseException(Status.TOKEN_EXPIRED, "token无效，请重新登录");
            }
            if (StringUtils.isNotBlank(sub)) {
                return JSONObject.parseObject(sub, UserTokenClaimSubModel.class);
            } else {
                throw new ResponseException(Status.TOKEN_EXPIRED, "token无效，请重新登录");
            }
        } else {
            throw new ResponseException(Status.TOKEN_EXPIRED, "当前用户未登录，请登录");
        }
    }


    /**
     * 获取jwt中systemId
     *
     * @param token ${@link String}
     * @return DecodedJWT ${@link DecodedJWT}
     * @author zxiaozhou
     * @date 2020-10-20 16:52
     */
    public static String getTokenSystemId(String token) {
        UserTokenClaimSubModel tokenSubInfoNoDecryption = getTokenSubInfoNoDecryption(token);
        return tokenSubInfoNoDecryption.getSystemId();
    }


    /**
     * token中用户信息解密
     *
     * @param userTokenSubInfoModel ${@link UserTokenClaimSubModel}  UserTokenClaimSubModel 信息
     * @param security              ${@link UserTokenSecurityModel} 解密密钥
     * @author zxiaozhou
     * @date 2020-10-20 17:14
     */
    public static void decryptionTokenSubInfo(UserTokenClaimSubModel userTokenSubInfoModel, UserTokenSecurityModel security) {
        userTokenSubInfoModel.setUserId(RSAUtils.decryptPrivateKey(security.getPrivateKey(), userTokenSubInfoModel.getUserId()));
    }
}
