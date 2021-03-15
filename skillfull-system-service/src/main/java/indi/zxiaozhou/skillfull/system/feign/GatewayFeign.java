// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.feign;

import indi.zxiaozhou.skillfull.corecommon.base.Result;
import indi.zxiaozhou.skillfull.corecommon.feign.FeignFallback;
import indi.zxiaozhou.skillfull.system.feign.dto.GatewayConstantDictDto;
import indi.zxiaozhou.skillfull.system.feign.dto.GatewayServiceRouteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 网关feign
 *
 * @author zxiaozhou
 * @date 2020-09-12 16:54
 * @since JDK11
 */
@FeignClient(value = "gateway-service", fallbackFactory = FeignFallback.class)
public interface GatewayFeign {

    /**
     * 获取网关常量字典
     *
     * @param type ${@link String} 字典类型: PredicateType--断言,SecurityType--过滤器
     * @return ConstantDictModel>> ${@link GatewayConstantDictDto >>}
     * @author zxiaozhou
     * @date 2020-09-12 17:13
     */
    @GetMapping("/gateway/tools/select/constant/dict/{type}")
    Result<List<GatewayConstantDictDto>> getConstantDict(@PathVariable String type);


    /**
     * 获取网关路由
     *
     * @return List<GatewayServiceRouteDto> ${@link List< GatewayServiceRouteDto >} 结果
     * @author zxiaozhou
     * @date 2020-09-15 17:03
     */
    @GetMapping("/gateway/route/select/list")
    Result<List<GatewayServiceRouteDto>> getRoutes();
}
