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
 * 密钥相关基础
 *
 * @author zxiaozhou
 * @date 2020-10-20 21:47
 * @since JDK11
 */
@Data
public class BaseSecretModel implements Serializable {
    private static final long serialVersionUID = 4280855850409329888L;

    /**
     * 私有
     */
    private String privateKey;

    /**
     * 公钥
     */
    private String publicKey;


}
