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
 * REST API 状态码接口
 *
 * @author zxiaozhou
 * @date 2020-06-22 16:40
 * @since JDK11
 */
public interface IStatus {

    /**
     * 状态码
     *
     * @return Integer ${@link Integer} 状态码
     * @author zxiaozhou
     * @date 2020-06-22 16:37
     */
    Integer getCode();


    /**
     * 返回信息
     *
     * @return String ${@link String} 返回信息
     * @author zxiaozhou
     * @date 2020-06-22 16:37
     */
    String getMessage();


    /**
     * http状态码
     *
     * @return Integer ${@link Integer} http状态码
     * @author zxiaozhou
     * @date 2020-06-22 16:37
     */
    Integer getStatus();

}