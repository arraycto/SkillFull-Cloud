// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.logging.modules.collect.controller;

import indi.zxiaozhou.skillfull.corecommon.base.Result;
import indi.zxiaozhou.skillfull.corecommon.constant.UrlPrefix;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotNullSize;
import indi.zxiaozhou.skillfull.coremvc.base.controller.BaseController;
import indi.zxiaozhou.skillfull.logging.modules.collect.service.ICollectService;
import indi.zxiaozhou.skillfull.logging.modules.manage.controller.vo.DataVo;
import indi.zxiaozhou.skillfull.logging.modules.manage.controller.vo.OperateVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 数据收集控制层
 *
 * @author zxiaozhou
 * @date 2021-01-12 14:38:25
 * @since JDK11
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "Collect", description = "数据收集")
@RequestMapping(value = UrlPrefix.LOGGING + "/collect", produces = MediaType.APPLICATION_JSON_VALUE)
public class CollectController extends BaseController {
    private final ICollectService service;

    @ApiOperation(value = "数据日志收集@(v1.0.0)", notes = "数据日志收集")
    @PostMapping(value = "/data-batch")
    public Result<String> dataCollectBatch(@RequestBody @NotNullSize(message = "数据日志不能为空") @Valid List<DataVo> vos) {
        service.dataCollectBatch(vos);
        return ok("数据日志入库成功");
    }


    @ApiOperation(value = "操作日志收集@(v1.0.0)", notes = "操作日志收集")
    @PostMapping(value = "/operate-batch")
    public Result<String> operateCollectBatch(@RequestBody @NotNullSize(message = "操作日志不能为空") @Valid List<OperateVo> vos) {
        service.operateCollectBatch(vos);
        return ok("操作日志入库成功");
    }
}