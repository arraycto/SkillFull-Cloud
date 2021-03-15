// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.gateway.modules.manage.controller;

import indi.zxiaozhou.skillfull.corecommon.base.Result;
import indi.zxiaozhou.skillfull.corecommon.constant.model.ConstantDictModel;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.PathNotBlank;
import indi.zxiaozhou.skillfull.corewebflux.base.controller.BaseController;
import indi.zxiaozhou.skillfull.corewebflux.utils.ReactiveRequestContextHolder;
import indi.zxiaozhou.skillfull.gateway.modules.manage.service.IToolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 工具类控制层
 *
 * @author zxiaozhou
 * @date 2020-09-28 10:05
 * @since JDK11
 */
@Api(tags = "Tools", description = "工具相关")
@RestController
@RequestMapping(value = "${server.context-path}/tools", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class ToolsController extends BaseController {
    private final IToolService service;

    
    @ApiOperation(value = "获取网关常量字典@(v1.0.0)", notes = "获取网关常量字典")
    @GetMapping("/select/constant/dict/{type}")
    @ApiImplicitParam(paramType = "path", value = "字典类型: PredicateType--断言,FilterType--过滤器,LbType--负载均衡类型,ProtocolType--传输协议类型", name = "type", required = true)
    Mono<Result<List<ConstantDictModel>>> getConstantDict(@PathVariable @PathNotBlank(message = "常量字典类型不能为空") String type) {
        return ReactiveRequestContextHolder.getRequest().map(v -> service.getConstantDict(type)).map(BaseController::ok);
    }
}
