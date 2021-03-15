// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.core.constant;

/**
 * 通用常量
 *
 * @author zxiaozhou
 * @date 2020-07-09 16:52
 * @since JDK11
 */
public interface SystemCommonConstant {

    /**
     * 图片验证码key前缀
     */
    String PICTURE_CODE_KEY_PREFIX = "PICTURE_CODE_";

    /**
     * 短信验证码key前缀
     */
    String SMS_CODE_KEY_PREFIX = "SMS_CODE_";


    /**
     * 超级管理员角色数据编码
     */
    String SUPER_ROLE_CODE = "super_admin";

    /**
     * 角色前缀
     */
    String ROLE_PREFIX = "role_";

    /**
     * 超级管理员角色完整信息
     */
    String ROLE_SUPER = ROLE_PREFIX + SUPER_ROLE_CODE;

    /**
     * 匿名用户
     */
    String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

    /**
     * 斜杠
     */
    String SLASH = "/";

    /**
     * 逗号
     */
    String COMMA = ",";

    /**
     * 分号
     */
    String SEMICOLON = ";";
}
