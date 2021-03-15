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

import indi.zxiaozhou.skillfull.auth.modules.common.controller.vo.CommonUserIdentityPageVo;
import indi.zxiaozhou.skillfull.auth.modules.common.controller.vo.CommonUserIdentityQueryVo;
import indi.zxiaozhou.skillfull.auth.modules.common.controller.vo.CommonUserIdentityVo;
import indi.zxiaozhou.skillfull.auth.modules.common.service.ICommonUserIdentityService;
import indi.zxiaozhou.skillfull.auth.modules.common.service.dto.CommonUserIdentityDto;
import indi.zxiaozhou.skillfull.auth.modules.common.service.dto.CommonUserIdentityPageDto;
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
 * 实名信息表(CommonUserIdentity)控制层
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:40:50
 * @since JDK1.8
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "CommonUserIdentity", description = "实名信息表Api接口相关", hidden = true)
@RequestMapping(value = UrlPrefix.AUTH + "/commonUserIdentity", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommonUserIdentityController extends BaseController {
    private final ICommonUserIdentityService service;


    @ApiOperation(value = "实名信息表添加@(v1.0.0)", notes = "添加实名信息表", hidden = true)
    @PostMapping(value = "/insert")
    public Result<String> insert(@RequestBody @Valid CommonUserIdentityVo vo) {
        service.save(vo);
        return ok("添加成功");
    }


    @ApiOperation(value = "通过实名信息id修改@(v1.0.0)", notes = "修改实名信息表", hidden = true)
    @ApiImplicitParam(paramType = "path", value = "实名信息id", name = "identityId", required = true)
    @PutMapping(value = "/update/{identityId}")
    public Result<String> update(@PathVariable(required = false) @PathNotBlank(message = "实名信息id不能为空") String identityId,
                                 @RequestBody @Valid CommonUserIdentityVo vo) {
        service.updateById(identityId, vo);
        return ok("修改成功");
    }


    @ApiOperation(value = "实名信息表逻辑删除@(v1.0.0)", notes = "删除实名信息表", hidden = true)
    @ApiImplicitParam(paramType = "path", value = "实名信息id", name = "identityId", required = true)
    @DeleteMapping(value = "/delete-one/{identityId}")
    public Result<String> delete(@PathVariable(required = false) @PathNotBlank(message = "实名信息id不能为空") String identityId) {
        service.deleteById(identityId);
        return ok("删除成功");
    }


    @ApiOperation(value = "实名信息表逻辑批量删除@(v1.0.0)", notes = "批量删除实名信息表", hidden = true)
    @PostMapping(value = "/delete-batch")
    public Result<String> deleteBatch(@RequestBody @NotNullSize(message = "待删除实名信息id不能为空") List<String> identityIds) {
        service.deleteBatch(identityIds);
        return ok("批量删除成功");
    }


    @ApiOperation(value = "通过实名信息id查询详情@(v1.0.0)", notes = "查询实名信息表详情", hidden = true)
    @ApiImplicitParam(paramType = "path", value = "实名信息id", name = "identityId", required = true)
    @GetMapping(value = "/select/one/{identityId}")
    public Result<CommonUserIdentityDto> getById(@PathVariable(required = false) @PathNotBlank(message = "实名信息id不能为空") String identityId) {
        return ok(service.getById(identityId));
    }


    @ApiOperation(value = "通过条件查询实名信息表多条数据@(v1.0.0)", notes = "通过条件查询实名信息表", hidden = true)
    @PostMapping(value = "/select/list")
    public Result<List<CommonUserIdentityDto>> getList(@RequestBody CommonUserIdentityQueryVo vo) {
        return ok(service.selectListByModel(vo));
    }


    @ApiOperation(value = "实名信息表分页查询@(v1.0.0)", notes = "分页查询实名信息表", hidden = true)
    @PostMapping(value = "/select/page")
    public Result<PageDto<CommonUserIdentityPageDto>> selectPage(@RequestBody CommonUserIdentityPageVo vo) {
        return ok(service.page(vo));
    }
}