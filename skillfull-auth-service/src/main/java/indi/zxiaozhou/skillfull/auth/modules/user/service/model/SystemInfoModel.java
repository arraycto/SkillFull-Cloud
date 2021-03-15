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

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统标识信息
 *
 * @author zxiaozhou
 * @date 2020-10-07 10:20
 * @since JDK11
 */
@Data
public class SystemInfoModel implements Serializable {
    private static final long serialVersionUID = -1113658999304028580L;

    @ApiModelProperty(name = "tokenHeaderKey", value = "token请求头key")
    private String tokenHeaderKey;

    @ApiModelProperty(name = "tokenStartWith", value = "令牌前缀(与token拼接放入请求头)")
    private String tokenStartWith;

    @ApiModelProperty(name = "tokenType", value = "token类型")
    private String tokenType;

    @ApiModelProperty(name = "tokenGetKey", value = "token放入get时key")
    private String tokenGetKey;

    @ApiModelProperty(name = "refreshTokenKey", value = "刷新token标识")
    private String refreshTokenKey;

    @ApiModelProperty(name = "refreshSecurityKey", value = "刷新密钥标记")
    private String refreshSecurityKey = "refresh-security";

    @ApiModelProperty(name = "securityKey", value = "解密密钥放入请求头标记")
    private String securityKey = "security";
}
