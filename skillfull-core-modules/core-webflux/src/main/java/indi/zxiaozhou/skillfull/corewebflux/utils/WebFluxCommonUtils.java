// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.corewebflux.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 通用工具类
 *
 * @author zxiaozhou
 * @date 2020-08-27 15:22
 * @since JDK11
 */
@RequiredArgsConstructor
@Component
public class WebFluxCommonUtils {
    private static WebFluxCommonUtils util;


    @PostConstruct
    private void init() {
        util = this;
    }
}
