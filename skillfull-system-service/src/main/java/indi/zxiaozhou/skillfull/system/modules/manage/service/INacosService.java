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

import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.*;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.NacosServiceInfoDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ServiceInstanceDto;

import java.util.List;

/**
 * nacos open api接口二次封装
 *
 * @author zxiaozhou
 * @date 2020-10-11 10:50
 * @since JDK1.8
 */
public interface INacosService {

    /**
     * 订阅服务变化监听
     *
     * @param vo ${@link NacosSubscribeVo} 查询条件
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-10-11 13:03
     */
    void subscribe(NacosSubscribeVo vo) throws RuntimeException;


    /**
     * 取消服务变化订阅
     *
     * @param vo ${@link NacosSubscribeVo} 查询条件
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-10-11 13:03
     */
    void unsubscribe(NacosSubscribeVo vo) throws RuntimeException;


    /**
     * 查询某个服务所有实例
     *
     * @param vo ${@link NacosAllInstancesQueryVo} 查询条件
     * @return ServiceInstanceDto ${@link ServiceInstanceDto} 实例
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-10-11 13:03
     */
    ServiceInstanceDto getAllInstances(NacosAllInstancesQueryVo vo) throws RuntimeException;


    /**
     * 查询某个组的所有服务以及服务实例
     *
     * @param vo ${@link NacosGroupNameVo} 查询条件
     * @return ServiceInstanceDto ${@link ServiceInstanceDto} 实例
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-10-11 13:03
     */
    List<ServiceInstanceDto> getAllServices(NacosGroupNameVo vo) throws RuntimeException;


    /**
     * 获取已经注册的服务
     *
     * @param vo ${@link NacosGroupNameVo} 查询条件
     * @return List<String> ${@link List<String>}
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-10-11 13:21
     */
    List<NacosServiceInfoDto> getServices(NacosGroupNameVo vo) throws RuntimeException;


    /**
     * 通过条件查询某个服务实例
     *
     * @param vo ${@link NacosSelectInstancesVo} 查询条件
     * @return ServiceInstanceDto ${@link ServiceInstanceDto} 实例
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-10-11 13:21
     */
    ServiceInstanceDto selectInstances(NacosSelectInstancesVo vo) throws RuntimeException;


    /**
     * 删除服务
     *
     * @param vo ${@link NacosDeleteInstanceVo} 查询条件
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-10-11 13:41
     */
    void deleteService(NacosDeleteInstanceVo vo) throws RuntimeException;


    /**
     * 服务实例上下线
     *
     * @param vo ${@link NacosUpdateInstanceVo} 查询条件
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-10-11 13:43
     */
    void updateInstance(NacosUpdateInstanceVo vo) throws RuntimeException;


    /**
     * 删除服务实例
     *
     * @param vo ${@link NacosDeregisterInstanceVo} 查询条件
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-10-11 13:45
     */
    void deregisterInstance(NacosDeregisterInstanceVo vo) throws RuntimeException;
}
