// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.security.validate.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.alibaba.fastjson.JSONObject;
import indi.zxiaozhou.skillfull.auth.security.config.properties.SecurityProperties;
import indi.zxiaozhou.skillfull.auth.security.token.TokenStore;
import indi.zxiaozhou.skillfull.auth.security.validate.*;
import indi.zxiaozhou.skillfull.coremvc.utils.CoreMvcStringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static indi.zxiaozhou.skillfull.auth.core.constant.SystemCommonConstant.PICTURE_CODE_KEY_PREFIX;


/**
 * 图片验证码实现
 *
 * @author zxiaozhou
 * @date 2020-06-29 02:30
 * @since JDK11
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class PictureValidate implements IValidate {
    private final SecurityProperties properties;
    private final TokenStore tokenStore;


    @Override
    public ValidateDto getVerification(JSONObject parameter, HttpServletRequest request) {
        // 生成验证码
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(300, 150, 4, 20);
        String imageBase64 = captcha.getImageBase64Data();
        String code = captcha.getCode();
        String codeId = CoreMvcStringUtils.get32UUId();

        ValidateModel securityModel = IValidate.getSecurityModel();
        securityModel.setValidateCode(code);
        // 存储
        tokenStore.save(PICTURE_CODE_KEY_PREFIX + codeId, securityModel, properties.getCodeValidityInSeconds());
        // 构造验证码web端信息
        ValidateDto validateDto = new ValidateDto();
        validateDto.setCodeType(CodeType.PICTURE_CODE);
        validateDto.setValidTime(properties.getCodeValidityInSeconds());
        validateDto.setPublicKey(securityModel.getPublicKey());
        validateDto.setCodeValue(imageBase64);
        validateDto.setCodeId(codeId);
        validateDto.setStatus(true);
        return validateDto;
    }


    @Override
    public CheckDto checkVerification(CheckModel parameter) {
        CheckDto checkDto = new CheckDto();
        Object data = tokenStore.getData(PICTURE_CODE_KEY_PREFIX + parameter.getCodeId());
        if (Objects.nonNull(data)) {
            ValidateModel securityModel = (ValidateModel) data;
            String code = securityModel.getValidateCode();
            tokenStore.removeData(PICTURE_CODE_KEY_PREFIX + parameter.getCodeId());
            if (code.equalsIgnoreCase(parameter.getCodeValue())) {
                checkDto.setResult(true);
            } else {
                checkDto.setMsg("验证码错误");
            }
        } else {
            checkDto.setMsg("验证码过期");
        }
        return checkDto;
    }
}
