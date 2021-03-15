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
import indi.zxiaozhou.skillfull.corecommon.constant.UrlPrefix;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.PathNotBlank;
import indi.zxiaozhou.skillfull.coremvc.base.controller.BaseController;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.system.modules.common.controller.vo.CommonSystemPageVo;
import indi.zxiaozhou.skillfull.system.modules.common.controller.vo.CommonSystemVo;
import indi.zxiaozhou.skillfull.system.modules.common.service.ICommonSystemService;
import indi.zxiaozhou.skillfull.system.modules.common.service.dto.CommonSystemDto;
import indi.zxiaozhou.skillfull.system.modules.common.service.dto.CommonSystemPageDto;
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
 * 系统表(CommonSystem)控制层
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:25:29
 * @since JDK11
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "CommonSystem", description = "系统表")
@RequestMapping(value = UrlPrefix.SYSTEM + "/common/system", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommonSystemController extends BaseController {
    private final ICommonSystemService service;


    @ApiOperation(value = "系统表添加@(v1.0.0)", notes = "添加系统表")
    @PostMapping(value = "/insert")
    public Result<String> insert(@RequestBody @Valid CommonSystemVo vo) {
        service.save(vo);
        return ok("添加成功");
    }


    @ApiOperation(value = "通过系统id修改@(v1.0.0)", notes = "修改系统表")
    @ApiImplicitParam(paramType = "path", value = "系统id", name = "systemId", required = true)
    @PutMapping(value = "/update/{systemId}")
    public Result<String> update(@PathVariable(required = false) @PathNotBlank(message = "系统id不能为空") String systemId,
                                 @RequestBody @Valid CommonSystemVo vo) {
        service.updateById(systemId, vo);
        return ok("修改成功");
    }


    @ApiOperation(value = "系统表逻辑删除@(v1.0.0)", notes = "删除系统表")
    @ApiImplicitParam(paramType = "path", value = "系统id", name = "systemId", required = true)
    @DeleteMapping(value = "/delete-one/{systemId}")
    public Result<String> delete(@PathVariable(required = false) @PathNotBlank(message = "系统id不能为空") String systemId) {
        service.deleteById(systemId);
        return ok("删除成功");
    }


    @ApiOperation(value = "查询所有系统信息@(v1.0.0)", notes = "查询所有系统信息")
    @PostMapping(value = "/select/list-all")
    public Result<List<CommonSystemDto>> getListAll() {
        return ok(service.selectListAll());
    }


    @ApiOperation(value = "系统表分页查询@(v1.0.0)", notes = "分页查询系统表")
    @PostMapping(value = "/select/page")
    public Result<PageDto<CommonSystemPageDto>> selectPage(@RequestBody CommonSystemPageVo vo) {
        return ok(service.pageByModel(vo));
    }
}