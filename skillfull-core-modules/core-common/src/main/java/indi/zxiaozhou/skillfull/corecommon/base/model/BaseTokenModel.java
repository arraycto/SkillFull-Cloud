// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.corecommon.base.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统token基础
 *
 * @author zxiaozhou
 * @date 2020-10-07 09:34
 * @since JDK11
 */
@Data
public class BaseTokenModel implements Serializable {
    private static final long serialVersionUID = -8035187351349997365L;

    /**
     * 令牌放入请求头key
     */
    private String tokenHeaderKey = "Authorization";

    /**
     * 请求头令牌前缀
     */
    private String tokenHeaderStartWith = "Bearer ";

    /**
     * token地址key
     */
    private String tokenQueryKey = "access-token";

    /**
     * 令牌过期时间(单位:秒)
     */
    private long tokenValidityInSeconds = 3600L;

    /**
     * 触发续期检测最小时间(单位:秒)
     */
    private long tokenDetectInSeconds = 1800L;

    /**
     * 续期时间(单位:秒)
     */
    private long tokenRenewInSeconds = 3600L;

    /**
     * 请求头刷新密钥标识
     */
    private String refreshSecurityKey = "refresh-security";
}
