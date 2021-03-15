// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.common.controller;

import indi.zxiaozhou.skillfull.corecommon.base.Result;
import indi.zxiaozhou.skillfull.corecommon.constant.CacheConstant;
import indi.zxiaozhou.skillfull.corecommon.constant.UrlPrefix;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotNullSize;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.PathNotBlank;
import indi.zxiaozhou.skillfull.coremvc.base.controller.BaseController;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.system.modules.common.controller.vo.CommonAreaPageVo;
import indi.zxiaozhou.skillfull.system.modules.common.controller.vo.CommonAreaVo;
import indi.zxiaozhou.skillfull.system.modules.common.service.ICommonAreaService;
import indi.zxiaozhou.skillfull.system.modules.common.service.dto.CommonAreaDto;
import indi.zxiaozhou.skillfull.system.modules.common.service.dto.CommonAreaPageDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 区域表(CommonArea)控制层
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:25:00
 * @since JDK11
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "CommonArea", description = "区域表Api接口相关")
@RequestMapping(value = UrlPrefix.SYSTEM + "/common/area", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommonAreaController extends BaseController {
    private final ICommonAreaService service;


    @ApiOperation(value = "区域表添加@(v1.0.0)", notes = "添加区域表")
    @PostMapping(value = "/insert")
    public Result<String> insert(@RequestBody @Valid CommonAreaVo vo) {
        service.save(vo);
        return ok("添加成功");
    }


    @ApiOperation(value = "通过区域id修改@(v1.0.0)", notes = "修改区域表")
    @ApiImplicitParam(paramType = "path", value = "区域id", name = "areaId", required = true)
    @PutMapping(value = "/update/{areaId}")
    @CacheEvict(value = CacheConstant.AREA_CACHE, allEntries = true)
    public Result<String> update(@PathVariable(required = false) @PathNotBlank(message = "区域id不能为空") String areaId,
                                 @RequestBody @Valid CommonAreaVo vo) {
        service.updateById(areaId, vo);
        return ok("修改成功");
    }


    @ApiOperation(value = "区域表逻辑删除@(v1.0.0)", notes = "删除区域表")
    @ApiImplicitParam(paramType = "path", value = "区域id", name = "areaId", required = true)
    @DeleteMapping(value = "/delete-one/{areaId}")
    @CacheEvict(value = CacheConstant.AREA_CACHE, allEntries = true)
    public Result<String> delete(@PathVariable(required = false) @PathNotBlank(message = "区域id不能为空") String areaId) {
        service.deleteById(areaId);
        return ok("删除成功");
    }


    @ApiOperation(value = "通过区域id查询详情@(v1.0.0)", notes = "查询路由详情")
    @ApiImplicitParam(paramType = "path", value = "区域id", name = "areaId", required = true)
    @GetMapping(value = "/select/one/{areaId}")
    public Result<CommonAreaDto> getById(@PathVariable(required = false) @PathNotBlank(message = "区域id不能为空") String areaId) {
        return ok(service.getById(areaId));
    }

    
    @ApiOperation(value = "区域表逻辑批量删除@(v1.0.0)", notes = "批量删除区域表")
    @PostMapping(value = "/delete-batch")
    @CacheEvict(value = CacheConstant.AREA_CACHE, allEntries = true)
    public Result<String> deleteBatch(@RequestBody @NotNullSize(message = "待删除区域id不能为空") List<String> areaIds) {
        service.deleteBatch(areaIds);
        return ok("批量删除成功");
    }


    @ApiOperation(value = "通过上级区域编码获取下级@(v1.0.0)", notes = "通过上级区域编码获取下级(如果为空则查询顶级)")
    @GetMapping(value = "/select/list")
    @Cacheable(value = CacheConstant.AREA_CACHE, key = "#parentId")
    public Result<List<CommonAreaDto>> getList(@RequestParam(required = false, defaultValue = "") String parentId) {
        return ok(service.selectList(parentId));
    }


    @ApiOperation(value = "区域表分页查询@(v1.0.0)", notes = "分页查询区域表(分页只查询顶级)")
    @PostMapping(value = "/select/page")
    public Result<PageDto<CommonAreaPageDto>> selectPage(@RequestBody CommonAreaPageVo vo) {
        return ok(service.pageByModel(vo));
    }


    @ApiOperation(value = "区域表查询下级@(v1.0.0)", notes = "区域表查询下级")
    @ApiImplicitParam(paramType = "path", value = "区域父级id", name = "parentId", required = true)
    @GetMapping(value = "/select/children/{parentId}")
    public Result<List<CommonAreaPageDto>> selectPageChildren(@PathVariable(required = false) @PathNotBlank(message = "区域上级id不能为空") String parentId) {
        return ok(service.selectPageChildren(parentId));
    }
}