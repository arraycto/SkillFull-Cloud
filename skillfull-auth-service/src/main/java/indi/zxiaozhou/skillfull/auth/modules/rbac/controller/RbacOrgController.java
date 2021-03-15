// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.rbac.controller;

import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacOrgPageVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacOrgVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.IRbacOrgService;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacOrgHasChildrenDto;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacOrgTreeDto;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacOrgTreePageDto;
import indi.zxiaozhou.skillfull.corecommon.base.Result;
import indi.zxiaozhou.skillfull.corecommon.constant.CacheConstant;
import indi.zxiaozhou.skillfull.corecommon.constant.UrlPrefix;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.PathNotBlank;
import indi.zxiaozhou.skillfull.coremvc.base.controller.BaseController;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 组织表(RbacOrg)控制层
 *
 * @author zxiaozhou
 * @date 2021-01-19 13:02:50
 * @since JDK11
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "RbacOrg", description = "组织机构")
@RequestMapping(value = UrlPrefix.AUTH + "/rbac/org", produces = MediaType.APPLICATION_JSON_VALUE)
public class RbacOrgController extends BaseController {
    private final IRbacOrgService service;


    @ApiOperation(value = "组织表添加@(v1.0.0)", notes = "添加组织表")
    @PostMapping(value = "/insert")
    @CacheEvict(value = CacheConstant.ORG_CACHE, allEntries = true)
    public Result<String> insert(@RequestBody @Valid RbacOrgVo vo) {
        service.save(vo);
        return ok("添加成功");
    }


    @ApiOperation(value = "通过组织id修改@(v1.0.0)", notes = "修改组织表")
    @ApiImplicitParam(paramType = "path", value = "组织id", name = "orgId", required = true)
    @PutMapping(value = "/update/{orgId}")
    @CacheEvict(value = CacheConstant.ORG_CACHE, allEntries = true)
    public Result<String> update(@PathVariable(required = false) @PathNotBlank(message = "组织id不能为空") String orgId,
                                 @RequestBody @Valid RbacOrgVo vo) {
        service.updateById(orgId, vo);
        return ok("修改成功");
    }


    @ApiOperation(value = "通过机构id修改机构状态@(v1.0.0)", notes = "通过机构id修改机构状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", value = "机构id", name = "orgId", required = true),
            @ApiImplicitParam(paramType = "query", value = "类型:0-禁用,1-启用", name = "type", required = true)
    })
    @GetMapping(value = "/update/org/state")
    @CacheEvict(value = CacheConstant.ORG_CACHE, allEntries = true)
    public Result<String> updateOrgState(@RequestParam(required = false) @NotBlank(message = "机构id不能为空") String orgId,
                                         @RequestParam(required = false) @NotNull(message = "操作类型不能为空") Integer type) {
        service.updateOrgState(orgId, type);
        return ok(type == 0 ? "禁用成功" : "启用成功");
    }

    @ApiOperation(value = "组织表逻辑删除@(v1.0.0)", notes = "删除组织表")
    @ApiImplicitParam(paramType = "path", value = "组织id", name = "orgId", required = true)
    @DeleteMapping(value = "/delete-one/{orgId}")
    @CacheEvict(value = CacheConstant.ORG_CACHE, allEntries = true)
    public Result<String> delete(@PathVariable(required = false) @PathNotBlank(message = "组织id不能为空") String orgId) {
        service.deleteById(orgId);
        return ok("删除成功");
    }


    @ApiOperation(value = "组织表分页查询@(v1.0.0)", notes = "分页查询组织父级")
    @GetMapping(value = "/select/org-list")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", value = "类型:0-所有,1-有效,默认1", name = "type"),
            @ApiImplicitParam(paramType = "query", value = "父级id", name = "parentId")
    })
    @Cacheable(value = CacheConstant.ORG_CACHE, key = "#parentId+#type")
    public Result<List<RbacOrgHasChildrenDto>> selectOrgList(@RequestParam(defaultValue = "1", required = false) Integer type,
                                                             @RequestParam(defaultValue = "", required = false) String parentId) {
        return ok(service.selectOrgList(type, parentId));
    }


    @ApiOperation(value = "获取组织机构树@(v1.0.0)", notes = "获取组织机构树")
    @GetMapping(value = "/select/org-tree-list")
    @ApiImplicitParam(paramType = "query", value = "类型:0-所有,1-有效,默认1", name = "type")
    @Cacheable(value = CacheConstant.ORG_CACHE, key = "#type")
    public Result<List<RbacOrgTreeDto>> selectOrgTreeList(@RequestParam(defaultValue = "1", required = false) Integer type) {
        return ok(service.selectOrgTreeList(type));
    }


    @ApiOperation(value = "组织表分页查询@(v1.0.0)", notes = "分页查询组织父级")
    @PostMapping(value = "/select/page")
    public Result<PageDto<RbacOrgTreePageDto>> selectPage(@RequestBody RbacOrgPageVo vo) {
        return ok(service.pageByModel(vo));
    }


    @ApiOperation(value = "通过父级id查询下级信息@(v1.0.0)", notes = "通过父级id查询下级信息")
    @GetMapping(value = "/select/children/{parentId}")
    public Result<List<RbacOrgTreePageDto>> getChildren(@PathVariable(required = false) @PathNotBlank(message = "父级机构id") String parentId) {
        return ok(service.getChildren(parentId));
    }
}