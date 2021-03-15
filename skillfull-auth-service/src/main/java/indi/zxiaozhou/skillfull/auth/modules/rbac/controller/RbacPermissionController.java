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

import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacPermissionPageVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacPermissionVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.IRbacPermissionService;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacPermissionDto;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacPermissionPageDto;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacPermissionTreeDto;
import indi.zxiaozhou.skillfull.corecommon.base.Result;
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
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 权限表(Permission)控制层
 *
 * @author zxiaozhou
 * @date 2020-09-26 17:16:14
 * @since JDK11
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "Permission", description = "权限管理")
@RequestMapping(value = UrlPrefix.AUTH + "/rbac/permission", produces = MediaType.APPLICATION_JSON_VALUE)
public class RbacPermissionController extends BaseController {
    private final IRbacPermissionService service;


    @ApiOperation(value = "权限表添加@(v1.0.0)", notes = "添加权限表")
    @PostMapping(value = "/insert")
    public Result<String> insert(@RequestBody @Valid RbacPermissionVo vo) {
        service.save(vo);
        return ok("添加成功");
    }


    @ApiOperation(value = "通过权限id修改@(v1.0.0)", notes = "修改权限表")
    @ApiImplicitParam(paramType = "path", value = "权限id", name = "permissionId", required = true)
    @PutMapping(value = "/update/{permissionId}")
    public Result<String> update(@PathVariable(required = false) @PathNotBlank(message = "权限id不能为空") String permissionId, @RequestBody @Valid RbacPermissionVo vo) {
        service.updateById(permissionId, vo);
        return ok("修改成功");
    }


    @ApiOperation(value = "权限表逻辑删除@(v1.0.0)", notes = "删除权限表")
    @ApiImplicitParam(paramType = "path", value = "权限id", name = "permissionId", required = true)
    @DeleteMapping(value = "/delete/{permissionId}")
    public Result<String> delete(@PathVariable(required = false) @PathNotBlank(message = "权限id不能为空") String permissionId) {
        service.deleteById(permissionId);
        return ok("删除成功");
    }


    @ApiOperation(value = "通过权限id查询详情@(v1.0.0)", notes = "查询权限表详情")
    @ApiImplicitParam(paramType = "path", value = "权限id", name = "permissionId", required = true)
    @GetMapping(value = "/select/one/{permissionId}")
    public Result<RbacPermissionDto> getById(@PathVariable(required = false) @PathNotBlank(message = "权限id不能为空") String permissionId) {
        return ok(service.getById(permissionId));
    }


    @ApiOperation(value = "通过权限id修改权限菜单状态@(v1.0.0)", notes = "通过权限id修改权限菜单状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", value = "权限id", name = "permissionId", required = true),
            @ApiImplicitParam(paramType = "query", value = "类型:0-禁用,1-启用", name = "type", required = true)
    })
    @GetMapping(value = "/update/permission/state")
    public Result<String> updatePermissionState(@RequestParam(required = false) @NotBlank(message = "权限id不能为空") String permissionId,
                                                @RequestParam(required = false) @NotNull(message = "操作类型不能为空") Integer type) {
        service.updatePermissionState(permissionId, type);
        return ok(type == 0 ? "禁用成功" : "启用成功");
    }


    @ApiOperation(value = "获取菜单权限树@(v1.0.0)", notes = "获取菜单权限树")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", value = "类型:0-目录,1-目录、菜单,2-目录、菜单、按钮权限", name = "type"),
            @ApiImplicitParam(paramType = "query", value = "状态:1-有效、2-所有,默认2", name = "status")
    })
    @GetMapping(value = "/select/tree")
    public Result<List<RbacPermissionTreeDto>> getPermissionTree(@RequestParam(defaultValue = "0", required = false) Integer type,
                                                                 @RequestParam(defaultValue = "2", required = false) Integer status) {
        return ok(service.getPermissionTree(type, status));
    }


    @ApiOperation(value = "权限表分页查询@(v1.0.0)", notes = "分页查询权限表")
    @PostMapping(value = "/select/page")
    public Result<PageDto<RbacPermissionPageDto>> selectPage(@RequestBody RbacPermissionPageVo vo) {
        return ok(service.pageByModel(vo));
    }


    @ApiOperation(value = "通过父级id查询下级信息@(v1.0.0)", notes = "通过父级id查询下级信息")
    @GetMapping(value = "/select/children/{parentId}")
    public Result<List<RbacPermissionPageDto>> getChildren(@PathVariable(required = false) @PathNotBlank(message = "父级权限id") String parentId) {
        return ok(service.getChildren(parentId));
    }
}