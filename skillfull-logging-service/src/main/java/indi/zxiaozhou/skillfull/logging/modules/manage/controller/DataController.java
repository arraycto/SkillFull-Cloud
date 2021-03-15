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
import indi.zxiaozhou.skillfull.logging.modules.manage.controller.vo.DataPageVo;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.IDataService;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.dto.DataDto;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.dto.DataPageDto;
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
 * 数据日志(Data)控制层
 *
 * @author zxiaozhou
 * @date 2021-01-12 14:38:25
 * @since JDK11
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "Data", description = "数据日志")
@RequestMapping(value = UrlPrefix.LOGGING + "/data", produces = MediaType.APPLICATION_JSON_VALUE)
public class DataController extends BaseController {
    private final IDataService service;


    @ApiOperation(value = "数据日志逻辑删除@(v1.0.0)", notes = "删除数据日志")
    @ApiImplicitParam(paramType = "path", value = "数据日志id", name = "dataLogId", required = true)
    @DeleteMapping(value = "/delete-one/{dataLogId}")
    public Result<String> delete(@PathVariable(required = false) @PathNotBlank(message = "数据日志id不能为空") String dataLogId) {
        service.deleteById(dataLogId);
        return ok("删除成功");
    }


    @ApiOperation(value = "数据日志逻辑批量删除@(v1.0.0)", notes = "批量删除数据日志")
    @PostMapping(value = "/delete-batch")
    public Result<String> deleteBatch(@RequestBody @NotNullSize(message = "待删除数据日志id不能为空") List<String> dataLogIds) {
        service.deleteBatch(dataLogIds);
        return ok("批量删除成功");
    }


    @ApiOperation(value = "通过数据日志id查询详情@(v1.0.0)", notes = "查询数据日志详情")
    @ApiImplicitParam(paramType = "path", value = "数据日志id", name = "dataLogId", required = true)
    @GetMapping(value = "/select/one/{dataLogId}")
    public Result<DataDto> getById(@PathVariable(required = false) @PathNotBlank(message = "数据日志id不能为空") String dataLogId) {
        return ok(service.getById(dataLogId));
    }


    @ApiOperation(value = "数据日志分页查询@(v1.0.0)", notes = "分页查询数据日志")
    @PostMapping(value = "/select/page")
    public Result<PageDto<DataPageDto>> selectPage(@RequestBody DataPageVo vo) {
        return ok(service.pageByModel(vo));
    }
}