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
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotNullSize;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.PathNotBlank;
import indi.zxiaozhou.skillfull.coremvc.base.controller.BaseController;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageSourcePageVo;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageSourceQueryVo;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageSourceVo;
import indi.zxiaozhou.skillfull.system.modules.manage.service.IManageSourceService;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageSourceDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageSourcePageDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 数据源表(ManageSource)控制层
 *
 * @author zxiaozhou
 * @date 2020-11-02 07:58:30
 * @since JDK1.8
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "ManageSource", description = "数据源表Api接口相关", hidden = true)
@RequestMapping(value = UrlPrefix.SYSTEM + "/manage/source", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManageSourceController extends BaseController {
    private final IManageSourceService service;


    @ApiOperation(value = "数据源表添加@(v1.0.0)", notes = "添加数据源表", hidden = true)
    @PostMapping(value = "/insert")
    public Result<String> insert(@RequestBody @Valid ManageSourceVo vo) {
        service.save(vo);
        return ok("添加成功");
    }


    @ApiOperation(value = "通过数据源id修改@(v1.0.0)", notes = "修改数据源表", hidden = true)
    @ApiImplicitParam(paramType = "path", value = "数据源id", name = "dataSourceId", required = true)
    @PutMapping(value = "/update/{dataSourceId}")
    public Result<String> update(@PathVariable(required = false) @PathNotBlank(message = "数据源id不能为空") String dataSourceId, @RequestBody @Valid ManageSourceVo vo) {
        service.updateById(dataSourceId, vo);
        return ok("修改成功");
    }


    @ApiOperation(value = "数据源表逻辑删除@(v1.0.0)", notes = "删除数据源表", hidden = true)
    @ApiImplicitParam(paramType = "path", value = "数据源id", name = "dataSourceId", required = true)
    @DeleteMapping(value = "/delete-one/{dataSourceId}")
    public Result<String> delete(@PathVariable(required = false) @PathNotBlank(message = "数据源id不能为空") String dataSourceId) {
        service.deleteById(dataSourceId);
        return ok("删除成功");
    }


    @ApiOperation(value = "数据源表逻辑批量删除@(v1.0.0)", notes = "批量删除数据源表", hidden = true)
    @PostMapping(value = "/delete-batch")
    public Result<String> deleteBatch(@RequestBody @NotNullSize(message = "待删除数据源id不能为空") List<String> dataSourceIds) {
        service.deleteBatch(dataSourceIds);
        return ok("批量删除成功");
    }


    @ApiOperation(value = "通过数据源id查询详情@(v1.0.0)", notes = "查询数据源表详情", hidden = true)
    @ApiImplicitParam(paramType = "path", value = "数据源id", name = "dataSourceId", required = true)
    @GetMapping(value = "/select/one/{dataSourceId}")
    public Result<ManageSourceDto> getById(@PathVariable(required = false) @PathNotBlank(message = "数据源id不能为空") String dataSourceId) {
        return ok(service.getById(dataSourceId));
    }


    @ApiOperation(value = "通过条件查询数据源表多条数据@(v1.0.0)", notes = "通过条件查询数据源表", hidden = true)
    @PostMapping(value = "/select/list")
    public Result<List<ManageSourceDto>> getList(@RequestBody ManageSourceQueryVo vo) {
        return ok(service.selectListByModel(vo));
    }


    @ApiOperation(value = "数据源表分页查询@(v1.0.0)", notes = "分页查询数据源表", hidden = true)
    @PostMapping(value = "/select/page")
    public Result<PageDto<ManageSourcePageDto>> selectPage(@RequestBody ManageSourcePageVo vo) {
        return ok(service.page(vo));
    }
}