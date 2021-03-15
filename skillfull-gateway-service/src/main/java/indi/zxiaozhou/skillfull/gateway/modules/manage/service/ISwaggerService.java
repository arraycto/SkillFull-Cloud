// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.gateway.modules.manage.service;

import indi.zxiaozhou.skillfull.gateway.modules.manage.service.dto.SwaggerInfoDto;

import java.util.List;

/**
 * swagger聚合处理
 *
 * @author zxiaozhou
 * @date 2020-09-14 02:32
 * @since JDK11
 */
public interface ISwaggerService {
    /**
     * 获取swagger信息
     *
     * @return List<SwaggerInfoDto> ${@link List<SwaggerInfoDto>} 结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-14 02:37
     */
    List<SwaggerInfoDto> getSwaggerInfo() throws RuntimeException;
}
