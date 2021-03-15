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

import indi.zxiaozhou.skillfull.corecommon.base.Result;
import indi.zxiaozhou.skillfull.gateway.feign.SystemFeign;
import indi.zxiaozhou.skillfull.gateway.modules.manage.service.ISwaggerService;
import indi.zxiaozhou.skillfull.gateway.modules.manage.service.dto.SwaggerInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * swagger聚合路由查询
 *
 * @author zxiaozhou
 * @date 2020-09-14 02:37
 * @since JDK11
 */
@Service
@RequiredArgsConstructor
public class SwaggerServiceImpl implements ISwaggerService {
    private final SystemFeign systemFeign;

    @Override
    public List<SwaggerInfoDto> getSwaggerInfo() throws RuntimeException {
        try {
            Result<List<SwaggerInfoDto>> swaggerInfo = systemFeign.getSwaggerInfo();
            if (swaggerInfo.isSuccess()) {
                return swaggerInfo.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
