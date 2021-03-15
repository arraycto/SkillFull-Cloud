// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.gateway.modules.manage.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import indi.zxiaozhou.skillfull.corecommon.annotation.ConstantType;
import indi.zxiaozhou.skillfull.corecommon.constant.ISuperType;
import indi.zxiaozhou.skillfull.corecommon.constant.model.ConstantDictModel;
import indi.zxiaozhou.skillfull.gateway.GatewayApplication;
import indi.zxiaozhou.skillfull.gateway.modules.manage.service.IToolService;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 工具类服务实现
 *
 * @author zxiaozhou
 * @date 2020-09-28 10:04
 * @since JDK11
 */
@Service
@Slf4j
public class ToolServiceImpl implements IToolService {
    private static final Map<String, List<ConstantDictModel>> CONSTANT_TYPE = new ConcurrentHashMap<>();


    @Override
    public List<ConstantDictModel> getConstantDict(String type) throws RuntimeException {
        if (CollectionUtil.isEmpty(CONSTANT_TYPE)) {
            log.debug("------------ToolServiceImpl------常量字段缓存信息不存在------>getConstantDict:{}", "正在重新获取");
            Reflections reflections = new Reflections(new ConfigurationBuilder()
                    .forPackages(GatewayApplication.class.getPackageName())
                    .addScanners(new SubTypesScanner())
                    .addScanners(new FieldAnnotationsScanner())
            );
            Set<Class<? extends ISuperType>> types = reflections.getSubTypesOf(ISuperType.class);
            for (Class<?> typeClass : types) {
                ConstantType annotation = typeClass.getAnnotation(ConstantType.class);
                if (typeClass.isEnum() && Objects.nonNull(annotation)) {
                    Object[] enumConstants = typeClass.getEnumConstants();
                    try {
                        Method getCode = typeClass.getMethod("getConstantDict");
                        @SuppressWarnings("unchecked")
                        List<ConstantDictModel> gatewayConstantDictDtoList = (List<ConstantDictModel>) getCode.invoke(enumConstants[0]);
                        CONSTANT_TYPE.put(annotation.value(), gatewayConstantDictDtoList);
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                        log.error("------------ToolServiceImpl------获取枚举下拉字典失败------>getConstantDict:{}", e.getMessage());
                    }
                }
            }
        }
        List<ConstantDictModel> constantDictDtoList = CONSTANT_TYPE.get(type);
        if (CollectionUtil.isEmpty(constantDictDtoList)) {
            constantDictDtoList = Collections.emptyList();
        }
        return constantDictDtoList;
    }
}
