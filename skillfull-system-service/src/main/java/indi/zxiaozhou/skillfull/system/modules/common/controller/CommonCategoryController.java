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
import indi.zxiaozhou.skillfull.system.modules.common.controller.vo.CommonCategoryPageVo;
import indi.zxiaozhou.skillfull.system.modules.common.controller.vo.CommonCategoryVo;
import indi.zxiaozhou.skillfull.system.modules.common.service.ICommonCategoryService;
import indi.zxiaozhou.skillfull.system.modules.common.service.dto.CommonCategoryDto;
import indi.zxiaozhou.skillfull.system.modules.common.service.dto.CommonCategoryPageDto;
import indi.zxiaozhou.skillfull.system.modules.common.service.dto.CommonCategoryTreeDto;
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
 * 分类字典表(CommonCategory)控制层
 *
 * @author zxiaozhou
 * @date 2021-01-07 23:39:52
 * @since JDK11
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "CommonCategory", description = "分类字典")
@RequestMapping(value = UrlPrefix.SYSTEM + "/common/category", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommonCategoryController extends BaseController {
    private final ICommonCategoryService service;


    @ApiOperation(value = "分类字典表添加@(v1.0.0)", notes = "添加分类字典表")
    @PostMapping(value = "/insert")
    public Result<String> insert(@RequestBody @Valid CommonCategoryVo vo) {
        service.save(vo);
        return ok("添加成功");
    }


    @ApiOperation(value = "通过分类id修改@(v1.0.0)", notes = "修改分类字典表")
    @ApiImplicitParam(paramType = "path", value = "分类id", name = "categoryId", required = true)
    @PutMapping(value = "/update/{categoryId}")
    @CacheEvict(value = CacheConstant.DICT_CATEGORY_CACHE, allEntries = true)
    public Result<String> update(@PathVariable(required = false) @PathNotBlank(message = "分类id不能为空") String categoryId,
                                 @RequestBody @Valid CommonCategoryVo vo) {
        service.updateById(categoryId, vo);
        return ok("修改成功");
    }


    @ApiOperation(value = "分类字典表逻辑删除@(v1.0.0)", notes = "删除分类字典表")
    @ApiImplicitParam(paramType = "path", value = "分类id", name = "categoryId", required = true)
    @DeleteMapping(value = "/delete-one/{categoryId}")
    @CacheEvict(value = CacheConstant.DICT_CATEGORY_CACHE, allEntries = true)
    public Result<String> delete(@PathVariable(required = false) @PathNotBlank(message = "分类id不能为空") String categoryId) {
        service.deleteById(categoryId);
        return ok("删除成功");
    }


    @ApiOperation(value = "分类字典表逻辑批量删除@(v1.0.0)", notes = "批量删除分类字典表")
    @PostMapping(value = "/delete-batch")
    @CacheEvict(value = CacheConstant.DICT_CATEGORY_CACHE, allEntries = true)
    public Result<String> deleteBatch(@RequestBody @NotNullSize(message = "待删除分类id不能为空") List<String> categoryIds) {
        service.deleteBatch(categoryIds);
        return ok("批量删除成功");
    }


    @ApiOperation(value = "根据统一分类id查询分类@(v1.0.0)", notes = "根据统一分类id查询分类")
    @GetMapping(value = "/select/list/{categoryCommonCode}")
    @Cacheable(value = CacheConstant.DICT_CATEGORY_CACHE, key = "#categoryCommonCode")
    @ApiImplicitParam(paramType = "path", value = "统一分类编码", name = "categoryCommonCode", required = true)
    public Result<List<CommonCategoryDto>> getList(@PathVariable(required = false) @PathNotBlank(message = "统一分类编码不能为空") String categoryCommonCode) {
        return ok(service.selectListByCommonCode(categoryCommonCode));
    }


    @ApiOperation(value = "通过统一分类id查询详情@(v1.0.0)", notes = "查询路由详情")
    @ApiImplicitParam(paramType = "path", value = "分类id", name = "categoryId", required = true)
    @GetMapping(value = "/select/one/{categoryId}")
    public Result<CommonCategoryDto> getById(@PathVariable(required = false) @PathNotBlank(message = "路由id不能为空") String categoryId) {
        return ok(service.getById(categoryId));
    }


    @ApiOperation(value = " 根据统一分类id查询分类(树形)@(v1.0.0)", notes = " 根据统一分类id查询分类(树形)")
    @GetMapping(value = "/select/list-tree/{categoryCommonCode}")
    @Cacheable(value = CacheConstant.DICT_CATEGORY_CACHE, key = "#categoryCommonCode")
    @ApiImplicitParam(paramType = "path", value = "统一分类编码", name = "categoryCommonCode", required = true)
    public Result<List<CommonCategoryTreeDto>> getTreeList(@PathVariable(required = false) @PathNotBlank(message = "统一分类编码不能为空") String categoryCommonCode) {
        return ok(service.selectTreeListByCommonCode(categoryCommonCode));
    }


    @ApiOperation(value = " 查询所有分类编码(树形)@(v1.0.0)", notes = " 查询所有分类编码(树形)")
    @GetMapping(value = "/select/list-tree-all")
    public Result<List<CommonCategoryTreeDto>> getAllTreeList() {
        return ok(service.selectAllTree());
    }

    @ApiOperation(value = "分类字典表分页查询@(v1.0.0)", notes = "分页查询分类字典表(只查询顶级)")
    @PostMapping(value = "/select/page")
    public Result<PageDto<CommonCategoryPageDto>> selectPage(@RequestBody CommonCategoryPageVo vo) {
        return ok(service.pageByModel(vo));
    }


    @ApiOperation(value = "分类字典表查询下级@(v1.0.0)", notes = "分类字典表查询下级")
    @ApiImplicitParam(paramType = "path", value = "上级分类id", name = "parentId", required = true)
    @GetMapping(value = "/select/children/{parentId}")
    public Result<List<CommonCategoryPageDto>> selectPageChildren(@PathVariable(required = false) @PathNotBlank(message = "上级分类id不能为空") String parentId) {
        return ok(service.selectPageChildren(parentId));
    }
}