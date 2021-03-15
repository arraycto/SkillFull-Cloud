// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.user.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

import static indi.zxiaozhou.skillfull.corecommon.constant.CommonConstant.TIME_ZONE_GMT8;

/**
 * token信息,用户登录返回给前端
 *
 * @author zxiaozhou
 * @date 2020-09-28 23:46
 * @since JDK11
 */
@Data
public class TokenInfo {
    @ApiModelProperty(name = "token", value = "令牌信息")
    private String token;

    @ApiModelProperty(name = "expiresTime", value = "令牌有效时间(单位s)")
    private Long expiresTime;

    @ApiModelProperty(name = "expiresAt", value = "令牌有效时间止")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = TIME_ZONE_GMT8)
    private Date expiresAt;

    @ApiModelProperty(name = "tokenHeaderKey", value = "令牌放入请求头key")
    private String tokenHeaderKey;

    @ApiModelProperty(name = "tokenHeaderStartWith", value = "请求头令牌前缀")
    private String tokenHeaderStartWith;

    @ApiModelProperty(name = "tokenType", value = "令牌类型")
    private String tokenType;

    @ApiModelProperty(name = "tokenQueryKey", value = "令牌放入地址时key")
    private String tokenQueryKey;

    @ApiModelProperty(name = "refreshTokenKey", value = "刷新令牌标识")
    private String refreshTokenKey;

    @ApiModelProperty(name = "refreshSecurityKey", value = "请求头刷新密钥标识")
    private String refreshSecurityKey;

    @ApiModelProperty(name = "securityKey", value = "数据解密密钥key")
    private String securityKey;

    @ApiModelProperty(name = "securityCipherKey", value = " 数据密文key")
    private String securityCipherKey;
}
