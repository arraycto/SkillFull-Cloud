// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.coremvc.utils;

import indi.zxiaozhou.skillfull.corecommon.base.model.BaseTokenModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

import static indi.zxiaozhou.skillfull.corecommon.constant.SysBaseConstant.SYSTEM_BASE;

/**
 * core mvc通用工具类
 *
 * @author zxiaozhou
 * @date 2020-08-27 15:22
 * @since JDK11
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class CoreMvcUtils {
    private static CoreMvcUtils util;

    private final RedisTemplate<String, Object> redisTemplate;


    /**
     * 获取系统基础信息
     *
     * @return BaseTokenModel ${@link BaseTokenModel}
     * @author zxiaozhou
     * @date 2020-10-08 14:40
     */
    public static BaseTokenModel getSystemBaseInfo() {
        try {
            Object object = util.redisTemplate.opsForValue().get(SYSTEM_BASE);
            if (Objects.nonNull(object)) {
                return (BaseTokenModel) object;
            }
        } catch (Exception e) {
            log.error("------------CoreMvcUtils------从redis获取系统基础配置失败------>getSystemBaseInfo--->\n异常消息:{}", e.getMessage());
        }
        return new BaseTokenModel();
    }


    @PostConstruct
    private void init() {
        util = this;
    }
}
