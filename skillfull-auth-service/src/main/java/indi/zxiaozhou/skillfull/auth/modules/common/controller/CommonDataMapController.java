// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.common.controller;

import indi.zxiaozhou.skillfull.auth.modules.common.controller.vo.CommonDataMapPageVo;
import indi.zxiaozhou.skillfull.auth.modules.common.controller.vo.CommonDataMapQueryVo;
import indi.zxiaozhou.skillfull.auth.modules.common.controller.vo.CommonDataMapVo;
import indi.zxiaozhou.skillfull.auth.modules.common.service.ICommonDataMapService;
import indi.zxiaozhou.skillfull.auth.modules.common.service.dto.CommonDataMapDto;
import indi.zxiaozhou.skillfull.auth.modules.common.service.dto.CommonDataMapPageDto;
import indi.zxiaozhou.skillfull.corecommon.base.Result;
import indi.zxiaozhou.skillfull.corecommon.constant.UrlPrefix;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotNullSize;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.PathNotBlank;
import indi.zxiaozhou.skillfull.coremvc.base.controller.BaseController;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
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
 * 数据映射表(CommonDataMap)控制层
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:40:04
 * @since JDK1.8
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "CommonDataMap", description = "数据映射表Api接口相关", hidden = true)
@RequestMapping(value = UrlPrefix.AUTH + "/commonDataMap", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommonDataMapController extends BaseController {
    private final ICommonDataMapService service;


    @ApiOperation(value = "数据映射表添加@(v1.0.0)", notes = "添加数据映射表", hidden = true)
    @PostMapping(value = "/insert")
    public Result<String> insert(@RequestBody @Valid CommonDataMapVo vo) {
        service.save(vo);
        return ok("添加成功");
    }


    @ApiOperation(value = "通过数据映射id修改@(v1.0.0)", notes = "修改数据映射表", hidden = true)
    @ApiImplicitParam(paramType = "path", value = "数据映射id", name = "dataMapId", required = true)
    @PutMapping(value = "/update/{dataMapId}")
    public Result<String> update(@PathVariable(required = false) @PathNotBlank(message = "数据映射id不能为空") String dataMapId,
                                 @RequestBody @Valid CommonDataMapVo vo) {
        service.updateById(dataMapId, vo);
        return ok("修改成功");
    }


    @ApiOperation(value = "数据映射表逻辑删除@(v1.0.0)", notes = "删除数据映射表", hidden = true)
    @ApiImplicitParam(paramType = "path", value = "数据映射id", name = "dataMapId", required = true)
    @DeleteMapping(value = "/delete-one/{dataMapId}")
    public Result<String> delete(@PathVariable(required = false) @PathNotBlank(message = "数据映射id不能为空") String dataMapId) {
        service.deleteById(dataMapId);
        return ok("删除成功");
    }


    @ApiOperation(value = "数据映射表逻辑批量删除@(v1.0.0)", notes = "批量删除数据映射表", hidden = true)
    @PostMapping(value = "/delete-batch")
    public Result<String> deleteBatch(@RequestBody @NotNullSize(message = "待删除数据映射id不能为空") List<String> dataMapIds) {
        service.deleteBatch(dataMapIds);
        return ok("批量删除成功");
    }


    @ApiOperation(value = "通过数据映射id查询详情@(v1.0.0)", notes = "查询数据映射表详情", hidden = true)
    @ApiImplicitParam(paramType = "path", value = "数据映射id", name = "dataMapId", required = true)
    @GetMapping(value = "/select/one/{dataMapId}")
    public Result<CommonDataMapDto> getById(@PathVariable(required = false) @PathNotBlank(message = "数据映射id不能为空") String dataMapId) {
        return ok(service.getById(dataMapId));
    }


    @ApiOperation(value = "通过条件查询数据映射表多条数据@(v1.0.0)", notes = "通过条件查询数据映射表", hidden = true)
    @PostMapping(value = "/select/list")
    public Result<List<CommonDataMapDto>> getList(@RequestBody CommonDataMapQueryVo vo) {
        return ok(service.selectListByModel(vo));
    }


    @ApiOperation(value = "数据映射表分页查询@(v1.0.0)", notes = "分页查询数据映射表", hidden = true)
    @PostMapping(value = "/select/page")
    public Result<PageDto<CommonDataMapPageDto>> selectPage(@RequestBody CommonDataMapPageVo vo) {
        return ok(service.page(vo));
    }
}