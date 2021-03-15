// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.storage.modules.oss.controller;

import indi.zxiaozhou.skillfull.corecommon.annotation.AutoLog;
import indi.zxiaozhou.skillfull.corecommon.base.Result;
import indi.zxiaozhou.skillfull.corecommon.constant.UrlPrefix;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotNullSize;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.PathNotBlank;
import indi.zxiaozhou.skillfull.coremvc.base.controller.BaseController;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.storage.modules.oss.controller.vo.OssFilePageVo;
import indi.zxiaozhou.skillfull.storage.modules.oss.controller.vo.OssFileVo;
import indi.zxiaozhou.skillfull.storage.modules.oss.service.IOssFileService;
import indi.zxiaozhou.skillfull.storage.modules.oss.service.dto.OssFileDto;
import indi.zxiaozhou.skillfull.storage.modules.oss.service.dto.OssFilePageDto;
import indi.zxiaozhou.skillfull.storage.modules.oss.service.dto.OssFileUploadBatchDto;
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
 * oss文件(OssFile)控制层
 *
 * @author zxiaozhou
 * @date 2020-10-22 23:36:20
 * @since JDK11
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "OssFile", description = "oss上传")
@RequestMapping(value = UrlPrefix.STORAGE + "/oss/file", produces = MediaType.APPLICATION_JSON_VALUE)
public class OssFileController extends BaseController {
    private final IOssFileService service;


    @ApiOperation(value = "文件上传到oss@(v1.0.0)", notes = "上传文件到oss")
    @PostMapping(value = "/upload")
    @ApiImplicitParam(paramType = "query", value = "文件存放目录", name = "fileFolder")
    public Result<OssFileDto> upload(@RequestParam(value = "file") MultipartFile file,
                                     @RequestParam(required = false, defaultValue = "") String fileFolder) {
        return ok(service.upload(file, fileFolder));
    }


    @ApiOperation(value = "url文件上传到服务器@(v1.0.0)", notes = "文件上传")
    @PostMapping(value = "/upload/url-one")
    public Result<OssFileDto> uploadUrlOne(@RequestBody @Valid OssFileVo vo) {
        return ok(service.uploadUrlOne(vo));
    }


    @ApiOperation(value = "url文件批量上传到服务器@(v1.0.0)", notes = "文件上传")
    @PostMapping(value = "/upload/url-batch")
    public Result<OssFileUploadBatchDto> uploadUrlBatch(@RequestBody @NotNullSize(message = "待上传文件信息不能为空") @Valid List<OssFileVo> vos) {
        return ok(service.uploadUrlBatch(vos));
    }


    @ApiOperation(value = "通过文件id获取oss访问地址@(v1.0.0)", notes = "通过文件id获取oss访问地址")
    @ApiImplicitParam(paramType = "path", value = "文件id", name = "ossFileId", required = true)
    @GetMapping(value = "/get/url/{ossFileId}")
    public Result<String> getAccessUrlById(@PathVariable(required = false) @PathNotBlank(message = "文件id不能为空") String ossFileId) {
        return ok(service.getAccessUrlById(ossFileId), "获取访问url成功");
    }


    @ApiOperation(value = "oss文件逻辑删除@(v1.0.0)", notes = "删除oss文件")
    @ApiImplicitParam(paramType = "path", value = "文件id", name = "ossFileId", required = true)
    @DeleteMapping(value = "/delete-one/{ossFileId}")
    public Result<String> delete(@PathVariable(required = false) @PathNotBlank(message = "文件id不能为空") String ossFileId) {
        service.deleteById(ossFileId);
        return ok("删除成功");
    }


    @ApiOperation(value = "oss文件逻辑批量删除@(v1.0.0)", notes = "批量删除oss文件")
    @PostMapping(value = "/delete-batch")
    public Result<String> deleteBatch(@RequestBody @NotNullSize(message = "待删除文件id不能为空") List<String> ossFileIds) {
        service.deleteBatch(ossFileIds);
        return ok("批量删除成功");
    }


    @ApiOperation(value = "通过文件id查询详情@(v1.0.0)", notes = "查询oss文件详情")
    @ApiImplicitParam(paramType = "path", value = "文件id", name = "ossFileId", required = true)
    @GetMapping(value = "/select/one/{ossFileId}")
    public Result<OssFileDto> getById(@PathVariable(required = false) @PathNotBlank(message = "文件id不能为空") String ossFileId) {
        return ok(service.getById(ossFileId, true));
    }


    @ApiOperation(value = "oss文件分页查询@(v1.0.0)", notes = "分页查询oss文件")
    @PostMapping(value = "/select/page")
    @AutoLog(note = "oss文件分页查询", type = AutoLog.QUERY)
    public Result<PageDto<OssFilePageDto>> selectPage(@RequestBody OssFilePageVo vo) {
        return ok(service.pageByModel(vo));
    }
}