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
 * 系统基础配置常量
 *
 * @author zxiaozhou
 * @date 2020-10-20 17:19
 * @since JDK11
 */
public interface SysBaseConstant {
    /**
     * 用户登录信息缓存前缀(前缀+userId+'_'+systemId),获取的类型为UserAndAuthModel
     */
    String USER_LOGIN_PREFIX = "USER_LOGIN_";


    /**
     * 用户token密钥信息缓存前缀(前缀+systemId),获取的类型为TokenSecurityModel
     */
    String USER_TOKEN_SECURITY_PREFIX = "USER_TOKEN_SECURITY_";


    /**
     * 用户data密钥信息缓存前缀(前缀+systemId),获取的类型为UserEncryptionInfoModel
     */
    String USER_DATA_SECURITY_PREFIX = "USER_DATA_SECURITY_";


    /**
     * token Claim sub值key
     */
    String CLAIM_SUB_KEY = "sub";


    /**
     * 超级管理员角色
     */
    String SUPER_ROLE = SysBaseType.SUPER_ROLE.getType();


    /**
     * 系统基础配置缓存key
     */
    String SYSTEM_BASE = "SYSTEM_BASE";


    /**
     * websocket chart链接存储前缀(前缀+userId+'_'+systemId),值为登录信息redis key
     */
    String WEBSOCKET_CHART_CONNECT_PREFIX = "WEBSOCKET_CHART_CONNECT_";


    /**
     * websocket msg链接存储前缀(前缀+userId+'_'+systemId),值为登录信息redis key
     */
    String WEBSOCKET_MSG_CONNECT_PREFIX = "WEBSOCKET_MSG_CONNECT_";
}
