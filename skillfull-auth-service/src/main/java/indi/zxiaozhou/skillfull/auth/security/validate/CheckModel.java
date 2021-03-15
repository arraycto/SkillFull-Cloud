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

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 验证码验证model
 *
 * @author zxiaozhou
 * @date 2020-06-29 11:07
 * @since JDK11
 */
@Data
@AllArgsConstructor
public class CheckModel {
    /**
     * 验证码id
     */
    private String codeId;

    /**
     * 验证码value
     */
    private String codeValue;
}
