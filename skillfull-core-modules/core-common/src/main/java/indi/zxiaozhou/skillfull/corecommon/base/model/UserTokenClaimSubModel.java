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
 * token sub信息
 *
 * @author zxiaozhou
 * @date 2020-10-20 16:36
 * @since JDK11
 */
@Data
public class UserTokenClaimSubModel implements Serializable {
    private static final long serialVersionUID = 6925442851885369086L;

    /**
     * 系统标识
     */
    private String systemId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 授权环境
     */
    private String profilesActive;
}
