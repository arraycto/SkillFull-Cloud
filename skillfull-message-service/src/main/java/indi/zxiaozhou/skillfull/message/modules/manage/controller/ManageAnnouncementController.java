// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.modules.manage.controller;

import indi.zxiaozhou.skillfull.corecommon.base.Result;
import indi.zxiaozhou.skillfull.corecommon.constant.UrlPrefix;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotNullSize;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.PathNotBlank;
import indi.zxiaozhou.skillfull.message.core.base.controller.BaseController;
import indi.zxiaozhou.skillfull.message.core.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.message.modules.manage.controller.vo.ManageAnnouncementPageVo;
import indi.zxiaozhou.skillfull.message.modules.manage.controller.vo.ManageAnnouncementQueryVo;
import indi.zxiaozhou.skillfull.message.modules.manage.controller.vo.ManageAnnouncementVo;
import indi.zxiaozhou.skillfull.message.modules.manage.service.IManageAnnouncementService;
import indi.zxiaozhou.skillfull.message.modules.manage.service.dto.ManageAnnouncementDto;
import indi.zxiaozhou.skillfull.message.modules.manage.service.dto.ManageAnnouncementPageDto;
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
 * 系统通告管理(ManageAnnouncement)控制层
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:56:51
 * @since JDK1.8
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "ManageAnnouncement", description = "系统通告管理Api接口相关", hidden = true)
@RequestMapping(value = UrlPrefix.MESSAGE + "/manageAnnouncement", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManageAnnouncementController extends BaseController {
    private final IManageAnnouncementService service;


    @ApiOperation(value = "系统通告管理添加@(v1.0.0)", notes = "添加系统通告管理", hidden = true)
    @PostMapping(value = "/insert")
    public Result<String> insert(@RequestBody @Valid ManageAnnouncementVo vo) {
        service.save(vo);
        return ok("添加成功");
    }


    @ApiOperation(value = "通过公告id修改@(v1.0.0)", notes = "修改系统通告管理", hidden = true)
    @ApiImplicitParam(paramType = "path", value = "公告id", name = "anntId", required = true)
    @PutMapping(value = "/update/{anntId}")
    public Result<String> update(@PathVariable(required = false) @PathNotBlank(message = "公告id不能为空") String anntId,
                                 @RequestBody @Valid ManageAnnouncementVo vo) {
        service.updateById(anntId, vo);
        return ok("修改成功");
    }


    @ApiOperation(value = "系统通告管理逻辑删除@(v1.0.0)", notes = "删除系统通告管理", hidden = true)
    @ApiImplicitParam(paramType = "path", value = "公告id", name = "anntId", required = true)
    @DeleteMapping(value = "/delete-one/{anntId}")
    public Result<String> delete(@PathVariable(required = false) @PathNotBlank(message = "公告id不能为空") String anntId) {
        service.deleteById(anntId);
        return ok("删除成功");
    }


    @ApiOperation(value = "系统通告管理逻辑批量删除@(v1.0.0)", notes = "批量删除系统通告管理", hidden = true)
    @PostMapping(value = "/delete-batch")
    public Result<String> deleteBatch(@RequestBody @NotNullSize(message = "待删除公告id不能为空") List<String> anntIds) {
        service.deleteBatch(anntIds);
        return ok("批量删除成功");
    }


    @ApiOperation(value = "通过公告id查询详情@(v1.0.0)", notes = "查询系统通告管理详情", hidden = true)
    @ApiImplicitParam(paramType = "path", value = "公告id", name = "anntId", required = true)
    @GetMapping(value = "/select/one/{anntId}")
    public Result<ManageAnnouncementDto> getById(@PathVariable(required = false) @PathNotBlank(message = "公告id不能为空") String anntId) {
        return ok(service.getById(anntId));
    }


    @ApiOperation(value = "通过条件查询系统通告管理多条数据@(v1.0.0)", notes = "通过条件查询系统通告管理", hidden = true)
    @PostMapping(value = "/select/list")
    public Result<List<ManageAnnouncementDto>> getList(@RequestBody ManageAnnouncementQueryVo vo) {
        return ok(service.selectListByModel(vo));
    }


    @ApiOperation(value = "系统通告管理分页查询@(v1.0.0)", notes = "分页查询系统通告管理", hidden = true)
    @PostMapping(value = "/select/page")
    public Result<PageDto<ManageAnnouncementPageDto>> selectPage(@RequestBody ManageAnnouncementPageVo vo) {
        return ok(service.page(vo));
    }
}