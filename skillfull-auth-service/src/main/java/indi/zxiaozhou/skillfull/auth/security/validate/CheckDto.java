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

import lombok.Data;

import java.io.Serializable;

/**
 * 验证结果
 *
 * @author zxiaozhou
 * @date 2020-06-30 16:35
 * @since JDK11
 */
@Data
public class CheckDto implements Serializable {
    private static final long serialVersionUID = -5032945213213317228L;
    /**
     * 验证结果
     */
    private boolean result = false;

    /**
     * 验证结果信息
     */
    private String msg;
}
