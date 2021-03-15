// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.storage.modules.local.controller;

import indi.zxiaozhou.skillfull.corecommon.constant.UrlPrefix;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.PathNotBlank;
import indi.zxiaozhou.skillfull.storage.modules.local.service.ILocalFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * 本地文件下载显示
 *
 * @author zxiaozhou
 * @date 2020-10-23 17:58
 * @since JDK11
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@Api(tags = "LocalFile", description = "本地上传")
@Controller
@RequestMapping(value = UrlPrefix.STORAGE + "/local")
public class LocalFileDownController {
    private final ILocalFileService service;


    @ApiOperation(value = "下载文件@(v1.0.0)", notes = "下载文件")
    @ApiImplicitParam(paramType = "path", value = "文件id", name = "localFileId", required = true)
    @GetMapping(value = "/download/{localFileId}")
    public void download(@PathVariable(required = false) @PathNotBlank(message = "文件id不能为空") String localFileId,
                         HttpServletResponse response) {
        service.download(localFileId, response);
    }


    @ApiOperation(value = "显示文件@(v1.0.0)", notes = "显示文件")
    @ApiImplicitParam(paramType = "path", value = "文件id", name = "localFileId", required = true)
    @GetMapping(value = "/file/{localFileId}")
    public void show(@PathVariable(required = false) @PathNotBlank(message = "文件id不能为空") String localFileId,
                     HttpServletResponse response) {
        service.show(localFileId, response);
    }

}
