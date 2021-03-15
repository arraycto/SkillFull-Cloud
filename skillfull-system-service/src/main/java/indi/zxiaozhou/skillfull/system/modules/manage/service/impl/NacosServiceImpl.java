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

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingMaintainService;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import indi.zxiaozhou.skillfull.corecommon.utils.ConvertUtil;
import indi.zxiaozhou.skillfull.coremvc.config.properties.AppProperty;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.*;
import indi.zxiaozhou.skillfull.system.modules.manage.service.ICustomNacosNamingService;
import indi.zxiaozhou.skillfull.system.modules.manage.service.INacosService;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.NacosServiceInfoDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ServiceInstanceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * nacos open api接口二次封装实现
 *
 * @author zxiaozhou
 * @date 2020-10-11 11:13
 * @since JDK1.8
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NacosServiceImpl implements INacosService {
    private final NamingService namingService;
    private final NamingMaintainService namingMaintainService;
    private final NacosDiscoveryProperties properties;
    private final NacosEventListener nacosEventListener;
    private final AppProperty property;
    private final ICustomNacosNamingService customNacosNamingService;


    @Override
    public void subscribe(NacosSubscribeVo vo) throws RuntimeException {
        String groupName = vo.getGroupName();
        String serviceName = vo.getServiceName();
        if (StringUtils.isBlank(groupName)) {
            groupName = properties.getGroup();
            if (StringUtils.isBlank(groupName)) {
                groupName = Constants.DEFAULT_GROUP;
            }
        }
        try {
            namingService.unsubscribe(serviceName, groupName, nacosEventListener);
            namingService.subscribe(serviceName, groupName, nacosEventListener);
        } catch (NacosException e) {
            e.printStackTrace();
            log.error("------------INacosServiceImpl------订阅服务变更失败------>subscribe:groupName-{},serviceName-{},errMsg-{}", groupName, serviceName, e.getErrMsg());
            throw new ResponseException(Status.ERROR, "订阅" + serviceName + "变更通知异常:" + e.getErrMsg());
        }
    }


    @Override
    public void unsubscribe(NacosSubscribeVo vo) throws RuntimeException {
        String groupName = vo.getGroupName();
        String serviceName = vo.getServiceName();
        if (StringUtils.isBlank(groupName)) {
            groupName = properties.getGroup();
        }
        try {
            namingService.unsubscribe(serviceName, groupName, nacosEventListener);
        } catch (NacosException e) {
            e.printStackTrace();
            log.error("------------INacosServiceImpl------取消服务变化订阅------>unsubscribe:groupName-{},serviceName-{},errMsg-{}", groupName, serviceName, e.getErrMsg());
            throw new ResponseException(Status.ERROR, "取消" + serviceName + "服务变化订阅异常:" + e.getErrMsg());
        }
    }


    @Override
    public ServiceInstanceDto getAllInstances(NacosAllInstancesQueryVo vo) throws RuntimeException {
        String groupName = vo.getGroupName();
        String serviceName = vo.getServiceName();
        List<Instance> allInstances = customNacosNamingService.getAllInstances(serviceName, groupName, null);
        return createServiceInstanceDto(allInstances);
    }

    /**
     * 服务实例信息转为自定义格式
     *
     * @param instances ${@link List<Instance>} 待转换实例
     * @return ServiceInstanceDto ${@link ServiceInstanceDto} 转换后结果
     * @author zxiaozhou
     * @date 2020-10-11 15:32
     */
    private ServiceInstanceDto createServiceInstanceDto(List<Instance> instances) {
        ServiceInstanceDto serviceInstanceDto = new ServiceInstanceDto();
        if (CollectionUtil.isNotEmpty(instances)) {
            String[] serviceNameInfos = instances.get(0).getServiceName().split("@@");
            String serviceName = serviceNameInfos[serviceNameInfos.length - 1];
            serviceInstanceDto.setServiceName(serviceName);
            serviceInstanceDto.setInstanceNum(instances.size());
            int healthyNum = 0;
            int enabledNum = 0;
            int ephemeralNum = 0;
            List<ServiceInstanceDto.ServiceInstanceDetail> serviceInstanceDetails = new ArrayList<>();
            Map<String, Instance> instanceMap = new HashMap<>(4);
            for (Instance instance : instances) {
                ServiceInstanceDto.ServiceInstanceDetail instanceDetail = ConvertUtil.map(instance, ServiceInstanceDto.ServiceInstanceDetail.class);
                instanceDetail.setServiceName(serviceName);
                instance.setServiceName(serviceName);
                serviceInstanceDetails.add(instanceDetail);
                instanceMap.put(instance.getInstanceId(), instance);
                if (instance.isEnabled()) {
                    enabledNum++;
                }
                if (instance.isEphemeral()) {
                    ephemeralNum++;
                }
                if (instance.isHealthy()) {
                    healthyNum++;
                }
            }
            serviceInstanceDto.setHealthyNum(healthyNum);
            serviceInstanceDto.setInstanceMap(instanceMap);
            serviceInstanceDto.setUnhealthyNum(serviceInstanceDto.getInstanceNum() - healthyNum);
            serviceInstanceDto.setEnabledNum(enabledNum);
            serviceInstanceDto.setEphemeralNum(ephemeralNum);
            serviceInstanceDto.setServiceInstanceDetails(serviceInstanceDetails);
        }
        return serviceInstanceDto;
    }


    @Override
    public List<ServiceInstanceDto> getAllServices(NacosGroupNameVo vo) throws RuntimeException {
        String groupName = vo.getGroupName();
        NacosGroupNameVo nameVo = new NacosGroupNameVo();
        nameVo.setGroupName(groupName);
        List<NacosServiceInfoDto> services = this.getServices(nameVo);
        List<ServiceInstanceDto> serviceInstanceDtos = Collections.emptyList();
        if (CollectionUtil.isNotEmpty(services)) {
            serviceInstanceDtos = new ArrayList<>(4);
            for (NacosServiceInfoDto serviceInfoDto : services) {
                NacosAllInstancesQueryVo queryVo = new NacosAllInstancesQueryVo();
                queryVo.setServiceName(serviceInfoDto.getName());
                queryVo.setGroupName(vo.getGroupName());
                serviceInstanceDtos.add(this.getAllInstances(queryVo));
            }
        }
        return serviceInstanceDtos;
    }


    @Override
    public List<NacosServiceInfoDto> getServices(NacosGroupNameVo vo) throws RuntimeException {
        return customNacosNamingService.getServicesOfServer(null, null, vo.getGroupName());
    }


    @Override
    public ServiceInstanceDto selectInstances(NacosSelectInstancesVo vo) throws RuntimeException {
        List<Instance> instances = customNacosNamingService.getAllInstances(vo.getServiceName(), vo.getGroupName(), null);
        return createServiceInstanceDto(instances);
    }


    @Override
    public void deleteService(NacosDeleteInstanceVo vo) throws RuntimeException {
        String groupName = vo.getGroupName();
        if (StringUtils.isBlank(groupName)) {
            groupName = properties.getGroup();
            if (StringUtils.isBlank(groupName)) {
                groupName = Constants.DEFAULT_GROUP;
            }
        }
        try {
            boolean b = namingMaintainService.deleteService(vo.getServiceName(), groupName);
            if (!b) {
                log.error("------------INacosServiceImpl------删除服务失败------>deleteService:vo-{}", vo);
                throw new ResponseException(Status.ERROR, "删除" + groupName + "组中" + vo.getServiceName() + "服务失败");
            }
        } catch (NacosException e) {
            e.printStackTrace();
            log.error("------------INacosServiceImpl------删除服务失败------>deleteService:vo-{},errMsg-{}", vo, e.getErrMsg());
            throw new ResponseException(Status.ERROR, "删除" + groupName + "组中" + vo.getServiceName() + "服务异常:" + e.getErrMsg());
        }
    }


    @Override
    public void updateInstance(NacosUpdateInstanceVo vo) throws RuntimeException {
        String groupName = vo.getGroupName();
        if (StringUtils.isBlank(groupName)) {
            groupName = properties.getGroup();
            if (StringUtils.isBlank(groupName)) {
                groupName = Constants.DEFAULT_GROUP;
            }
        }
        NacosAllInstancesQueryVo queryVo = new NacosAllInstancesQueryVo();
        queryVo.setGroupName(groupName);
        queryVo.setServiceName(vo.getServiceName());
        ServiceInstanceDto allInstances = this.getAllInstances(queryVo);
        Map<String, Instance> instanceMap = allInstances.getInstanceMap();
        List<ServiceInstanceDto.ServiceInstanceDetail> serviceInstanceDetails = allInstances.getServiceInstanceDetails();
        if (CollectionUtil.isEmpty(serviceInstanceDetails)) {
            throw new ResponseException(Status.ERROR, "查询" + groupName + "组中" + vo.getGroupName() + "服务不存在");
        }
        if (vo.getServiceName().equals(property.getServiceName()) && vo.getType() == 0 && serviceInstanceDetails.size() == 1) {
            throw new ResponseException(Status.ERROR, vo.getServiceName() + "服务剩余实例数量只有1个时不可操作下线,避免不能修改服务");
        }
        Instance instance = instanceMap.get(vo.getInstanceId());
        if (Objects.isNull(instance)) {
            throw new ResponseException(Status.ERROR, vo.getServiceName() + "服务实例不存在");
        }
        instance.setEnabled(vo.getType() == 1);
        try {
            namingMaintainService.updateInstance(instance.getServiceName(), groupName, instance);
        } catch (NacosException e) {
            e.printStackTrace();
            log.error("------------INacosServiceImpl------------>updateInstance:vo-{},errMsg-{}", vo, e.getErrMsg());
            throw new ResponseException(Status.ERROR, "更新" + groupName + "组中" + vo.getServiceName() + "服务" + vo.getInstanceId() + "实例状态异常:" + e.getErrMsg());
        }
    }


    @Override
    public void deregisterInstance(NacosDeregisterInstanceVo vo) throws RuntimeException {
        try {
            List<Instance> allInstances = customNacosNamingService.getAllInstances(vo.getServiceName(), vo.getGroupName(), null);
            if (CollectionUtil.isNotEmpty(allInstances)) {
                for (Instance instance : allInstances) {
                    if (vo.getInstanceId().equals(instance.getInstanceId())) {
                        namingService.deregisterInstance(vo.getServiceName(), instance);
                        return;
                    }
                }
            }
        } catch (NacosException e) {
            e.printStackTrace();
            log.error("------------INacosServiceImpl------------>getAllInstances:vo-{},errMsg-{}", vo, e.getErrMsg());
            throw new ResponseException(Status.ERROR, "注销" + vo.getServiceName() + "服务实例失败:" + e.getMessage());
        }
    }

}
