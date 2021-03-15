// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.corecommon.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.base.CaseFormat;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 系统工具类
 *
 * @author zxiaozhou
 * @date 2020-09-25 09:34
 * @since JDK11
 */
@Slf4j
public class CoreCommonUtils {

    /**
     * 打印系统关键信息
     *
     * @author zxiaozhou
     * @date 2019-10-24 10:59
     */
    public static void printSysInfo(ConfigurableApplicationContext application) {
        String ip = "localhost";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Environment env = application.getEnvironment();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        if (StringUtils.isBlank(path)) {
            path = "";
        }
        String profilesActive = env.getProperty("spring.profiles.active");
        String version = env.getProperty("spring.application.version");
        String projectName = env.getProperty("spring.application.name");
        log.info("\n------------------------------------------------------------------------------\n"
                + "Application " + projectName + " v" + version + " " + profilesActive + " is running! Access URLs:\n"
                + "\tApi-Url:\t\t\thttp://" + ip + ":" + port + path + "\n"
                + "\tSwagger-Ui:\t\t\thttp://" + ip + ":" + port + path + "/swagger-ui/index.html\n"
                + "------------------------------------------------------------------------------");
    }


    /**
     * 文件大小格式化
     *
     * @param size ${@link Long} 文件大小
     * @return String ${@link String} 返回格式化的文件大小
     * @author zxiaozhou
     * @date 2020-10-23 14:47
     */
    public static String getFormatFileSize(long size) {
        String fileSize;
        String unit;
        DecimalFormat df = new DecimalFormat("#.00");
        if (size < (1 << 10)) {
            fileSize = df.format((double) size);
            unit = "B";
        } else if (size < (1 << 20)) {
            fileSize = df.format((double) size / (1 << 10));
            unit = "KB";
        } else if (size < (1 << 30)) {
            fileSize = df.format((double) size / (1 << 20));
            unit = "MB";
        } else {
            fileSize = df.format((double) size / (1 << 30));
            unit = "GB";
        }
        return fileSize + unit;
    }


    /**
     * 获取文件md5值
     *
     * @param inputStream ${@link InputStream}
     * @return String ${@link String}
     * @author zxiaozhou
     * @date 2020-10-23 14:50
     */
    public static String getFileMd5Hex(InputStream inputStream) {
        String md5 = "";
        try {
            md5 = DigestUtils.md5Hex(inputStream);
        } catch (IOException e) {
            log.error("------------CoreCommonUtils------------>getFileMd5Hex:{}", "获取文件md5值失败");
        }
        return md5;
    }


    /**
     * 对象转json string(保留空键)
     *
     * @param object ${@link Object} 待处理对象
     * @return String ${@link String} 处理结果
     * @author zxiaozhou
     * @date 2020-12-31 15:12
     */
    public static String objectToJsonStr(Object object) {
        String result = "";
        if (Objects.nonNull(object)) {
            result = JSONObject.toJSONString(object, SerializerFeature.WriteMapNullValue);
        }
        return result;
    }


    /**
     * json字符串转实体
     *
     * @param jsonStr ${@link String} 待转换数据
     * @param cla     ${@link Class<T>} 目标对象类型类
     * @return T  处理结果
     * @author zxiaozhou
     * @date 2021-01-08 13:21
     */
    public static <T> T jsonStrToObject(String jsonStr, Class<T> cla) {
        if (StringUtils.isNotBlank(jsonStr)) {
            return JSONObject.parseObject(jsonStr, cla);
        }
        return null;
    }


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

    /**
     * 驼峰命名转下划线
     *
     * @param str ${@link String} 驼峰
     * @return String ${@link String} 下划线
     * @author zxiaozhou
     * @date 2019-08-01 13:54
     */
    public static String humpToUnderline(String str) {
        if (StringUtils.isNotBlank(str)) {
            return CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE).convert(str);
        }
        return null;
    }


    /**
     * 下划线转驼峰
     *
     * @param str ${@link String}
     * @return String ${@link String}
     * @author zxiaozhou
     * @date 2019-08-01 13:58
     */
    public static String underlineToHump(String str) {
        if (StringUtils.isNotBlank(str)) {
            return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, str);
        }
        return null;
    }

    /**
     * 获取get中参数
     *
     * @param queryStr ${@link String} 待解析内容
     * @return Map<String, String>  ${@link Map<String, String> } 解析结果
     * @author zxiaozhou
     * @date 2021-01-08 14:36
     */
    public static Map<String, String> getQueryMap(String queryStr) {
        Map<String, String> queryMap = new HashMap<>(8);
        if (!org.springframework.util.StringUtils.isEmpty(queryStr)) {
            String[] queryParam = queryStr.split("&");
            Arrays.stream(queryParam).forEach(s -> {
                String[] kv = s.split("=", 2);
                String value = kv.length == 2 ? kv[1] : "";
                queryMap.put(kv[0], value);
            });
        }
        return queryMap;
    }


}
