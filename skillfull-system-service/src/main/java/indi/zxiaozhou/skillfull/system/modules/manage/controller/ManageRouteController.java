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
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.PathNotBlank;
import indi.zxiaozhou.skillfull.coremvc.base.controller.BaseController;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.system.feign.GatewayFeign;
import indi.zxiaozhou.skillfull.system.feign.dto.GatewayConstantDictDto;
import indi.zxiaozhou.skillfull.system.feign.dto.GatewayServiceRouteDto;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageRoutePageVo;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageRouteVo;
import indi.zxiaozhou.skillfull.system.modules.manage.service.IGatewayFeignService;
import indi.zxiaozhou.skillfull.system.modules.manage.service.IManageRouteService;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageRouteDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageRoutePageDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 路由(ManageRoute)控制层
 *
 * @author zxiaozhou
 * @date 2020-09-12 16:33:35
 * @since JDK1.8
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "ManageRoute", description = "服务路由管理")
@RequestMapping(value = UrlPrefix.SYSTEM + "/manage/route", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManageRouteController extends BaseController {
    private final IManageRouteService service;
    private final IGatewayFeignService gatewayFeignService;
    private final GatewayFeign gatewayFeign;


    @ApiOperation(value = "路由添加@(v1.0.0)", notes = "添加路由")
    @PostMapping(value = "/insert")
    public Result<String> insert(@RequestBody @Valid ManageRouteVo vo) {
        service.save(vo);
        return ok("添加成功");
    }


    @ApiOperation(value = "通过路由id修改@(v1.0.0)", notes = "修改路由")
    @ApiImplicitParam(paramType = "path", value = "路由id", name = "routeId", required = true)
    @PutMapping(value = "/update/{routeId}")
    public Result<String> update(@PathVariable(required = false) @PathNotBlank(message = "路由id不能为空") String routeId, @RequestBody @Valid ManageRouteVo vo) {
        service.updateById(routeId, vo);
        return ok("修改成功");
    }


    @ApiOperation(value = "路由逻辑删除@(v1.0.0)", notes = "删除路由")
    @ApiImplicitParam(paramType = "path", value = "路由id", name = "routeId", required = true)
    @DeleteMapping(value = "/delete/{routeId}")
    public Result<String> delete(@PathVariable(required = false) @PathNotBlank(message = "路由id不能为空") String routeId) {
        service.deleteById(routeId);
        return ok("删除成功");
    }


    @ApiOperation(value = "通过路由id查询详情@(v1.0.0)", notes = "查询路由详情")
    @ApiImplicitParam(paramType = "path", value = "路由id", name = "routeId", required = true)
    @GetMapping(value = "/select/one/{routeId}")
    public Result<ManageRouteDto> getById(@PathVariable(required = false) @PathNotBlank(message = "路由id不能为空") String routeId) {
        return ok(service.getById(routeId));
    }


    @ApiOperation(value = "路由分页查询@(v1.0.0)", notes = "分页查询路由")
    @PostMapping(value = "/select/page")
    public Result<PageDto<ManageRoutePageDto>> selectPage(@RequestBody ManageRoutePageVo vo) {
        return ok(service.pageByModel(vo));
    }


    @ApiOperation(value = "更新路由状态@(v1.0.0)", notes = "更新路由状态")
    @GetMapping(value = "/update/route/state")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", value = "路由id", name = "routeId", required = true),
            @ApiImplicitParam(paramType = "query", value = "操作状态:0-禁用,1-启用", name = "state", required = true)
    })
    public Result<String> updateRouteState(@RequestParam(required = false) @NotBlank(message = "路由id不能为空") String routeId,
                                           @RequestParam(required = false)
                                           @NotNull(message = "状态不能为空")
                                           @Min(value = 0, message = "状态只能为0或1")
                                           @Max(value = 1, message = "状态只能为0或1")
                                                   Integer state) {
        service.updateRouteState(routeId, state);
        return ok("更新路由状态成功");
    }


    @ApiOperation(value = "获取网关常量字典@(v1.0.0)", notes = "获取网关常量字典")
    @GetMapping("/select/constant/dict/{type}")
    @ApiImplicitParam(paramType = "path", value = "字典类型: PredicateType--断言,FilterType--过滤器", name = "type", required = true)
    Result<List<GatewayConstantDictDto>> getConstantDict(@PathVariable @PathNotBlank(message = "常量字典类型不能为空") String type) {
        return ok(gatewayFeignService.getConstantDict(type));
    }


    @ApiOperation(value = "刷新路由@(v1.0.0)", notes = "刷新路由")
    @GetMapping("/refresh/route")
    Result<String> refreshRoute() {
        service.refreshRoute();
        return ok("刷新路由成功");
    }


    @ApiOperation(value = "获取网关路由列表@(v1.0.0)", notes = "获取网关路由列表")
    @GetMapping("/route/select/list")
    Result<List<GatewayServiceRouteDto>> getRoutes() {
        return gatewayFeign.getRoutes();
    }
}