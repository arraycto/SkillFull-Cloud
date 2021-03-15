// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.security.validate;

import com.alibaba.fastjson.JSONObject;
import indi.zxiaozhou.skillfull.corecommon.base.model.BaseSecretModel;
import indi.zxiaozhou.skillfull.corecommon.utils.encryption.RSAUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 验证
 *
 * @author zxiaozhou
 * @date 2020-06-29 02:23
 * @since JDK11
 */
public interface IValidate {
    /**
     * 获取验证码信息
     * redis key构建:picture:codeKey+system,sms:codeKey+system+phone
     *
     * @param parameter ${@link JSONObject} 获取验证码参数
     * @param request   ${@link HttpServletRequest} HttpServletRequest
     * @return ValidateDto ${@link ValidateDto} 验证码结果
     * @author zxiaozhou
     * @date 2020-06-29 02:25
     */
    ValidateDto getVerification(JSONObject parameter, HttpServletRequest request);


    /**
     * 验证码验证
     *
     * @param parameter ${@link CheckModel} 参数
     * @return CheckDto 验证结果
     * @author zxiaozhou
     * @date 2020-06-29 02:25
     */
    CheckDto checkVerification(CheckModel parameter);


    /**
     * 获取非对称密钥用于用户信息加密
     *
     * @return BaseSecretModel ${@link BaseSecretModel}
     * @author zxiaozhou
     * @date 2020-10-20 21:50
     */
    static ValidateModel getSecurityModel() {
        Map<String, String> rsaSecurity = RSAUtils.getX509AndPKCS8Key();
        ValidateModel model = new ValidateModel();
        model.setPrivateKey(rsaSecurity.get("privateKey"));
        model.setPublicKey(rsaSecurity.get("publicKey"));
        return model;
    }


}
