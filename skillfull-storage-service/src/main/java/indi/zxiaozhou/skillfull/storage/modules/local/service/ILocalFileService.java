// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.storage.modules.local.service;

import indi.zxiaozhou.skillfull.coremvc.base.service.BaseService;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.storage.modules.local.controller.vo.LocalFilePageVo;
import indi.zxiaozhou.skillfull.storage.modules.local.controller.vo.LocalFileVo;
import indi.zxiaozhou.skillfull.storage.modules.local.entity.LocalFileEntity;
import indi.zxiaozhou.skillfull.storage.modules.local.service.dto.LocalFileDto;
import indi.zxiaozhou.skillfull.storage.modules.local.service.dto.LocalFilePageDto;
import indi.zxiaozhou.skillfull.storage.modules.local.service.dto.LocalFileUploadBatchDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 本地文件服务(LocalFile)业务层接口
 *
 * @author zxiaozhou
 * @date 2020-10-23 14:37:33
 * @since JDK11
 */
public interface ILocalFileService extends BaseService<LocalFileEntity> {
    /**
     * 文件上传
     *
     * @param file       ${@link MultipartFile} 待上传文件
     * @param fileFolder ${@link String} 存储文件文件夹名称
     * @return LocalFileDto ${@link LocalFileDto} 上传结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-10-22 23:36:35
     */
    LocalFileDto upload(MultipartFile file, String fileFolder) throws RuntimeException;

    /**
     * 文件上传
     *
     * @param vo ${@link LocalFileVo} 待上传文件信息
     * @return LocalFileDto ${@link LocalFileDto} 上传结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-10-22 23:36:35
     */
    LocalFileDto uploadUrlOne(LocalFileVo vo) throws RuntimeException;


    /**
     * 文件上传
     *
     * @param vos ${@link List<LocalFileVo>} 待上传文件信息
     * @return OssFileUploadBatchDto ${@link LocalFileUploadBatchDto} 上传结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-10-22 23:36:35
     */
    LocalFileUploadBatchDto uploadUrlBatch(List<LocalFileVo> vos) throws RuntimeException;


    /**
     * 文件下载
     *
     * @param localFileId ${@link String} 文件id
     * @param response    ${@link HttpServletResponse}
     * @author zxiaozhou
     * @date 2020-10-23 14:33
     */
    void download(String localFileId, HttpServletResponse response);


    /**
     * 显示文件
     *
     * @param localFileId ${@link String} 文件id
     * @param response    ${@link HttpServletResponse}
     * @author zxiaozhou
     * @date 2020-10-23 14:33
     */
    void show(String localFileId, HttpServletResponse response);


    /**
     * 分页查询
     *
     * @param vo ${@link LocalFilePageVo} 本地文件服务分页查询Vo
     * @return PageDto<LocalFilePageDto> ${@link PageDto<LocalFilePageDto>} 分页查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-10-23 14:37:33
     */
    PageDto<LocalFilePageDto> pageByModel(LocalFilePageVo vo) throws RuntimeException;


    /**
     * 通过id查询详情
     *
     * @param localFileId ${@link String} 文件id
     * @return LocalFileDto ${@link LocalFileDto} 查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-10-23 14:37:33
     */
    LocalFileDto getById(String localFileId) throws RuntimeException;


    /**
     * 通过localFileId删除
     *
     * @param localFileId ${@link String} 文件id
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-08-28 12:20
     */
    void deleteById(String localFileId) throws RuntimeException;


    /**
     * 文件批量删除
     *
     * @param localFileIds ${@link List<String>}
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-10-24 22:59
     */
    void deleteBatch(List<String> localFileIds) throws RuntimeException;
}