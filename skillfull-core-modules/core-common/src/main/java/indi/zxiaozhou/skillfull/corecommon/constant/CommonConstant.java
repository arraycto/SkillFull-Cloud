// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.corecommon.constant;

/**
 * 全局公共常量
 *
 * @author zxiaozhou
 * @date 2020-07-23 19:57
 * @since JDK11
 */
public interface CommonConstant {
    /**
     * 斜杠
     */
    String SLASH = "/";


    /**
     * 点
     */
    String POINT = ".";


    /**
     * 时区
     */
    String TIME_ZONE_GMT8 = "GMT+8";

    /**
     * 测试缓存key
     */
    String TEST_DEMO_CACHE = "test:demo";

    /**
     * 开发环境标记
     */
    String DEV = "DEV";

    /**
     * 测试环境标记
     */
    String TEST = "TEST";

    /**
     * 正式环境标记
     */
    String PRO = "PRO";


    /**
     * 退出登录地址
     */
    String LOGIN_OUT = UrlPrefix.AUTH + "/user/login/logout";

    /**
     * 账号密码登录地址
     */
    String PASSWORD_LOGIN = UrlPrefix.AUTH + "/user/login/picture";

    /**
     * 微信登录地址
     */
    String WEI_XIN_LOGIN = UrlPrefix.AUTH + "/user/login/wx";

    /**
     * 短信验证码登录地址
     */
    String SMS_LOGIN = UrlPrefix.AUTH + "/user/login/sms";
}
