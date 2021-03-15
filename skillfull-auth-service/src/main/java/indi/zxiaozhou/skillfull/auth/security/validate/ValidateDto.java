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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 验证码dto
 *
 * @author zxiaozhou
 * @date 2020-06-30 15:20
 * @since JDK11
 */
@Data
public class ValidateDto implements Serializable {
    private static final long serialVersionUID = -3819528498303862491L;
    @ApiModelProperty(name = "codeValue", value = "验证码值")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String codeValue;

    @ApiModelProperty(name = "codeType", value = "验证码类型")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String codeType;

    @ApiModelProperty(name = "validTime", value = "验证码有效时间(秒)")
    private Long validTime;

    @ApiModelProperty(name = "codeId", value = "tokenId")
    private String codeId;

    @ApiModelProperty(name = "publicKey", value = "用户信息加密公钥")
    private String publicKey;

    @JsonIgnore
    private boolean status = false;

    @JsonIgnore
    private String msg;
}
