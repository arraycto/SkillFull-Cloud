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

import indi.zxiaozhou.skillfull.corecommon.base.model.BaseSecretModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 验证码登录信息model
 *
 * @author zxiaozhou
 * @date 2020-10-20 21:58
 * @since JDK11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ValidateModel extends BaseSecretModel implements Serializable {
    private static final long serialVersionUID = 7375924342404576905L;
    /**
     * 验证码值
     */
    private String validateCode;
}
