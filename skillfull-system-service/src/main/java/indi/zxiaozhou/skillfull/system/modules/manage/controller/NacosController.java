// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.manage.controller;

import indi.zxiaozhou.skillfull.corecommon.base.Result;
import indi.zxiaozhou.skillfull.corecommon.constant.UrlPrefix;
import indi.zxiaozhou.skillfull.coremvc.base.controller.BaseController;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.*;
import indi.zxiaozhou.skillfull.system.modules.manage.service.ICustomNacosNamingService;
import indi.zxiaozhou.skillfull.system.modules.manage.service.INacosService;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.NacosNamespacesDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.NacosServiceInfoDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ServiceInstanceDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Nacos操作
 *
 * @author zxiaozhou
 * @date 2020-10-11 15:47
 * @since JDK1.8
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "Nacos", description = "nacos操作")
@RequestMapping(value = UrlPrefix.SYSTEM + "/manage/nacos", produces = MediaType.APPLICATION_JSON_VALUE)
public class NacosController extends BaseController {
    private final INacosService service;
    private final ICustomNacosNamingService nacosNamingService;


    @ApiOperation(value = "订阅服务变化@(v1.0.0)", notes = "订阅服务变化")
    @PostMapping(value = "/service/subscribe")
    Result<String> subscribe(@RequestBody @Valid NacosSubscribeVo vo) {
        service.subscribe(vo);
        return ok("订阅服务变化监听成功");
    }


    @ApiOperation(value = "取消服务变化订阅@(v1.0.0)", notes = "取消服务变化订阅")
    @PostMapping(value = "/service/unsubscribe")
    Result<String> unsubscribe(@RequestBody @Valid NacosSubscribeVo vo) {
        service.unsubscribe(vo);
        return ok("取消服务监听成功");
    }


    @ApiOperation(value = "查询某个服务所有实例@(v1.0.0)", notes = "查询某个服务所有实例")
    @PostMapping(value = "/select/service-instance-all")
    Result<ServiceInstanceDto> getAllInstances(@RequestBody @Valid NacosAllInstancesQueryVo vo) {
        return ok(service.getAllInstances(vo));
    }


    @ApiOperation(value = "查询某个组的所有服务以及服务实例@(v1.0.0)", notes = "查询某个组的所有服务以及服务实例")
    @PostMapping(value = "/select/list/service")
    Result<List<ServiceInstanceDto>> getAllServices(@RequestBody @Valid NacosGroupNameVo vo) {
        return ok(service.getAllServices(vo));
    }


    @ApiOperation(value = "获取已经注册的服务@(v1.0.0)", notes = "获取已经注册的服务")
    @PostMapping(value = "/select/list/register-service")
    Result<List<NacosServiceInfoDto>> getServices(@RequestBody @Valid NacosGroupNameVo vo) {
        return ok(service.getServices(vo));
    }


    @ApiOperation(value = "通过条件查询某个服务实例@(v1.0.0)", notes = "通过条件查询某个服务实例")
    @PostMapping(value = "/select/one/service")
    Result<ServiceInstanceDto> selectInstances(@RequestBody @Valid NacosSelectInstancesVo vo) {
        return ok(service.selectInstances(vo));
    }


    @ApiOperation(value = "删除服务@(v1.0.0)", notes = "删除服务")
    @PostMapping(value = "/delete/service")
    Result<String> deleteService(@RequestBody @Valid NacosDeleteInstanceVo vo) {
        service.deleteService(vo);
        return ok("删除服务成功");
    }


    @ApiOperation(value = "服务实例上下线@(v1.0.0)", notes = "服务实例上下线")
    @PostMapping(value = "/update/service-instance/state")
    Result<String> updateInstance(@RequestBody @Valid NacosUpdateInstanceVo vo) {
        service.updateInstance(vo);
        return ok(vo.getType() == 0 ? "服务实例下线成功" : "服务实例上线成功");
    }


    @ApiOperation(value = "删除服务实例@(v1.0.0)", notes = "删除服务实例")
    @PostMapping(value = "/delete/service-instance")
    Result<String> deregisterInstance(@RequestBody @Valid NacosDeregisterInstanceVo vo) {
        service.deregisterInstance(vo);
        return ok("删除服务实例成功");
    }


    @ApiOperation(value = "获取命名空间信息@(v1.0.0)", notes = "获取命名空间信息")
    @GetMapping(value = "/get/namespaces")
    Result<List<NacosNamespacesDto>> getNamespaces() {
        return ok(nacosNamingService.getAllNamespaces());
    }
}
