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
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.zxiaozhou.skillfull.corecommon.base.model.GatewayRouteInfoModel;
import indi.zxiaozhou.skillfull.corecommon.constant.CacheConstant;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import indi.zxiaozhou.skillfull.corecommon.utils.ConvertUtil;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.system.core.constant.SystemCommon;
import indi.zxiaozhou.skillfull.system.core.message.RouterUpdateProcessor;
import indi.zxiaozhou.skillfull.system.feign.dto.GatewayConstantDictDto;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageRoutePageVo;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageRouteVo;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageRouteEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageRouteFilterEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageRoutePredicateEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageServiceEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.mapper.ManageRouteMapper;
import indi.zxiaozhou.skillfull.system.modules.manage.mapper.ManageServiceMapper;
import indi.zxiaozhou.skillfull.system.modules.manage.service.IGatewayFeignService;
import indi.zxiaozhou.skillfull.system.modules.manage.service.IManageRouteFilterService;
import indi.zxiaozhou.skillfull.system.modules.manage.service.IManageRoutePredicateService;
import indi.zxiaozhou.skillfull.system.modules.manage.service.IManageRouteService;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageRouteDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageRoutePageDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.mapstruct.dto.ManageRouteDtoMap;
import indi.zxiaozhou.skillfull.system.modules.manage.service.mapstruct.dto.ManageRoutePageDtoMap;
import indi.zxiaozhou.skillfull.system.modules.manage.service.mapstruct.vo.ManageRoutePageVoMap;
import indi.zxiaozhou.skillfull.system.modules.manage.service.mapstruct.vo.ManageRouteQueryVoMap;
import indi.zxiaozhou.skillfull.system.modules.manage.service.mapstruct.vo.ManageRouteVoMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 路由(ManageRoute)业务层实现
 *
 * @author zxiaozhou
 * @date 2020-09-12 16:33:36
 * @since JDK1.8
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ManageRouteServiceImpl extends ServiceImpl<ManageRouteMapper, ManageRouteEntity> implements IManageRouteService {
    private final ManageRouteDtoMap dtoMap;
    private final ManageRoutePageDtoMap pageDtoMap;
    private final ManageRouteVoMap voMap;
    private final ManageRouteQueryVoMap queryVoMap;
    private final ManageRoutePageVoMap pageVoMap;
    private final ManageRouteMapper mapper;
    private final ManageServiceMapper serviceMapper;
    private final IManageRoutePredicateService predicateService;
    private final IManageRouteFilterService filterService;
    private final IGatewayFeignService gatewayFeignService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final RouterUpdateProcessor customProcessor;


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void save(ManageRouteVo vo) throws RuntimeException {
        // 保存主表信息
        ManageRouteEntity entity = voMap.toEntity(vo);
        entity.setMetadataJson(JSONObject.toJSONString(vo.getMetadata(), SerializerFeature.WriteMapNullValue));
        boolean result = super.save(entity);
        if (!result) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "保存路由信息失败");
        }
        String routeId = entity.getRouteId();
        // 保存其他信息
        saveOtherInfo(routeId, vo);
        // 通知网关更新路由
        if (vo.getRouteState() == 1) {
            refreshRoute();
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void updateById(String routeId, ManageRouteVo vo) throws RuntimeException {
        // 查询数据是否存在
        this.getById(routeId);
        // 更新主表数据
        ManageRouteEntity entity = voMap.toEntity(vo);
        entity.setMetadataJson(JSONObject.toJSONString(vo.getMetadata(), SerializerFeature.WriteMapNullValue));
        entity.setRouteId(routeId);
        boolean result = super.updateById(entity);
        if (!result) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "更新路由信息失败");
        }
        // 删除历史数据
        deleteOtherInfo(routeId);
        // 保存其他信息
        saveOtherInfo(routeId, vo);
        // 通知网关更新路由
        if (vo.getRouteState() == 1) {
            refreshRoute();
        }
    }


    /**
     * 保存其他信息
     *
     * @param routeId ${@link String} 路由id
     * @param vo      ${@link ManageRouteVo} 待入库数据
     * @author zxiaozhou
     * @date 2020-09-12 17:44
     */
    @Transactional(rollbackFor = {Exception.class, Error.class})
    void saveOtherInfo(String routeId, ManageRouteVo vo) {
        String serviceId = vo.getServiceId();
        // 保存断言信息
        List<ManageRouteVo.ManageRoutePredicate> routePredicates = vo.getRoutePredicates();
        List<ManageRoutePredicateEntity> predicateEntities = ConvertUtil.listAToListB(routePredicates, ManageRoutePredicateEntity.class);
        predicateEntities.forEach(v -> {
            v.setServiceId(serviceId);
            // 验证网关断言类型是否存在
            checkRouteType(SystemCommon.PREDICATE_TYPE, v.getPredicateType());
            v.setRouteId(routeId);
        });
        boolean b = predicateService.saveBatch(predicateEntities);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "保存断言信息失败");
        }
        // 保存过滤器信息
        List<ManageRouteVo.ManageRouteFilter> routeFilters = vo.getRouteFilters();
        if (!CollectionUtils.isEmpty(routeFilters)) {
            List<ManageRouteFilterEntity> filterEntities = ConvertUtil.listAToListB(routeFilters, ManageRouteFilterEntity.class);
            filterEntities.forEach(v -> {
                v.setServiceId(serviceId);
                // 验证网关过滤器类型是否存在
                checkRouteType(SystemCommon.FILTER_TYPE, v.getFilterType());
                v.setRouteId(routeId);
            });
            b = filterService.saveBatch(filterEntities);
            if (!b) {
                throw new ResponseException(Status.DATABASE_BASE_ERROR, "保存断言信息失败");
            }
        }
    }


    /**
     * 删除其他信息
     *
     * @param routeId ${@link String} 路由id
     * @author zxiaozhou
     * @date 2020-09-12 17:47
     */
    @Transactional(rollbackFor = {Exception.class, Error.class})
    void deleteOtherInfo(String routeId) {
        LambdaQueryWrapper<ManageRoutePredicateEntity> predicateLambdaQueryWrapper = new LambdaQueryWrapper<>();
        predicateLambdaQueryWrapper.eq(ManageRoutePredicateEntity::getRouteId, routeId);
        List<ManageRoutePredicateEntity> predicateEntities = predicateService.list(predicateLambdaQueryWrapper);
        if (!CollectionUtils.isEmpty(predicateEntities)) {
            boolean remove = predicateService.remove(predicateLambdaQueryWrapper);
            if (!remove) {
                throw new ResponseException(Status.DATABASE_BASE_ERROR, "清空历史断言信息失败");
            }
        }
        LambdaQueryWrapper<ManageRouteFilterEntity> filterLambdaQueryWrapper = new LambdaQueryWrapper<>();
        filterLambdaQueryWrapper.eq(ManageRouteFilterEntity::getRouteId, routeId);
        List<ManageRouteFilterEntity> filterEntities = filterService.list(filterLambdaQueryWrapper);
        if (!CollectionUtils.isEmpty(filterEntities)) {
            boolean remove = filterService.remove(filterLambdaQueryWrapper);
            if (!remove) {
                throw new ResponseException(Status.DATABASE_BASE_ERROR, "清空历史过滤器信息失败");
            }
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public PageDto<ManageRoutePageDto> pageByModel(ManageRoutePageVo vo) throws RuntimeException {
        return new PageDto<>(mapper.pageByModel(vo.getPage(), vo));
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public ManageRouteDto getById(String routeId) throws RuntimeException {
        // 获取主表数据
        ManageRouteEntity byId = super.getById(routeId);
        if (Objects.isNull(byId)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR);
        }
        // 组装其余数据
        List<ManageRouteEntity> routeEntityList = new ArrayList<>();
        routeEntityList.add(byId);
        List<ManageRouteDto> routeAllInfo = getRouteAllInfo(routeEntityList, true);
        ManageRouteDto manageRouteDto = routeAllInfo.get(0);
        manageRouteDto.setMetadata(JSONObject.parseObject(byId.getMetadataJson()));
        return manageRouteDto;
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void updateRouteState(String routeId, Integer state) throws RuntimeException {
        this.getById(routeId);
        ManageRouteEntity entity = new ManageRouteEntity();
        entity.setRouteState(state);
        entity.setRouteId(routeId);
        boolean b = super.updateById(entity);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "更新路由失败");
        }
        // 通知网关更新路由
        refreshRoute();
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void deleteById(String routeId) throws RuntimeException {
        // 查询数据是否存在
        ManageRouteDto byId = this.getById(routeId);
        // 删除主表数据
        boolean b = this.removeById(routeId);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR);
        }
        // 删除其他信息
        deleteOtherInfo(routeId);
        // 通知网关更新路由
        if (byId.getRouteState() == 1) {
            refreshRoute();
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public List<ManageRouteDto> getRouteAllInfoByServiceName(String serviceName) throws RuntimeException {
        // 获取所有路由信息
        LambdaQueryWrapper<ManageRouteEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ManageRouteEntity::getServiceName, serviceName);
        List<ManageRouteEntity> list = this.list(lambdaQueryWrapper);
        return getRouteAllInfo(list, true);
    }


    /**
     * 检测网关过滤器或断言是否存在
     *
     * @param type  ${@link String} 检验类型: PredicateType--断言,SecurityType--过滤器
     * @param value ${@link String} 待检验数据
     * @author zxiaozhou
     * @date 2020-09-12 21:14
     */
    private void checkRouteType(String type, String value) {
        List<GatewayConstantDictDto> constantDict = gatewayFeignService.getConstantDict(type);
        if (CollectionUtils.isEmpty(constantDict)) {
            throw new ResponseException(Status.API_ERROR, "获取网关常量字典失败,请检查网关服务");
        }
        boolean checkResult = false;
        StringBuilder sb = new StringBuilder();
        for (GatewayConstantDictDto dto : constantDict) {
            sb.append("、").append(dto.getType());
            if (dto.getType().equals(value)) {
                checkResult = true;
            }
        }
        String strType = SystemCommon.FILTER_TYPE.equals(type) ? "过滤器类型" + type + "不存在,只能为:" : "断言类型" + type + "不存在,只能为:" + sb.toString().replaceFirst("、", "");
        if (!checkResult) {
            throw new ResponseException(Status.API_ERROR, strType);
        }
    }


    /**
     * 构建路由详细信息(结构与网管结构一样)
     *
     * @param routeEntities ${@link List<ManageRouteEntity>}
     * @param getHealthy    ${@link Boolean}  是否获取健康信息
     * @return ManageRouteDto> ${@link ManageRouteDto>}
     * @author zxiaozhou
     * @date 2020-09-13 00:11
     */
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    List<ManageRouteDto> getRouteAllInfo(List<ManageRouteEntity> routeEntities, boolean getHealthy) {
        if (CollectionUtils.isEmpty(routeEntities)) {
            return Collections.emptyList();
        }
        // 获取服务路由id以及服务名称
        Set<String> routeIds = new HashSet<>();
        routeEntities.forEach(v -> {
            routeIds.add(v.getRouteId());
        });
        // 获取断言数据
        LambdaQueryWrapper<ManageRoutePredicateEntity> predicateLambdaQueryWrapper = new LambdaQueryWrapper<>();
        predicateLambdaQueryWrapper.in(ManageRoutePredicateEntity::getRouteId, routeIds);
        List<ManageRoutePredicateEntity> predicateEntities = predicateService.list(predicateLambdaQueryWrapper);
        // 获取过滤器数据
        LambdaQueryWrapper<ManageRouteFilterEntity> filterLambdaQueryWrapper = new LambdaQueryWrapper<>();
        filterLambdaQueryWrapper.in(ManageRouteFilterEntity::getRouteId, routeIds);
        List<ManageRouteFilterEntity> filterEntities = filterService.list(filterLambdaQueryWrapper);
        // 响应数据组装(如果断言数据为空则跳过,因为路由必须要有断言信息: 如果全部为空则直接返回，如果部分为空则组装有效的)
        if (CollectionUtils.isEmpty(predicateEntities)) {
            return Collections.emptyList();
        }
        List<ManageRouteDto> manageRouteDtoList = new ArrayList<>();
        for (ManageRouteEntity routeEntity : routeEntities) {
            ManageRouteDto manageRouteDto = dtoMap.toDto(routeEntity);
            manageRouteDto.setMetadata(JSONObject.parseObject(routeEntity.getMetadataJson()));
            // 断言信息
            if (!CollectionUtils.isEmpty(predicateEntities)) {
                List<ManageRouteDto.ManageRoutePredicate> routePredicates = new ArrayList<>();
                predicateEntities.forEach(sv -> {
                    if (sv.getRouteId().equals(routeEntity.getRouteId())) {
                        // 创建断言数据
                        ManageRouteDto.ManageRoutePredicate predicate = new ManageRouteDto.ManageRoutePredicate();
                        predicate.setPredicateType(sv.getPredicateType());
                        predicate.setRemark(sv.getRemark());
                        List<ManageRouteDto.Rule> rules = JSONObject.parseArray(sv.getRules(), ManageRouteDto.Rule.class);
                        predicate.setRules(new HashSet<>(rules));
                        routePredicates.add(predicate);
                    }
                });
                if (CollectionUtils.isEmpty(routePredicates)) {
                    continue;
                }
                manageRouteDto.setRoutePredicates(routePredicates);
            }
            // 过滤器信息
            if (!CollectionUtils.isEmpty(filterEntities)) {
                List<ManageRouteDto.ManageRouteFilter> routeFilters = new ArrayList<>();
                filterEntities.forEach(sv -> {
                    if (routeEntity.getRouteId().equals(sv.getRouteId())) {
                        // 创建过滤器数据
                        ManageRouteDto.ManageRouteFilter filter = new ManageRouteDto.ManageRouteFilter();
                        filter.setFilterType(sv.getFilterType());
                        filter.setRemark(sv.getRemark());
                        if (StringUtils.isNotBlank(sv.getRules())) {
                            List<ManageRouteDto.Rule> rules = JSONObject.parseArray(sv.getRules(), ManageRouteDto.Rule.class);
                            filter.setRules(new HashSet<>(rules));
                        }
                        routeFilters.add(filter);
                    }
                });
                if (!CollectionUtils.isEmpty(routeFilters)) {
                    manageRouteDto.setRouteFilters(routeFilters);
                }
            }
            manageRouteDtoList.add(manageRouteDto);
        }
        return manageRouteDtoList;
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public void refreshRoute() throws RuntimeException {
        // 获取所有启用的服务
        LambdaQueryWrapper<ManageServiceEntity> serviceLambdaQueryWrapper = new LambdaQueryWrapper<ManageServiceEntity>();
        serviceLambdaQueryWrapper.eq(ManageServiceEntity::getServiceState, 1);
        List<ManageServiceEntity> manageServiceEntities = serviceMapper.selectList(serviceLambdaQueryWrapper);
        if (CollectionUtil.isNotEmpty(manageServiceEntities)) {
            Set<String> serviceIds = new HashSet<>();
            manageServiceEntities.forEach(v -> serviceIds.add(v.getServiceId()));
            // 查询所有有效的路由
            LambdaQueryWrapper<ManageRouteEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(ManageRouteEntity::getRouteState, 1)
                    .in(ManageRouteEntity::getServiceId, serviceIds);
            List<ManageRouteEntity> list = this.list(lambdaQueryWrapper);
            if (CollectionUtil.isEmpty(list)) {
                // 清空所有
                redisTemplate.delete(CacheConstant.ROUTE_INFO_CACHE);
            } else {
                // 数据写入redis
                List<ManageRouteDto> routeAllInfo = getRouteAllInfo(list, false);
                List<GatewayRouteInfoModel> routeInfoModels = ConvertUtil.listAToListB(routeAllInfo, GatewayRouteInfoModel.class);
                redisTemplate.opsForValue().set(CacheConstant.ROUTE_INFO_CACHE, routeInfoModels);
            }
            // 广播网关刷新数据
            Message<String> stringMessage = MessageBuilder.withPayload("需要更新路由").build();
            customProcessor.updateRouteMessage().send(stringMessage);
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void deleteByServiceId(String serviceId) throws RuntimeException {
        // 删除断言
        LambdaQueryWrapper<ManageRoutePredicateEntity> predicateEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        predicateEntityLambdaQueryWrapper.eq(ManageRoutePredicateEntity::getServiceId, serviceId);
        List<ManageRoutePredicateEntity> list = predicateService.list(predicateEntityLambdaQueryWrapper);
        if (CollectionUtil.isNotEmpty(list)) {
            boolean remove = predicateService.remove(predicateEntityLambdaQueryWrapper);
            if (!remove) {
                throw new ResponseException(Status.DATABASE_BASE_ERROR, "删除路由断言失败");
            }
        }
        // 删除过滤器
        LambdaQueryWrapper<ManageRouteFilterEntity> filterEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        filterEntityLambdaQueryWrapper.eq(ManageRouteFilterEntity::getServiceId, serviceId);
        List<ManageRouteFilterEntity> filterEntities = filterService.list(filterEntityLambdaQueryWrapper);
        if (CollectionUtil.isNotEmpty(filterEntities)) {
            boolean remove = filterService.remove(filterEntityLambdaQueryWrapper);
            if (!remove) {
                throw new ResponseException(Status.DATABASE_BASE_ERROR, "删除路由过滤器失败");
            }
        }
        // 删除主表信息
        LambdaQueryWrapper<ManageRouteEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ManageRouteEntity::getServiceId, serviceId);
        List<ManageRouteEntity> routeEntities = this.list(lambdaQueryWrapper);
        if (CollectionUtil.isNotEmpty(routeEntities)) {
            boolean remove = this.remove(lambdaQueryWrapper);
            if (!remove) {
                throw new ResponseException(Status.DATABASE_BASE_ERROR, "删除路由失败");
            }
        }
        // 刷新网关路由信息
        this.refreshRoute();
    }
}