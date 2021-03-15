// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.manage.service.impl;

import indi.zxiaozhou.skillfull.corecommon.base.Result;
import indi.zxiaozhou.skillfull.system.core.constant.SystemCache;
import indi.zxiaozhou.skillfull.system.feign.GatewayFeign;
import indi.zxiaozhou.skillfull.system.feign.dto.GatewayConstantDictDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.IGatewayFeignService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 网关feign部分接口二次封装实现
 *
 * @author zxiaozhou
 * @date 2020-09-12 21:07
 * @since JDK1.8
 */
@Service
@RequiredArgsConstructor
public class GatewayFeignServiceImpl implements IGatewayFeignService {
    private final GatewayFeign gatewayFeign;


    @Override
    @Cacheable(value = SystemCache.GATEWAY_CONSTANT_DICT, key = "#type")
    public List<GatewayConstantDictDto> getConstantDict(String type) throws RuntimeException {
        Result<List<GatewayConstantDictDto>> constantDict = gatewayFeign.getConstantDict(type);
        if (constantDict.isSuccess()) {
            return constantDict.getData();
        }
        return Collections.emptyList();
    }
}
