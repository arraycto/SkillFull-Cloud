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
import indi.zxiaozhou.skillfull.message.modules.manage.controller.vo.ManageSendRecordPageVo;
import indi.zxiaozhou.skillfull.message.modules.manage.controller.vo.ManageSendRecordQueryVo;
import indi.zxiaozhou.skillfull.message.modules.manage.controller.vo.ManageSendRecordVo;
import indi.zxiaozhou.skillfull.message.modules.manage.service.IManageSendRecordService;
import indi.zxiaozhou.skillfull.message.modules.manage.service.dto.ManageSendRecordDto;
import indi.zxiaozhou.skillfull.message.modules.manage.service.dto.ManageSendRecordPageDto;
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
 * 消息发送记录表(ManageSendRecord)控制层
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:57:15
 * @since JDK1.8
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "ManageSendRecord", description = "消息发送记录表Api接口相关", hidden = true)
@RequestMapping(value = UrlPrefix.MESSAGE + "/manageSendRecord", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManageSendRecordController extends BaseController {
    private final IManageSendRecordService service;


    @ApiOperation(value = "消息发送记录表添加@(v1.0.0)", notes = "添加消息发送记录表", hidden = true)
    @PostMapping(value = "/insert")
    public Result<String> insert(@RequestBody @Valid ManageSendRecordVo vo) {
        service.save(vo);
        return ok("添加成功");
    }


    @ApiOperation(value = "通过消息id修改@(v1.0.0)", notes = "修改消息发送记录表", hidden = true)
    @ApiImplicitParam(paramType = "path", value = "消息id", name = "esId", required = true)
    @PutMapping(value = "/update/{esId}")
    public Result<String> update(@PathVariable(required = false) @PathNotBlank(message = "消息id不能为空") String esId,
                                 @RequestBody @Valid ManageSendRecordVo vo) {
        service.updateById(esId, vo);
        return ok("修改成功");
    }


    @ApiOperation(value = "消息发送记录表逻辑删除@(v1.0.0)", notes = "删除消息发送记录表", hidden = true)
    @ApiImplicitParam(paramType = "path", value = "消息id", name = "esId", required = true)
    @DeleteMapping(value = "/delete-one/{esId}")
    public Result<String> delete(@PathVariable(required = false) @PathNotBlank(message = "消息id不能为空") String esId) {
        service.deleteById(esId);
        return ok("删除成功");
    }


    @ApiOperation(value = "消息发送记录表逻辑批量删除@(v1.0.0)", notes = "批量删除消息发送记录表", hidden = true)
    @PostMapping(value = "/delete-batch")
    public Result<String> deleteBatch(@RequestBody @NotNullSize(message = "待删除消息id不能为空") List<String> esIds) {
        service.deleteBatch(esIds);
        return ok("批量删除成功");
    }


    @ApiOperation(value = "通过消息id查询详情@(v1.0.0)", notes = "查询消息发送记录表详情", hidden = true)
    @ApiImplicitParam(paramType = "path", value = "消息id", name = "esId", required = true)
    @GetMapping(value = "/select/one/{esId}")
    public Result<ManageSendRecordDto> getById(@PathVariable(required = false) @PathNotBlank(message = "消息id不能为空") String esId) {
        return ok(service.getById(esId));
    }


    @ApiOperation(value = "通过条件查询消息发送记录表多条数据@(v1.0.0)", notes = "通过条件查询消息发送记录表", hidden = true)
    @PostMapping(value = "/select/list")
    public Result<List<ManageSendRecordDto>> getList(@RequestBody ManageSendRecordQueryVo vo) {
        return ok(service.selectListByModel(vo));
    }


    @ApiOperation(value = "消息发送记录表分页查询@(v1.0.0)", notes = "分页查询消息发送记录表", hidden = true)
    @PostMapping(value = "/select/page")
    public Result<PageDto<ManageSendRecordPageDto>> selectPage(@RequestBody ManageSendRecordPageVo vo) {
        return ok(service.page(vo));
    }
}