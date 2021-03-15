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

import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacRolePageVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacRolePermissionVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacRoleVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.IRbacCorrelateRoleService;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.IRbacRolePermissionService;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.IRbacRoleService;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacRoleDto;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacRoleEffectiveDto;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacRolePageDto;
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
import java.util.List;

/**
 * 角色表(RbacRole)控制层
 *
 * @author zxiaozhou
 * @date 2020-10-08 13:44:02
 * @since JDK11
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "RbacRole", description = "角色管理")
@RequestMapping(value = UrlPrefix.AUTH + "/rbac/role", produces = MediaType.APPLICATION_JSON_VALUE)
public class RbacRoleController extends BaseController {
    private final IRbacRoleService service;
    private final IRbacRolePermissionService permissionService;
    private final IRbacCorrelateRoleService rbacCorrelateRoleService;


    @ApiOperation(value = "角色表添加@(v1.0.0)", notes = "添加角色表")
    @PostMapping(value = "/insert")
    public Result<String> insert(@RequestBody @Valid RbacRoleVo vo) {
        service.save(vo);
        return ok("添加成功");
    }


    @ApiOperation(value = "关联角色权限信息@(v1.0.0)", notes = "关联角色权限信息")
    @PostMapping(value = "/insert-permission")
    public Result<String> editPermission(@RequestBody @Valid RbacRolePermissionVo vo) {
        permissionService.updatePermission(vo);
        return ok("添加成功");
    }

    
    @ApiOperation(value = "获取有效的角色@(v1.0.0)", notes = "获取有效的角色")
    @GetMapping(value = "/select/role-info")
    public Result<List<RbacRoleEffectiveDto>> getEffectiveRoles() {
        return ok(service.getEffectiveRoles());
    }

    @ApiOperation(value = "通过角色id修改@(v1.0.0)", notes = "修改角色表")
    @ApiImplicitParam(paramType = "path", value = "角色id", name = "roleId", required = true)
    @PutMapping(value = "/update/{roleId}")
    public Result<String> update(@PathVariable(required = false) @PathNotBlank(message = "角色id不能为空") String roleId, @RequestBody @Valid RbacRoleVo vo) {
        service.updateById(roleId, vo);
        return ok("修改成功");
    }

    @ApiOperation(value = "角色表逻辑删除@(v1.0.0)", notes = "删除角色表")
    @ApiImplicitParam(paramType = "path", value = "角色id", name = "roleId", required = true)
    @DeleteMapping(value = "/delete/{roleId}")
    public Result<String> delete(@PathVariable(required = false) @PathNotBlank(message = "角色id不能为空") String roleId) {
        service.deleteById(roleId);
        return ok("删除成功");
    }


    @ApiOperation(value = "通过角色id查询详情@(v1.0.0)", notes = "查询角色表详情")
    @ApiImplicitParam(paramType = "path", value = "角色id", name = "roleId", required = true)
    @GetMapping(value = "/select/one/{roleId}")
    public Result<RbacRoleDto> getById(@PathVariable(required = false) @PathNotBlank(message = "角色id不能为空") String roleId) {
        return ok(service.getById(roleId));
    }


    @ApiOperation(value = "角色启用或禁用@(v1.0.0)", notes = "角色启用或禁用")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", value = "角色id", name = "roleId"),
            @ApiImplicitParam(paramType = "query", value = "状态:0-禁用、1-启用", name = "status")
    })
    @GetMapping(value = "/update/status")
    public Result<String> updateStatus(@RequestParam(required = false) String roleId,
                                       @RequestParam(required = false) Integer status) {
        service.updateStatus(roleId, status);
        return ok(status == 0 ? "禁用成功" : "启用成功");
    }


    @ApiOperation(value = "角色表分页查询@(v1.0.0)", notes = "分页查询角色表")
    @PostMapping(value = "/select/page")
    public Result<PageDto<RbacRolePageDto>> selectPage(@RequestBody RbacRolePageVo vo) {
        return ok(service.pageByModel(vo));
    }


}