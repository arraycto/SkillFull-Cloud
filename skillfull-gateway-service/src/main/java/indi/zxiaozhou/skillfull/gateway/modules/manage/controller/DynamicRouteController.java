// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.gateway.modules.manage.controller;

import indi.zxiaozhou.skillfull.corecommon.base.Result;
import indi.zxiaozhou.skillfull.corecommon.base.model.GatewayRouteInfoModel;
import indi.zxiaozhou.skillfull.corecommon.utils.ConvertUtil;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.PathNotBlank;
import indi.zxiaozhou.skillfull.corewebflux.base.controller.BaseController;
import indi.zxiaozhou.skillfull.corewebflux.utils.ReactiveRequestContextHolder;
import indi.zxiaozhou.skillfull.gateway.modules.manage.controller.vo.GatewayRouteVo;
import indi.zxiaozhou.skillfull.gateway.modules.manage.service.IDynamicRouteService;
import indi.zxiaozhou.skillfull.gateway.modules.manage.service.dto.GatewayRouteDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

/**
 * 动态路由controller
 *
 * @author zxiaozhou
 * @date 2020-09-10 21:14
 * @since JDK11
 */
@Api(tags = "DynamicRoute", description = "动态路由")
@RestController
@RequestMapping(value = "${server.context-path}/route", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class DynamicRouteController extends BaseController {
    private final IDynamicRouteService service;


    @ApiOperation(value = "添加路由@(v1.0.0)", notes = "添加路由")
    @PostMapping("/add")
    Mono<Result<String>> addRoute(@RequestBody @Valid GatewayRouteVo vo) {
        return ReactiveRequestContextHolder.getRequest().map(v -> {
            GatewayRouteInfoModel map = ConvertUtil.map(vo, GatewayRouteInfoModel.class);
            service.addRoute(map, true);
            return "添加成功";
        }).map(BaseController::ok);
    }


    @ApiOperation(value = "更新路由@(v1.0.0)", notes = "更新路由")
    @PutMapping("/update")
    Mono<Result<String>> updateRoute(@RequestBody @Valid GatewayRouteVo vo) {
        return ReactiveRequestContextHolder.getRequest().map(v -> {
            GatewayRouteInfoModel map = ConvertUtil.map(vo, GatewayRouteInfoModel.class);
            service.updateRoute(map, true);
            return "更新成功";
        }).map(BaseController::ok);
    }


    @ApiOperation(value = "删除路由@(v1.0.0)", notes = "删除路由")
    @ApiImplicitParam(paramType = "path", value = "路由编码", name = "routeCode", required = true)
    @DeleteMapping("/delete/{routeCode}")
    Mono<Result<String>> deleteRoute(@PathVariable @PathNotBlank(message = "路由编码不能为空") String routeCode) {
        return ReactiveRequestContextHolder.getRequest().map(v -> {
            service.deleteRoute(routeCode, true);
            return "删除成功";
        }).map(BaseController::ok);
    }


    @ApiOperation(value = "查询路由@(v1.0.0)", notes = "查询路由")
    @GetMapping("/select/list")
    Mono<Result<List<GatewayRouteDto>>> getRoutes() {

        return service.getRoutes().collectList().map(BaseController::ok);
    }
}
