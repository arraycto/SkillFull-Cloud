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

import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacCorrelateRoleQueryVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacCorrelateRoleVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacUserPageVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacUserVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.IRbacCorrelateRoleService;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.IRbacUserService;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacCorrelateRoleDto;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacUserDto;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacUserPageDto;
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户表(User)控制层
 *
 * @author zxiaozhou
 * @date 2020-09-26 17:16:16
 * @since JDK11
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "User", description = "用户管理")
@RequestMapping(value = UrlPrefix.AUTH + "/rbac/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class RbacUserController extends BaseController {
    private final IRbacUserService service;
    private final IRbacCorrelateRoleService correlateRoleService;


    @ApiOperation(value = "用户表添加@(v1.0.0)", notes = "添加用户表")
    @PostMapping(value = "/insert")
    public Result<String> insert(@RequestBody @Valid RbacUserVo vo) {
        String newPassword = service.save(vo);
        return ok(newPassword, "添加成功,初始密码为:" + newPassword);
    }

    @ApiOperation(value = "获取关联的角色信息@(v1.0.0)", notes = "获取关联的角色信息")
    @PostMapping(value = "/select/role-correlate")
    public Result<List<RbacCorrelateRoleDto>> getCorrelateRoleInfos(@RequestBody @Valid RbacCorrelateRoleQueryVo vo) {
        return ok(correlateRoleService.selectListByModel(vo));
    }


    @ApiOperation(value = "用户授权@(v1.0.0)", notes = "用户授权")
    @PostMapping(value = "/auth")
    public Result<String> userAuth(@RequestBody @Valid RbacCorrelateRoleVo vo) {
        correlateRoleService.updateRoleCorrelate(vo);
        return ok("授权成功");
    }


    @ApiOperation(value = "重置用户密码@(v1.0.0)", notes = "重置用户密码")
    @ApiImplicitParam(paramType = "path", value = "用户id", name = "userId", required = true)
    @GetMapping(value = "/reset-password/{userId}")
    public Result<String> resetPassword(@PathVariable(required = false) @PathNotBlank(message = "用户id不能为空") String userId) {
        String newPassword = service.resetPassword(userId);
        return ok(newPassword, "重置密码成功,新密码:" + newPassword);
    }


    @ApiOperation(value = "通过用户id修改@(v1.0.0)", notes = "修改用户表")
    @ApiImplicitParam(paramType = "path", value = "用户id", name = "userId", required = true)
    @PutMapping(value = "/update/{userId}")
    public Result<String> update(@PathVariable(required = false) @PathNotBlank(message = "用户id不能为空") String userId,
                                 @RequestBody @Valid RbacUserVo vo) {
        service.updateById(userId, vo);
        return ok("修改成功");
    }


    @ApiOperation(value = "通过用户id修改用户状态@(v1.0.0)", notes = "通过用户id修改用户状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", value = "用户id", name = "userId", required = true),
            @ApiImplicitParam(paramType = "query", value = "类型:2-冻结,1-解冻", name = "type", required = true)
    })
    @GetMapping(value = "/update/user/state")
    public Result<String> updateUserState(@RequestParam(required = false) @NotBlank(message = "用户id不能为空") String userId,
                                          @RequestParam(required = false) @NotNull(message = "操作类型不能为空")
                                          @Min(value = 1, message = "操作类型只能为1、2")
                                          @Max(value = 2, message = "操作类型只能为1、2") Integer type) {
        service.updateUserState(userId, type);
        return ok("用户状态修改成功");
    }


    @ApiOperation(value = "用户表逻辑删除@(v1.0.0)", notes = "删除用户表")
    @ApiImplicitParam(paramType = "path", value = "用户id", name = "userId", required = true)
    @DeleteMapping(value = "/delete/{userId}")
    public Result<String> delete(@PathVariable(required = false) @PathNotBlank(message = "用户id不能为空") String userId) {
        service.deleteById(userId);
        return ok("删除成功");
    }


    @ApiOperation(value = "通过用户id查询详情@(v1.0.0)", notes = "查询用户表详情")
    @ApiImplicitParam(paramType = "path", value = "用户id", name = "userId", required = true)
    @GetMapping(value = "/select/one/{userId}")
    public Result<RbacUserDto> getById(@PathVariable(required = false) @PathNotBlank(message = "用户id不能为空") String userId) {
        return ok(service.getById(userId));
    }


    @ApiOperation(value = "用户表分页查询@(v1.0.0)", notes = "分页查询用户表")
    @PostMapping(value = "/select/page")
    public Result<PageDto<RbacUserPageDto>> selectPage(@RequestBody RbacUserPageVo vo) {
        return ok(service.pageByModel(vo));
    }
}