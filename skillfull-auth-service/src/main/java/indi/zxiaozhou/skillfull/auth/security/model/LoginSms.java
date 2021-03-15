// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.security.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 短信登录vo
 *
 * @author zxiaozhou
 * @date 2020-07-08 19:05
 * @since JDK11
 */
@Data
public class LoginSms implements Serializable {
    private static final long serialVersionUID = 7229979897479559294L;

    @ApiModelProperty(name = "phone", value = "电话号码", required = true)
    @NotBlank(message = "电话号码不能为空")
    private String phone;

    @ApiModelProperty(name = "code", value = "短信验证码", required = true)
    @NotBlank(message = "短信验证码不能为空")
    private String code;

    @ApiModelProperty(name = "rememberMe", value = "记住我")
    private boolean rememberMe;
}
