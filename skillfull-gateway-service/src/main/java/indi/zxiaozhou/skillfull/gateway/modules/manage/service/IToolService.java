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

import indi.zxiaozhou.skillfull.corecommon.constant.model.ConstantDictModel;

import java.util.List;

/**
 * 工具了服务
 *
 * @author zxiaozhou
 * @date 2020-09-28 10:03
 * @since JDK11
 */
public interface IToolService {

    /**
     * 获取网关常量字典
     *
     * @param type ${@link String} 字典类型: PredicateType--断言,FilterType--过滤器,LbType--负载均衡类型,ProtocolType--传输协议类型
     * @return List<ConstantDictModel> ${@link List <ConstantDictModel>}
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-12 17:01
     */
    List<ConstantDictModel> getConstantDict(String type) throws RuntimeException;
}
