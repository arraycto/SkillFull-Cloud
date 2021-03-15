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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间处理工具类
 *
 * @author zxiaozhou
 * @date 2019-04-01 16:55
 * @since JDK11
 */
public class CoreCommonDateUtils {
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FORMATYYYYLMMYDDR = "yyyy年MM月dd日";
    public static final String FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * Date转LocalDateTime
     *
     * @param date ${@link Date}
     * @return LocalDateTime ${@link LocalDateTime}
     * @author zxiaozhou
     * @date 2020-10-19 19:56
     */
    public static LocalDateTime dateTimeToLocalDateTime(Date date) {
        return null;
    }


    /**
     * LocalDateTime转Date
     *
     * @param localDateTime ${@link LocalDateTime} LocalDateTime时间
     * @return Date ${@link Date}
     * @author zxiaozhou
     * @date 2020-10-19 19:57
     */
    public static Date localDateTimeToDateTime(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获取今天的字符串日期
     *
     * @param format ${@link String} 格式
     * @return String ${@link String} 结果
     * @author zxiaozhou
     * @date 2020-08-28 15:01
     */
    public static String getNowStrDate(String format) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return dateToStr(localDateTime, format);
    }


    /**
     * 时间格式化为字符串
     *
     * @param localDateTime ${@link LocalDateTime} 时间
     * @param format        ${@link String} 格式
     * @return String ${@link String}
     * @author zxiaozhou
     * @date 2020-08-28 15:10
     */
    public static String dateToStr(LocalDateTime localDateTime, String format) {
        return DateTimeFormatter.ofPattern(format).format(localDateTime);
    }


    /**
     * 字符串转时间
     *
     * @param strDate ${@link String} 日期
     * @param format  ${@link String} 格式
     * @return LocalDateTime ${@link LocalDateTime} 时间
     * @author zxiaozhou
     * @date 2020-08-28 15:11
     */
    public static LocalDateTime strToDate(String strDate, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(strDate, dateTimeFormatter);
    }


    /**
     * 计算年龄
     *
     * @param birthDay ${@link LocalDateTime} 日期
     * @author zxiaozhou
     * @date 2019-12-24 19:33
     */
    public static int getAge(LocalDateTime birthDay) {
        LocalDate birthLocalDate = birthDay.toLocalDate();
        LocalDate nowLocalDate = LocalDate.now();
        return birthLocalDate.until(nowLocalDate).getYears();
    }


    /**
     * 分钟加减
     *
     * @param localDateTime ${@link LocalDateTime} 时间
     * @param num           ${@link Integer} 加或减分钟数
     * @return Date ${@link Date} 结果
     * @author zxiaozhou
     * @date 2019-12-15 01:56
     */
    public static LocalDateTime minuteAdd(LocalDateTime localDateTime, int num) {
        return localDateTime.plusMinutes(num);
    }


    /**
     * 小时加减
     *
     * @param localDateTime ${@link LocalDateTime} 时间
     * @param num           ${@link Integer} 加或减小时数
     * @return LocalDateTime ${@link LocalDateTime} 结果
     * @author zxiaozhou
     * @date 2019-12-15 01:56
     */
    public static LocalDateTime hoursAdd(LocalDateTime localDateTime, int num) {
        return localDateTime.plusHours(num);
    }


    /**
     * 天加减
     *
     * @param localDateTime ${@link LocalDateTime} 时间
     * @param num           ${@link Integer} 加或减天数
     * @return LocalDateTime ${@link LocalDateTime} 结果
     * @author zxiaozhou
     * @date 2019-12-15 01:56
     */
    public static LocalDateTime dateAdd(LocalDateTime localDateTime, int num) {
        return localDateTime.plusDays(num);
    }


    /**
     * 周加减
     *
     * @param localDateTime ${@link LocalDateTime} 时间
     * @param num           ${@link Integer} 加或减周数
     * @return LocalDateTime ${@link LocalDateTime} 结果
     * @author zxiaozhou
     * @date 2019-12-15 01:56
     */
    public static LocalDateTime weekAdd(LocalDateTime localDateTime, int num) {
        return localDateTime.plusWeeks(num);
    }


    /**
     * 月加减
     *
     * @param localDateTime ${@link LocalDateTime} 时间
     * @param num           ${@link Integer} 加或减月数
     * @return LocalDateTime ${@link LocalDateTime} 结果
     * @author zxiaozhou
     * @date 2019-12-15 01:56
     */
    public static LocalDateTime monthAdd(LocalDateTime localDateTime, int num) {
        return localDateTime.plusMonths(num);
    }


    /**
     * 年加减
     *
     * @param localDateTime ${@link LocalDateTime} 时间
     * @param num           ${@link Integer} 加或减年数
     * @return LocalDateTime ${@link LocalDateTime} 结果
     * @author zxiaozhou
     * @date 2019-12-15 01:56
     */
    public static LocalDateTime yearsAdd(LocalDateTime localDateTime, int num) {
        return localDateTime.plusYears(num);
    }
}
