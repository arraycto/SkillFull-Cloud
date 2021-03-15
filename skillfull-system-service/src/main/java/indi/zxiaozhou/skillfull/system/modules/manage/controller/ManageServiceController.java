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
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageServicePageVo;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageServiceVo;
import indi.zxiaozhou.skillfull.system.modules.manage.service.IManageServiceService;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageServiceDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageServicePageDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ServiceStatDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.SwaggerInfoDto;
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
 * 服务管理(ManageService)控制层
 *
 * @author zxiaozhou
 * @date 2020-09-13 17:50:23
 * @since JDK1.8
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "ManageService", description = "服务管理")
@RequestMapping(value = UrlPrefix.SYSTEM + "/manage/service", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManageServiceController extends BaseController {
    private final IManageServiceService service;


    @ApiOperation(value = "服务管理添加@(v1.0.0)", notes = "添加服务管理")
    @PostMapping(value = "/insert")
    public Result<String> insert(@RequestBody @Valid ManageServiceVo vo) {
        service.save(vo);
        return ok("添加成功");
    }


    @ApiOperation(value = "通过服务id修改@(v1.0.0)", notes = "修改服务管理")
    @ApiImplicitParam(paramType = "path", value = "服务id", name = "serviceId", required = true)
    @PutMapping(value = "/update/{serviceId}")
    public Result<String> update(@PathVariable(required = false) @PathNotBlank(message = "服务id不能为空") String serviceId, @RequestBody @Valid ManageServiceVo vo) {
        service.updateById(serviceId, vo);
        return ok("修改成功");
    }


    @ApiOperation(value = "服务管理逻辑删除@(v1.0.0)", notes = "删除服务管理")
    @ApiImplicitParam(paramType = "path", value = "服务id", name = "serviceId", required = true)
    @DeleteMapping(value = "/delete/{serviceId}")
    public Result<String> delete(@PathVariable(required = false) @PathNotBlank(message = "服务id不能为空") String serviceId) {
        service.deleteById(serviceId);
        return ok("删除成功");
    }


    @ApiOperation(value = "通过服务id查询详情@(v1.0.0)", notes = "查询服务管理详情")
    @ApiImplicitParam(paramType = "path", value = "服务id", name = "serviceId", required = true)
    @GetMapping(value = "/select/one/{serviceId}")
    public Result<ManageServiceDto> getById(@PathVariable(required = false) @PathNotBlank(message = "服务id不能为空") String serviceId) {
        return ok(service.getById(serviceId));
    }

    @ApiOperation(value = "更新服务状态@(v1.0.0)", notes = "更新服务状态")
    @GetMapping(value = "/update/service/state")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", value = "服务id", name = "serviceId", required = true),
            @ApiImplicitParam(paramType = "query", value = "操作状态:0-禁用,1-启用", name = "state", required = true)
    })
    public Result<String> updateServiceState(@RequestParam(required = false) @NotBlank(message = "服务id不能为空") String serviceId,
                                             @RequestParam(required = false)
                                             @NotNull(message = "状态不能为空")
                                             @Min(value = 0, message = "状态只能为0或1")
                                             @Max(value = 1, message = "状态只能为0或1")
                                                     Integer state) {
        service.updateServiceState(serviceId, state);
        return ok(state == 0 ? "禁用服务成功" : "启用服务成功");
    }


    @ApiOperation(value = "服务管理分页查询@(v1.0.0)", notes = "分页查询服务管理")
    @PostMapping(value = "/select/page")
    public Result<PageDto<ManageServicePageDto>> selectPage(@RequestBody ManageServicePageVo vo) {
        return ok(service.pageByModel(vo));
    }


    @ApiOperation(value = "获取swagger配置@(v1.0.0)", notes = "获取swagger配置")
    @GetMapping(value = "/select/swagger-info")
    public Result<List<SwaggerInfoDto>> selectSwaggerInfo() {
        return ok(service.selectSwaggerInfo());
    }

    @ApiOperation(value = "获取服务统计@(v1.0.0)", notes = "获取服务统计")
    @GetMapping(value = "/stat")
    public Result<ServiceStatDto> serviceStat() {
        return ok(service.serviceStat());
    }
}