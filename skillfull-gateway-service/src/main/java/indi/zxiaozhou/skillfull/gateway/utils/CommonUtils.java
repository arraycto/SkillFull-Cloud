// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.gateway.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 通用工具类
 *
 * @author zxiaozhou
 * @date 2020-08-27 15:22
 * @since JDK11
 */
public class CommonUtils {
    private static final char SEPARATOR = '_';

    private static final String UNKNOWN = "unknown";

    /**
     * 获取堆栈信息
     *
     * @param throwable ${@link Throwable} 异常信息
     * @return String ${@link String} 处理结果
     * @author zxiaozhou
     * @date 2020-08-27 15:22
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }
}
