// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.user.controller.vo;

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
public class ChangePasswordVo implements Serializable {
    private static final long serialVersionUID = 7229979897479559294L;

    @ApiModelProperty(name = "oldPassword", value = "旧密码")
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;

    @ApiModelProperty(name = "newPassword", value = "新密码")
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}
