// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.manage.service;

import indi.zxiaozhou.skillfull.system.feign.dto.GatewayConstantDictDto;

import java.util.List;

/**
 * 网关feign部分接口二次封装
 *
 * @author zxiaozhou
 * @date 2020-09-12 21:06
 * @since JDK1.8
 */
public interface IGatewayFeignService {
    /**
     * 获取网关常量字典
     *
     * @param type ${@link String} 类型
     * @return List<ConstantDictModel> ${@link List< GatewayConstantDictDto >}
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-12 21:08
     */
    List<GatewayConstantDictDto> getConstantDict(String type) throws RuntimeException;
}
