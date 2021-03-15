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


import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * 通用状态码
 *
 * @author zxiaozhou
 * @date 2020-06-22 16:35
 * @since JDK11
 */
@Getter
@ToString
public enum Status implements IStatus {
    //------------------------成功----------------
    /**
     * 操作成功！
     */
    SUCCESS(0, "操作成功！", HttpStatus.OK.value()),

    /**
     * 退出成功！
     */
    LOGOUT(0, "退出成功！", HttpStatus.OK.value()),

    //------------------------需要重新登录----------------

    /**
     * 登录过期
     */
    TOKEN_EXPIRED(4001, "登录过期，请重新登录！", HttpStatus.UNAUTHORIZED.value()),


    //------------------------操作异常------------------
    /**
     * 操作异常！
     */
    ERROR(5000, "操作异常！", HttpStatus.INTERNAL_SERVER_ERROR.value()),

    /**
     * 暂无权限访问！
     */
    ACCESS_DENIED(4003, "权限不足！", HttpStatus.FORBIDDEN.value()),

    /**
     * 请求不存在！
     */
    REQUEST_NOT_FOUND(4004, "请求不存在！", HttpStatus.NOT_FOUND.value()),

    /**
     * 数据库操作失败
     */
    DATABASE_BASE_ERROR(5000, "数据库操作失败", HttpStatus.INTERNAL_SERVER_ERROR.value()),

    /**
     * 验证失败
     */
    VERIFICATION_FAILED(5000, "验证失败", HttpStatus.INTERNAL_SERVER_ERROR.value()),

    /**
     * 调用第三方接口失败
     */
    API_ERROR(5003, "API接口调用失败", HttpStatus.SERVICE_UNAVAILABLE.value());

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 返回信息
     */
    private final String message;

    /**
     * http状态码
     */
    private final Integer status;

    Status(Integer code, String message, Integer status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
