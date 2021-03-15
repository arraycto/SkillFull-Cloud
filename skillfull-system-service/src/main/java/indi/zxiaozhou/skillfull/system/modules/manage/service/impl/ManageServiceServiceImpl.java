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
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import indi.zxiaozhou.skillfull.corecommon.utils.ConvertUtil;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageServicePageVo;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageServiceVo;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.NacosAllInstancesQueryVo;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageServiceEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.mapper.ManageServiceMapper;
import indi.zxiaozhou.skillfull.system.modules.manage.service.ICustomNacosNamingService;
import indi.zxiaozhou.skillfull.system.modules.manage.service.IManageRouteService;
import indi.zxiaozhou.skillfull.system.modules.manage.service.IManageServiceService;
import indi.zxiaozhou.skillfull.system.modules.manage.service.INacosService;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.*;
import indi.zxiaozhou.skillfull.system.modules.manage.service.mapstruct.dto.ManageServiceDtoMap;
import indi.zxiaozhou.skillfull.system.modules.manage.service.mapstruct.vo.ManageServiceVoMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 服务管理(ManageService)业务层实现
 *
 * @author zxiaozhou
 * @date 2020-09-13 17:50:24
 * @since JDK1.8
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ManageServiceServiceImpl extends ServiceImpl<ManageServiceMapper, ManageServiceEntity> implements IManageServiceService {
    private final ManageServiceDtoMap dtoMap;
    private final ManageServiceVoMap voMap;
    private final ManageServiceMapper mapper;
    private final IManageRouteService routeService;
    private final ICustomNacosNamingService nacosNamingService;
    private final INacosService nacosService;


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void save(ManageServiceVo vo) throws RuntimeException {
        ManageServiceEntity entity = voMap.toEntity(vo);
        boolean result = super.save(entity);
        if (!result) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR);
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void updateById(String serviceId, ManageServiceVo vo) throws RuntimeException {
        // 查询数据是否存在
        this.getById(serviceId);
        // 更新数据
        ManageServiceEntity entity = voMap.toEntity(vo);
        entity.setServiceId(serviceId);
        boolean result = super.updateById(entity);
        if (!result) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR);
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public PageDto<ManageServicePageDto> pageByModel(ManageServicePageVo vo) throws RuntimeException {
        IPage<ManageServicePageDto> resultPage = mapper.pageByModel(vo.getPage(), vo);
        List<ManageServicePageDto> records = resultPage.getRecords();
        if (!CollectionUtils.isEmpty(records)) {
            // 获取所有服务并补充返回参数中实例健康情况
            List<NacosServiceInfoDto> servicesOfServer = nacosNamingService.getServicesOfServer(null, null, null);
            records.forEach(v -> {
                if (CollectionUtil.isNotEmpty(servicesOfServer)) {
                    String serviceName = v.getServiceName();
                    servicesOfServer.forEach(sv -> {
                        if (serviceName.equals(sv.getName())) {
                            v.setInstanceNum(sv.getIpCount());
                            v.setHealthyNum(sv.getHealthyInstanceCount());
                            v.setUnhealthyNum(sv.getClusterCount() - sv.getHealthyInstanceCount());
                        }
                    });
                }
            });
        }
        return new PageDto<>(resultPage, records);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public ManageServiceDto getById(String serviceId) throws RuntimeException {
        ManageServiceEntity byId = super.getById(serviceId);
        if (Objects.isNull(byId)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR);
        }
        ManageServiceDto manageServiceDto = dtoMap.toDto(byId);
        // 获取所有服务
        if (manageServiceDto.getIsLoadBalancer() == 1) {
            NacosAllInstancesQueryVo queryVo = new NacosAllInstancesQueryVo();
            queryVo.setServiceName(manageServiceDto.getServiceName());
            ServiceInstanceDto allInstances = nacosService.getAllInstances(queryVo);
            manageServiceDto.setServiceInstanceDto(allInstances);
        }
        List<ManageRouteDto> routeAllInfoByServiceName = routeService.getRouteAllInfoByServiceName(byId.getServiceName());
        manageServiceDto.setRouteList(routeAllInfoByServiceName);
        return manageServiceDto;
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void deleteById(String serviceId) throws RuntimeException {
        // 查询数据是否存在
        this.getById(serviceId);
        boolean b = this.removeById(serviceId);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR);
        }
        // 删除服务下面的路由同时刷新网关路由列表
        routeService.deleteByServiceId(serviceId);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public List<SwaggerInfoDto> selectSwaggerInfo() throws RuntimeException {
        LambdaQueryWrapper<ManageServiceEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.isNotNull(ManageServiceEntity::getSwaggerDocUri);
        return ConvertUtil.listAToListB(this.list(lambdaQueryWrapper), SwaggerInfoDto.class);
    }


    @Override
    public ServiceStatDto serviceStat() throws RuntimeException {
        ServiceStatDto dto = new ServiceStatDto();
        List<ManageServiceEntity> list = this.list();
        dto.setTotalService(list.size());
        AtomicReference<Integer> healthyInstanceCountAtom = new AtomicReference<>(0);
        AtomicReference<Integer> instanceCountAtom = new AtomicReference<>(0);
        if (CollectionUtil.isNotEmpty(list)) {
            list.forEach(v -> {
                // 获取所有服务
                List<NacosServiceInfoDto> servicesOfServer = nacosNamingService.getServicesOfServer(null, null, null);
                if (CollectionUtil.isNotEmpty(servicesOfServer)) {
                    String serviceName = v.getServiceName();
                    servicesOfServer.forEach(sv -> {
                        if (serviceName.equals(sv.getName())) {
                            healthyInstanceCountAtom.updateAndGet(v1 -> v1 + sv.getHealthyInstanceCount());
                            instanceCountAtom.updateAndGet(v1 -> v1 + sv.getIpCount());
                        }
                    });
                }
            });
            int healthyInstanceCount = Objects.nonNull(healthyInstanceCountAtom.get()) ? healthyInstanceCountAtom.get() : 0;
            int instanceCount = Objects.nonNull(instanceCountAtom.get()) ? instanceCountAtom.get() : 0;
            dto.setHealthyInstanceCount(healthyInstanceCount);
            dto.setNoHealthyInstanceCount(instanceCount - healthyInstanceCount);
        }
        return dto;
    }


    @Override
    public void updateServiceState(String serviceId, Integer state) throws RuntimeException {
        this.getById(serviceId);
        // 更新状态
        ManageServiceEntity entity = new ManageServiceEntity();
        entity.setServiceId(serviceId);
        entity.setServiceState(state);
        boolean b = super.updateById(entity);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, state == 0 ? "禁用服务失败" : "启用服务失败");
        }
        // 刷新路由
        routeService.refreshRoute();
    }
}