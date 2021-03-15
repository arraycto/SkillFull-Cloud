// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.gateway.feign;

import indi.zxiaozhou.skillfull.corecommon.base.Result;
import indi.zxiaozhou.skillfull.gateway.core.FeignFallback;
import indi.zxiaozhou.skillfull.gateway.modules.manage.service.dto.SwaggerInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 网关feign
 *
 * @author zxiaozhou
 * @date 2020-09-12 16:54
 * @since JDK11
 */
@FeignClient(value = "system-boot", fallbackFactory = FeignFallback.class)
public interface SystemFeign {

    /**
     * 获取swagger配置
     *
     * @return Result<List < SwaggerInfoDto>> ${@link Result<List<SwaggerInfoDto>>}
     * @author zxiaozhou
     * @date 2020-09-12 17:13
     */
    @GetMapping("/system/manage/service/select/swagger-info")
    Result<List<SwaggerInfoDto>> getSwaggerInfo();
}
