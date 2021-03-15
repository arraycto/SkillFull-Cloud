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

import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacPositionPageVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo.RbacPositionVo;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.IRbacPositionService;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacPositionDto;
import indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto.RbacPositionPageDto;
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
 * 职位表(RbacPosition)控制层
 *
 * @author zxiaozhou
 * @date 2021-01-19 13:42:15
 * @since JDK11
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "RbacPosition", description = "职位")
@RequestMapping(value = UrlPrefix.AUTH + "/rbac/position", produces = MediaType.APPLICATION_JSON_VALUE)
public class RbacPositionController extends BaseController {
    private final IRbacPositionService service;


    @ApiOperation(value = "职位表添加@(v1.0.0)", notes = "添加职位表")
    @PostMapping(value = "/insert")
    public Result<String> insert(@RequestBody @Valid RbacPositionVo vo) {
        service.save(vo);
        return ok("添加成功");
    }


    @ApiOperation(value = "通过职位id修改@(v1.0.0)", notes = "修改职位表")
    @ApiImplicitParam(paramType = "path", value = "职位id", name = "positionId", required = true)
    @PutMapping(value = "/update/{positionId}")
    public Result<String> update(@PathVariable(required = false) @PathNotBlank(message = "职位id不能为空") String positionId,
                                 @RequestBody @Valid RbacPositionVo vo) {
        service.updateById(positionId, vo);
        return ok("修改成功");
    }


    @ApiOperation(value = "职位表逻辑删除@(v1.0.0)", notes = "删除职位表")
    @ApiImplicitParam(paramType = "path", value = "职位id", name = "positionId", required = true)
    @DeleteMapping(value = "/delete-one/{positionId}")
    public Result<String> delete(@PathVariable(required = false) @PathNotBlank(message = "职位id不能为空") String positionId) {
        service.deleteById(positionId);
        return ok("删除成功");
    }


    @ApiOperation(value = "通过职位id修改职位状态@(v1.0.0)", notes = "通过职位id修改职位状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", value = "职位id", name = "positionId", required = true),
            @ApiImplicitParam(paramType = "query", value = "类型:0-禁用,1-启用", name = "type", required = true)
    })
    @GetMapping(value = "/update/position/state")
    public Result<String> updatePositionState(@RequestParam(required = false) @NotBlank(message = "职位id不能为空") String positionId,
                                              @RequestParam(required = false)
                                              @NotNull(message = "操作类型不能为空")
                                              @Min(value = 0, message = "操作类型只能为0、1")
                                              @Max(value = 1, message = "操作类型只能为0、1") Integer type) {
        service.updatePositionState(positionId, type);
        return ok(type == 0 ? "职位禁用成功" : "职位启用成功");
    }


    @ApiOperation(value = "通过条件查询职位表多条数据@(v1.0.0)", notes = "通过条件查询职位表")
    @GetMapping(value = "/select/list-all")
    public Result<List<RbacPositionDto>> getAllList() {
        return ok(service.getAllList());
    }


    @ApiOperation(value = "职位表分页查询@(v1.0.0)", notes = "分页查询职位表")
    @PostMapping(value = "/select/page")
    public Result<PageDto<RbacPositionPageDto>> selectPage(@RequestBody RbacPositionPageVo vo) {
        return ok(service.pageByModel(vo));
    }
}