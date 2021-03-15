// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.modules.message.controller;

import indi.zxiaozhou.skillfull.corecommon.base.Result;
import indi.zxiaozhou.skillfull.corecommon.constant.UrlPrefix;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotNullSize;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.PathNotBlank;
import indi.zxiaozhou.skillfull.corewebflux.utils.ReactiveRequestContextHolder;
import indi.zxiaozhou.skillfull.corewebflux.utils.ServletUtils;
import indi.zxiaozhou.skillfull.message.core.base.controller.BaseController;
import indi.zxiaozhou.skillfull.message.core.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.message.modules.message.controller.vo.MessageUserRecordPageVo;
import indi.zxiaozhou.skillfull.message.modules.message.controller.vo.MessageUserRecordVo;
import indi.zxiaozhou.skillfull.message.modules.message.service.IMessageUserRecordService;
import indi.zxiaozhou.skillfull.message.modules.message.service.dto.MessageUserNoReadRecordDto;
import indi.zxiaozhou.skillfull.message.modules.message.service.dto.MessageUserRecordDto;
import indi.zxiaozhou.skillfull.message.modules.message.service.dto.MessageUserRecordPageDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户消息记录(MessageUserRecord)控制层
 *
 * @author zxiaozhou
 * @date 2021-01-26 16:47:59
 * @since JDK11
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "MessageUserRecord", description = "用户消息记录")
@RequestMapping(value = UrlPrefix.MESSAGE + "/message/record", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageUserRecordController extends BaseController {
    private final IMessageUserRecordService service;


    @ApiOperation(value = "用户消息记录添加@(v1.0.0)", notes = "添加用户消息记录", hidden = true)
    @PostMapping(value = "/insert")
    public Mono<Result<String>> insert(@RequestBody @Valid MessageUserRecordVo vo) {
        return ReactiveRequestContextHolder.getRequest().map(v -> {
            ServletUtils.setServerHttpRequest(v);
            service.save(vo);
            return "添加成功";
        }).map(BaseController::ok);
    }


    @ApiOperation(value = "通过消息记录id修改@(v1.0.0)", notes = "修改用户消息记录", hidden = true)
    @ApiImplicitParam(paramType = "path", value = "消息记录id", name = "msgId", required = true)
    @PutMapping(value = "/update/{msgId}")
    public Mono<Result<String>> update(@PathVariable(required = false) @PathNotBlank(message = "消息记录id不能为空") String msgId,
                                       @RequestBody @Valid MessageUserRecordVo vo) {
        return ReactiveRequestContextHolder.getRequest().map(v -> {
            ServletUtils.setServerHttpRequest(v);
            service.updateById(msgId, vo);
            return "修改成功";
        }).map(BaseController::ok);
    }


    @ApiOperation(value = "用户消息记录逻辑删除@(v1.0.0)", notes = "删除用户消息记录", hidden = true)
    @ApiImplicitParam(paramType = "path", value = "消息记录id", name = "msgId", required = true)
    @DeleteMapping(value = "/delete-one/{msgId}")
    public Mono<Result<String>> delete(@PathVariable(required = false) @PathNotBlank(message = "消息记录id不能为空") String msgId) {
        return ReactiveRequestContextHolder.getRequest().map(v -> {
            ServletUtils.setServerHttpRequest(v);
            service.deleteById(msgId);
            return "删除成功";
        }).map(BaseController::ok);
    }


    @ApiOperation(value = "用户消息记录逻辑批量删除@(v1.0.0)", notes = "批量删除用户消息记录", hidden = true)
    @PostMapping(value = "/delete-batch")
    public Mono<Result<String>> deleteBatch(@RequestBody @NotNullSize(message = "待删除消息记录id不能为空") List<String> msgIds) {
        return ReactiveRequestContextHolder.getRequest().map(v -> {
            ServletUtils.setServerHttpRequest(v);
            service.deleteBatch(msgIds);
            return "批量删除成功";
        }).map(BaseController::ok);
    }


    @ApiOperation(value = "通过消息记录id查询详情@(v1.0.0)", notes = "查询用户消息记录详情", hidden = true)
    @ApiImplicitParam(paramType = "path", value = "消息记录id", name = "msgId", required = true)
    @GetMapping(value = "/select/one/{msgId}")
    public Mono<Result<MessageUserRecordDto>> getById(@PathVariable(required = false) @PathNotBlank(message = "消息记录id不能为空") String msgId) {
        return ReactiveRequestContextHolder.getRequest().map(v -> {
            ServletUtils.setServerHttpRequest(v);
            return service.getById(msgId);
        }).map(BaseController::ok);
    }


    @ApiOperation(value = "获取未读消息@(v1.0.0)", notes = "获取未读消息")
    @GetMapping(value = "/select/no-read")
    public Mono<Result<MessageUserNoReadRecordDto>> getListNoRead() {
        return ReactiveRequestContextHolder.getRequest().map(v -> {
            ServletUtils.setServerHttpRequest(v);
            return service.getListNoRead();
        }).map(BaseController::ok);
    }


    @ApiOperation(value = "清空消息@(v1.0.0)", notes = "清空消息")
    @GetMapping(value = "/clear/msg/{type}")
    public Mono<Result<String>> clearMsg(@PathVariable(required = false) Integer type) {
        return ReactiveRequestContextHolder.getRequest().map(v -> {
            ServletUtils.setServerHttpRequest(v);
            service.clearMsg(type);
            return "清空成功";
        }).map(BaseController::ok);
    }


    @ApiOperation(value = "用户消息记录分页查询@(v1.0.0)", notes = "分页查询用户消息记录")
    @PostMapping(value = "/select/page")
    public Mono<Result<PageDto<MessageUserRecordPageDto>>> selectPage(@RequestBody MessageUserRecordPageVo vo) {
        return ReactiveRequestContextHolder.getRequest().map(v -> {
            ServletUtils.setServerHttpRequest(v);
            return service.pageByModel(vo);
        }).map(BaseController::ok);
    }
}