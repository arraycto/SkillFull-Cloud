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
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户数据解密信息
 *
 * @author zxiaozhou
 * @date 2020-10-07 09:13
 * @since JDK11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthUserDataSecurityModel extends BaseSecretModel implements Serializable {
    private static final long serialVersionUID = -2147772099404900857L;
}
