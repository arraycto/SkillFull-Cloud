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

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 类型转换工具类
 *
 * @author zxiaozhou
 * @date 2019-04-01 16:51
 * @since JDK11
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class ConvertUtil {
    private static ConvertUtil util;
    private final ModelMapper modelMapper;


    @PostConstruct
    private void init() {
        util = this;
    }


    /**
     * 对象拷贝-同时实现Map to Map(创建目标对象并拷贝数据)
     *
     * @param source     ${@link Object} 原对象
     * @param targetType ${@link Class}  目标对象类型类
     * @author zxiaozhou
     * @date 2019-04-10 19:03
     */
    public static <T> T map(Object source, Class<T> targetType) {
        if (!(source instanceof Map)) {
            try {
                T t = targetType.getDeclaredConstructor().newInstance();
                if (t instanceof Map) {
                    objToMap(source, t);
                    return t;
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return util.modelMapper.map(source, targetType);
    }


    /**
     * 对象拷贝-同时实现Map to Map(拷贝数据,不创建目标对象)
     *
     * @param source ${@link Object} 原对象
     * @param target ${@link Object}  目标对象
     * @author zxiaozhou
     * @date 2019-04-29 15:24
     */
    public static void map(Object source, Object target) {
        if (!(source instanceof Map) && target instanceof Map) {
            objToMap(source, target);
        } else {
            util.modelMapper.map(source, target);
        }
    }


    /**
     * 实体或map参数拼接为get请求的地址参数
     *
     * @param source ${@link Object} 原对象
     * @return String ${@link String} get请求的参数字符串
     * @author zxiaozhou
     * @date 2019-04-24 12:32
     */
    public static String toGetParams(Object source) {
        StringBuilder sb = new StringBuilder();
        // 处理map
        JSONObject jsonObject = map(source, JSONObject.class);
        boolean flag = false;
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            if (!flag) {
                flag = true;
                sb.append(key).append("=").append(value);
            } else {
                sb.append("&").append(key).append("=").append(value);
            }
        }
        return sb.toString();
    }


    /**
     * json 字符串转为Java Bean
     *
     * @param jsonString ${@link String}
     * @param target     ${@link Class}
     * @author zxiaozhou
     * @date 2019-05-15 10:57
     */
    public static <T> T jsonStringToBean(Object jsonString, Class<T> target) {
        return Optional.ofNullable(jsonString).map(v -> {
            String str;
            if (jsonString instanceof String) {
                str = jsonString.toString();
            } else {
                str = JSONObject.toJSONString(jsonString, SerializerFeature.WriteMapNullValue);
            }
            return JSONObject.parseObject(str, target);
        }).orElse(null);
    }


    /**
     * Object 转为JSONObject
     *
     * @param source ${@link Object}  原数据
     * @return JSONObject ${@link JSONObject}
     * @author zxiaozhou
     * @date 2019-05-10 22:53
     */
    public static JSONObject toJSONObject(Object source) {
        return Optional.ofNullable(source).map(v -> {
            String jsonString = JSONObject.toJSONString(source, SerializerFeature.WriteMapNullValue);
            return JSON.parseObject(jsonString);
        }).orElse(new JSONObject());
    }


    /**
     * 实体转map
     *
     * @param source ${@link Object} 原对象
     * @param target ${@link Object} 目标map
     * @author zxiaozhou
     * @date 2019-04-03 14:08
     */
    @SuppressWarnings("unchecked")
    private static void objToMap(Object source, Object target) {
        if (source == null) {
            return;
        }
        if (target instanceof Map) {
            Map<Object, Object> map = (Map<Object, Object>) target;
            Field[] fields = source.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    Field f = source.getClass().getDeclaredField(field.getName());
                    f.setAccessible(true);
                    Object o = f.get(source);
                    map.put(field.getName(), o);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 一个List<A>转另一个List<B>
     *
     * @param sources    ${@link List<Object>}
     * @param targetType ${@link Class<T>}
     * @return List<T> ${@link List<T>}
     * @author zxiaozhou
     * @date 2019-09-11 10:51
     */
    public static <T> List<T> listAToListB(@NotNull List<?> sources, @NotNull Class<T> targetType) {
        if (CollectionUtil.isNotEmpty(sources) && Objects.nonNull(targetType)) {
            String jsonString = JSONObject.toJSONString(sources);
            return JSONObject.parseArray(jsonString, targetType);
        }
        return new ArrayList<>();
    }


}

