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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 图片验证码vo
 *
 * @author zxiaozhou
 * @date 2020-06-30 15:49
 * @since JDK11
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class SmsCodeVo implements Serializable {
    private static final long serialVersionUID = 2012658056429032034L;

    @ApiModelProperty(name = "codeId", value = "验证码id")
    @NotBlank(message = "图片验证码标识不能为空")
    private String codeId;

    @ApiModelProperty(name = "codeValue", value = "验证码value")
    @NotBlank(message = "图片验证码不能为空")
    private String codeValue;

    @ApiModelProperty(name = "phone", value = "手机号码value")
    @NotBlank(message = "手机号码不能为空")
    private String phone;
}
