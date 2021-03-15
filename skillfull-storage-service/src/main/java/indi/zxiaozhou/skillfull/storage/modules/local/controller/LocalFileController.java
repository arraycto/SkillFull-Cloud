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

import indi.zxiaozhou.skillfull.corecommon.annotation.AutoLog;
import indi.zxiaozhou.skillfull.corecommon.base.Result;
import indi.zxiaozhou.skillfull.corecommon.constant.UrlPrefix;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotNullSize;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.PathNotBlank;
import indi.zxiaozhou.skillfull.coremvc.base.controller.BaseController;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.storage.modules.local.controller.vo.LocalFilePageVo;
import indi.zxiaozhou.skillfull.storage.modules.local.controller.vo.LocalFileVo;
import indi.zxiaozhou.skillfull.storage.modules.local.service.ILocalFileService;
import indi.zxiaozhou.skillfull.storage.modules.local.service.dto.LocalFileDto;
import indi.zxiaozhou.skillfull.storage.modules.local.service.dto.LocalFilePageDto;
import indi.zxiaozhou.skillfull.storage.modules.local.service.dto.LocalFileUploadBatchDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * 本地文件服务(LocalFile)控制层
 *
 * @author zxiaozhou
 * @date 2020-10-22 23:35:05
 * @since JDK11
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "LocalFile", description = "本地上传")
@RequestMapping(value = UrlPrefix.STORAGE + "/local/file", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocalFileController extends BaseController {
    private final ILocalFileService service;


    @ApiOperation(value = "上传文件到服务器@(v1.0.0)", notes = "文件上传")
    @PostMapping(value = "/upload")
    @ApiImplicitParam(paramType = "query", value = "文件存放目录", name = "fileFolder")
    public Result<LocalFileDto> upload(@RequestParam(value = "file") MultipartFile file,
                                       @RequestParam(required = false, defaultValue = "") String fileFolder) {
        return ok(service.upload(file, fileFolder));
    }


    @ApiOperation(value = "url文件上传到服务器@(v1.0.0)", notes = "文件上传")
    @PostMapping(value = "/upload/url-one")
    public Result<LocalFileDto> uploadUrlOne(@RequestBody @Valid LocalFileVo vo) {
        return ok(service.uploadUrlOne(vo));
    }


    @ApiOperation(value = "url文件批量上传到服务器@(v1.0.0)", notes = "文件上传")
    @PostMapping(value = "/upload/url-batch")
    public Result<LocalFileUploadBatchDto> uploadUrlBatch(@RequestBody @NotNullSize(message = "待上传文件信息不能为空") @Valid List<LocalFileVo> vos) {
        return ok(service.uploadUrlBatch(vos));
    }


    @ApiOperation(value = "本地文件删除@(v1.0.0)", notes = "删除本地文件服务")
    @ApiImplicitParam(paramType = "path", value = "文件id", name = "localFile", required = true)
    @DeleteMapping(value = "/delete-one/{localFileId}")
    public Result<String> delete(@PathVariable(required = false) @PathNotBlank(message = "文件id不能为空") String localFileId) {
        service.deleteById(localFileId);
        return ok("删除成功");
    }


    @ApiOperation(value = "本地文件批量删除@(v1.0.0)", notes = "批量删除本地文件服务")
    @PostMapping(value = "/delete-batch")
    public Result<String> deleteBatch(@RequestBody @NotNullSize(message = "待删除文件id不能为空") List<String> localFileIds) {
        service.deleteBatch(localFileIds);
        return ok("批量删除成功");
    }


    @ApiOperation(value = "通过文件id查询详情@(v1.0.0)", notes = "查询本地文件服务详情")
    @ApiImplicitParam(paramType = "path", value = "文件id", name = "localFile", required = true)
    @GetMapping(value = "/select/one/{localFileId}")
    public Result<LocalFileDto> getById(@PathVariable(required = false) @PathNotBlank(message = "文件id不能为空") String localFileId) {
        return ok(service.getById(localFileId));
    }


    @ApiOperation(value = "本地文件服务分页查询@(v1.0.0)", notes = "分页查询本地文件服务")
    @PostMapping(value = "/select/page")
    @AutoLog(note = "oss文件分页查询", type = AutoLog.QUERY)
    public Result<PageDto<LocalFilePageDto>> selectPage(@RequestBody LocalFilePageVo vo) {
        return ok(service.pageByModel(vo));
    }
}