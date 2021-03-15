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
 * 图片验证码登录vo
 *
 * @author zxiaozhou
 * @date 2020-07-08 19:05
 * @since JDK11
 */
@Data
public class LoginPicture implements Serializable {
    private static final long serialVersionUID = 7229979897479559294L;

    @ApiModelProperty(name = "userName", value = "账号")
    @NotBlank(message = "账号不能为空")
    private String userName;

    @ApiModelProperty(name = "password", value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(name = "codeId", value = "验证码id")
    @NotBlank(message = "验证码id不能为空")
    private String codeId;

    @ApiModelProperty(name = "code", value = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String code;

    @ApiModelProperty(name = "rememberMe", value = "记住我")
    private boolean rememberMe;
}
