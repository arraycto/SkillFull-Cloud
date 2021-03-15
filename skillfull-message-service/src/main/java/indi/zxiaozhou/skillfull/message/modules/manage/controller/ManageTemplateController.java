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
import indi.zxiaozhou.skillfull.message.modules.manage.controller.vo.ManageTemplatePageVo;
import indi.zxiaozhou.skillfull.message.modules.manage.controller.vo.ManageTemplateQueryVo;
import indi.zxiaozhou.skillfull.message.modules.manage.controller.vo.ManageTemplateVo;
import indi.zxiaozhou.skillfull.message.modules.manage.service.IManageTemplateService;
import indi.zxiaozhou.skillfull.message.modules.manage.service.dto.ManageTemplateDto;
import indi.zxiaozhou.skillfull.message.modules.manage.service.dto.ManageTemplatePageDto;
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
 * 消息模板(ManageTemplate)控制层
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:57:45
 * @since JDK1.8
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "ManageTemplate", description = "消息模板Api接口相关", hidden = true)
@RequestMapping(value = UrlPrefix.MESSAGE + "/manageTemplate", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManageTemplateController extends BaseController {
    private final IManageTemplateService service;


    @ApiOperation(value = "消息模板添加@(v1.0.0)", notes = "添加消息模板", hidden = true)
    @PostMapping(value = "/insert")
    public Result<String> insert(@RequestBody @Valid ManageTemplateVo vo) {
        service.save(vo);
        return ok("添加成功");
    }


    @ApiOperation(value = "通过模板id修改@(v1.0.0)", notes = "修改消息模板", hidden = true)
    @ApiImplicitParam(paramType = "path", value = "模板id", name = "templateId", required = true)
    @PutMapping(value = "/update/{templateId}")
    public Result<String> update(@PathVariable(required = false) @PathNotBlank(message = "模板id不能为空") String templateId,
                                 @RequestBody @Valid ManageTemplateVo vo) {
        service.updateById(templateId, vo);
        return ok("修改成功");
    }


    @ApiOperation(value = "消息模板逻辑删除@(v1.0.0)", notes = "删除消息模板", hidden = true)
    @ApiImplicitParam(paramType = "path", value = "模板id", name = "templateId", required = true)
    @DeleteMapping(value = "/delete-one/{templateId}")
    public Result<String> delete(@PathVariable(required = false) @PathNotBlank(message = "模板id不能为空") String templateId) {
        service.deleteById(templateId);
        return ok("删除成功");
    }


    @ApiOperation(value = "消息模板逻辑批量删除@(v1.0.0)", notes = "批量删除消息模板", hidden = true)
    @PostMapping(value = "/delete-batch")
    public Result<String> deleteBatch(@RequestBody @NotNullSize(message = "待删除模板id不能为空") List<String> templateIds) {
        service.deleteBatch(templateIds);
        return ok("批量删除成功");
    }


    @ApiOperation(value = "通过模板id查询详情@(v1.0.0)", notes = "查询消息模板详情", hidden = true)
    @ApiImplicitParam(paramType = "path", value = "模板id", name = "templateId", required = true)
    @GetMapping(value = "/select/one/{templateId}")
    public Result<ManageTemplateDto> getById(@PathVariable(required = false) @PathNotBlank(message = "模板id不能为空") String templateId) {
        return ok(service.getById(templateId));
    }


    @ApiOperation(value = "通过条件查询消息模板多条数据@(v1.0.0)", notes = "通过条件查询消息模板", hidden = true)
    @PostMapping(value = "/select/list")
    public Result<List<ManageTemplateDto>> getList(@RequestBody ManageTemplateQueryVo vo) {
        return ok(service.selectListByModel(vo));
    }


    @ApiOperation(value = "消息模板分页查询@(v1.0.0)", notes = "分页查询消息模板", hidden = true)
    @PostMapping(value = "/select/page")
    public Result<PageDto<ManageTemplatePageDto>> selectPage(@RequestBody ManageTemplatePageVo vo) {
        return ok(service.page(vo));
    }
}