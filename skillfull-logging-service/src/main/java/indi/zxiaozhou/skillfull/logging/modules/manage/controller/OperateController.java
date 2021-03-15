// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.logging.modules.manage.controller;

import indi.zxiaozhou.skillfull.corecommon.base.Result;
import indi.zxiaozhou.skillfull.corecommon.constant.UrlPrefix;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotNullSize;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.PathNotBlank;
import indi.zxiaozhou.skillfull.coremvc.base.controller.BaseController;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.logging.modules.manage.controller.vo.OperatePageVo;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.IOperateService;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.dto.OperateDto;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.dto.OperatePageDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志(Operate)控制层
 *
 * @author zxiaozhou
 * @date 2021-01-12 14:40:15
 * @since JDK11
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "Operate", description = "操作日志")
@RequestMapping(value = UrlPrefix.LOGGING + "/operate", produces = MediaType.APPLICATION_JSON_VALUE)
public class OperateController extends BaseController {
    private final IOperateService service;


    @ApiOperation(value = "操作日志逻辑删除@(v1.0.0)", notes = "删除操作日志")
    @ApiImplicitParam(paramType = "path", value = "系统日志id", name = "sysLogId", required = true)
    @DeleteMapping(value = "/delete-one/{sysLogId}")
    public Result<String> delete(@PathVariable(required = false) @PathNotBlank(message = "系统日志id不能为空") String sysLogId) {
        service.deleteById(sysLogId);
        return ok("删除成功");
    }


    @ApiOperation(value = "操作日志逻辑批量删除@(v1.0.0)", notes = "批量删除操作日志")
    @PostMapping(value = "/delete-batch")
    public Result<String> deleteBatch(@RequestBody @NotNullSize(message = "待删除系统日志id不能为空") List<String> sysLogIds) {
        service.deleteBatch(sysLogIds);
        return ok("批量删除成功");
    }


    @ApiOperation(value = "通过系统日志id查询详情@(v1.0.0)", notes = "查询操作日志详情")
    @ApiImplicitParam(paramType = "path", value = "系统日志id", name = "sysLogId", required = true)
    @GetMapping(value = "/select/one/{sysLogId}")
    public Result<OperateDto> getById(@PathVariable(required = false) @PathNotBlank(message = "系统日志id不能为空") String sysLogId) {
        return ok(service.getById(sysLogId));
    }


    @ApiOperation(value = "操作日志分页查询@(v1.0.0)", notes = "分页查询操作日志")
    @PostMapping(value = "/select/page")
    public Result<PageDto<OperatePageDto>> selectPage(@RequestBody OperatePageVo vo) {
        return ok(service.pageByModel(vo));
    }
}