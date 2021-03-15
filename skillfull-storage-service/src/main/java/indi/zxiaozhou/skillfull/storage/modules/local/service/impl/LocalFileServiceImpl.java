// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.storage.modules.local.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import indi.zxiaozhou.skillfull.corecommon.utils.CoreCommonDateUtils;
import indi.zxiaozhou.skillfull.corecommon.utils.CoreCommonUtils;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.storage.core.config.properties.LocalFileProperty;
import indi.zxiaozhou.skillfull.storage.modules.local.controller.vo.LocalFilePageVo;
import indi.zxiaozhou.skillfull.storage.modules.local.controller.vo.LocalFileVo;
import indi.zxiaozhou.skillfull.storage.modules.local.entity.LocalFileEntity;
import indi.zxiaozhou.skillfull.storage.modules.local.mapper.LocalFileMapper;
import indi.zxiaozhou.skillfull.storage.modules.local.service.ILocalFileService;
import indi.zxiaozhou.skillfull.storage.modules.local.service.dto.LocalFileDto;
import indi.zxiaozhou.skillfull.storage.modules.local.service.dto.LocalFilePageDto;
import indi.zxiaozhou.skillfull.storage.modules.local.service.dto.LocalFileUploadBatchDto;
import indi.zxiaozhou.skillfull.storage.modules.local.service.mapstruct.dto.LocalFileDtoMap;
import indi.zxiaozhou.skillfull.storage.modules.local.service.mapstruct.dto.LocalFileUploadSuccessMap;
import indi.zxiaozhou.skillfull.storage.modules.local.service.mapstruct.vo.LocalFileVoUploadFailMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static indi.zxiaozhou.skillfull.corecommon.constant.CommonConstant.POINT;
import static indi.zxiaozhou.skillfull.corecommon.constant.CommonConstant.SLASH;

/**
 * 本地文件服务(LocalFile)业务层实现
 *
 * @author zxiaozhou
 * @date 2020-10-23 14:37:34
 * @since JDK11
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class, Error.class})
public class LocalFileServiceImpl extends ServiceImpl<LocalFileMapper, LocalFileEntity> implements ILocalFileService {
    private final LocalFileDtoMap dtoMap;
    private final LocalFileProperty fileProperty;
    private final LocalFileUploadSuccessMap uploadSuccessMap;
    private final LocalFileMapper mapper;
    private final Snowflake snowflake;
    private final LocalFileVoUploadFailMap uploadFailMap;
    private final DataSourceTransactionManager dataSourceTransactionManager;
    private final TransactionDefinition transactionDefinition;


    @Override
    public LocalFileDto upload(MultipartFile file, String fileFolder) throws RuntimeException {
        // 获取文件基本信息并补全存放文件信息
        String fileOriginalFullName = file.getOriginalFilename();
        long fileSizeDetail = file.getSize();
        String fileMd5;
        try {
            fileMd5 = CoreCommonUtils.getFileMd5Hex(file.getInputStream());
        } catch (IOException e) {
            log.error("------------LocalFileServiceImpl------获取文件md5值失败------>upload:{}", e.getMessage());
            throw new ResponseException(Status.ERROR, "获取文件md5值失败:" + e.getMessage());
        }
        String fileSize = CoreCommonUtils.getFormatFileSize(fileSizeDetail);
        String fileOriginalName = "";
        String fileType = "";
        if (StringUtils.isNotBlank(fileOriginalFullName)) {
            fileOriginalName = fileOriginalFullName.substring(0, fileOriginalFullName.lastIndexOf("."));
            fileType = fileOriginalFullName.substring(fileOriginalFullName.lastIndexOf("."));
        }
        // 处理文件保存文件夹
        String fileDiskRelativePath = CoreCommonDateUtils.getNowStrDate(CoreCommonDateUtils.YYYYMMDD);
        if (StringUtils.isNotBlank(fileFolder)) {
            if (fileFolder.startsWith(SLASH)) {
                fileFolder = fileFolder.replaceFirst(SLASH, "");
            }
            if (fileFolder.endsWith(SLASH)) {
                fileFolder = fileFolder.substring(0, fileFolder.length() - 1);
            }
            fileDiskRelativePath += fileFolder;
        }

        // 构建入库文件信息
        LocalFileEntity entity = new LocalFileEntity();
        entity.setFileOriginalFullName(fileOriginalFullName);
        entity.setFileOriginalName(fileOriginalName);
        entity.setFileSize(fileSize);
        entity.setFileType(fileType);
        entity.setFileMd5(fileMd5);
        entity.setFileSizeDetail(fileSizeDetail);
        entity.setFileDirPrefix(fileFolder);
        entity.setFileFullName(snowflake.nextIdStr() + fileType);
        entity.setFileDiskRelativePath(fileDiskRelativePath + SLASH + entity.getFileFullName());
        entity.setFileMappingPath(fileProperty.getVirtualMapping() + SLASH + entity.getFileDiskRelativePath());

        // 查看文件是否已经存储(如果存在则使用已经存在的文件信息)
        LambdaQueryWrapper<LocalFileEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(LocalFileEntity::getFileMd5, entity.getFileMd5());
        List<LocalFileEntity> list = this.list(lambdaQueryWrapper);
        boolean isHave = false;
        if (CollectionUtil.isNotEmpty(list)) {
            LocalFileEntity fileEntity = list.get(0);
            entity.setFileDiskRelativePath(fileEntity.getFileDiskRelativePath());
            entity.setFileDirPrefix(fileEntity.getFileDirPrefix());
            entity.setFileFullName(fileEntity.getFileFullName());
            entity.setFileMappingPath(fileEntity.getFileMappingPath());
            isHave = true;
        }

        // 存放文件
        if (!isHave) {
            // 创建文件存放文件夹
            String saveFileFolder = fileProperty.getUploadFolder() + SLASH + fileDiskRelativePath;
            File localFileFolder = new File(saveFileFolder);
            if (!localFileFolder.exists() || !localFileFolder.isDirectory()) {
                boolean mkdirs = localFileFolder.mkdirs();
                if (!mkdirs) {
                    log.error("------------LocalFileServiceImpl------------>upload:{}", "创建文件存放路径失败");
                    throw new ResponseException(Status.ERROR, "创建文件存放路径失败");
                }
            }
            // 创建存放文件
            String saveFilePath = saveFileFolder + SLASH + entity.getFileFullName();
            File localFile = new File(saveFilePath);
            try {
                file.transferTo(localFile);
            } catch (IOException e) {
                log.error("------------LocalFileServiceImpl------存放文件到本地失败------>upload:{}", e.getMessage());
                throw new ResponseException(Status.ERROR, "存放文件到本地失败:" + e.getMessage());
            }
        }

        // 文件信息存入数据库并返回文件信息
        boolean result = super.save(entity);
        if (!result) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "保存数据失败");
        }
        return dtoMap.toDto(entity);
    }


    @Override
    public LocalFileDto uploadUrlOne(LocalFileVo vo) throws RuntimeException {
        // 创建链接并判断文件是否能下载
        HttpURLConnection con;
        try {
            URL url = new URL(vo.getUrl());
            con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(60 * 1000);
            // 创建浏览器模拟信息,避免三方网站拒绝处理导致403
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116");
            int responseCode = con.getResponseCode();
            if (responseCode != HttpStatus.OK.value()) {
                log.error("------------LocalFileServiceImpl------------>uploadUrlOne:{}", con.getResponseMessage());
                throw new ResponseException(Status.ERROR, "下载文件失败(" + vo.getUrl() + "):" + con.getResponseMessage());
            }
        } catch (IOException e) {
            log.error("------------LocalFileServiceImpl------下载文件失败------>uploadUrlOne:{}", "(" + vo.getUrl() + ")" + e.getMessage());
            throw new ResponseException(Status.ERROR, "下载文件失败(" + vo.getUrl() + "):" + e.getMessage());
        }

        // 获取文件信息(首先从传入参数中获取文件名,不存在则从地址中解析，再从请求头解析)
        String fileOriginalName = "";
        String fileType = "";
        if (StringUtils.isNotBlank(vo.getFileOriginalFullName())) {
            fileOriginalName = vo.getFileOriginalFullName();
            if (fileOriginalName.contains(POINT)) {
                fileType = fileOriginalName.split("\\.")[1];
            }
        } else if (vo.getUrl().contains(POINT)) {
            String[] split = vo.getUrl().split(SLASH);
            fileOriginalName = split[split.length - 1];
            fileType = POINT + fileOriginalName.split("\\.")[1];
        } else {
            String headerField = con.getHeaderField("Content-disposition");
            if (StringUtils.isNotBlank(headerField)) {
                fileOriginalName = headerField.replace("attachment;fileOriginalName=", "");
                try {
                    fileOriginalName = URLDecoder.decode(fileOriginalName, String.valueOf(StandardCharsets.UTF_8));
                    if (fileOriginalName.contains(POINT)) {
                        fileType = POINT + fileOriginalName.split("\\.")[1];
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    log.error("------------LocalFileServiceImpl------请求头Content-disposition文件信息url解码失败------>uploadUrlOne:{}", e.getMessage());
                    throw new ResponseException(Status.DATABASE_BASE_ERROR, "请求头Content-disposition文件信息url解码失败:" + e.getMessage());
                }
            }
        }

        // 处理文件存放文件夹以及存放文件
        String fileDiskRelativePath = CoreCommonDateUtils.getNowStrDate(CoreCommonDateUtils.YYYYMMDD);
        String fileFolder = vo.getFileFolder();
        if (StringUtils.isNotBlank(fileFolder)) {
            if (fileFolder.endsWith(SLASH)) {
                fileFolder = fileFolder.substring(0, fileFolder.length() - 1);
            }
            if (fileFolder.startsWith(SLASH)) {
                fileFolder = fileFolder.replaceFirst(SLASH, "");
            }
            fileDiskRelativePath += fileFolder;
        }

        // 计算文件md5以及文件大小
        String fileMd5;
        long fileSizeDetail;
        String fileSize;
        byte[] bytes;
        try {
            bytes = con.getInputStream().readAllBytes();
            fileMd5 = CoreCommonUtils.getFileMd5Hex(new ByteArrayInputStream(bytes));
            fileSizeDetail = bytes.length;
            fileSize = CoreCommonUtils.getFormatFileSize(fileSizeDetail);
        } catch (IOException e) {
            log.error("------------LocalFileServiceImpl------获取文件md5值失败------>upload:{}", e.getMessage());
            throw new ResponseException(Status.ERROR, "获取文件md5值失败:" + e.getMessage());
        }

        // 构建文件信息
        LocalFileEntity entity = new LocalFileEntity();
        entity.setFileOriginalFullName(fileOriginalName);
        entity.setFileType(fileType);
        entity.setFileOriginalName(snowflake.nextIdStr());
        entity.setFileFullName(entity.getFileOriginalName() + fileType);
        entity.setFileDirPrefix(fileFolder);
        entity.setFileDiskRelativePath(fileDiskRelativePath + SLASH + entity.getFileFullName());
        entity.setFileMappingPath(fileProperty.getVirtualMapping() + SLASH + entity.getFileDiskRelativePath());
        entity.setFileMd5(fileMd5);
        entity.setFileSizeDetail(fileSizeDetail);
        entity.setFileSize(fileSize);

        // 查看文件是否已经存储(如果存在则使用已经存在的文件信息)
        LambdaQueryWrapper<LocalFileEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(LocalFileEntity::getFileMd5, entity.getFileMd5());
        List<LocalFileEntity> list = this.list(lambdaQueryWrapper);
        boolean isHave = false;
        if (CollectionUtil.isNotEmpty(list)) {
            LocalFileEntity fileEntity = list.get(0);
            entity.setFileMappingPath(fileEntity.getFileMappingPath());
            entity.setFileDirPrefix(fileEntity.getFileDirPrefix());
            entity.setFileFullName(fileEntity.getFileFullName());
            entity.setFileDiskRelativePath(fileEntity.getFileDiskRelativePath());
            isHave = true;
        }

        // 文件不存在则重新保存
        if (!isHave) {
            // 创建文件存放文件夹
            String saveFileFolder = fileProperty.getUploadFolder() + SLASH + fileDiskRelativePath;
            File localFileFolder = new File(saveFileFolder);
            if (!localFileFolder.exists() || !localFileFolder.isDirectory()) {
                boolean mkdirs = localFileFolder.mkdirs();
                if (!mkdirs) {
                    log.error("------------LocalFileServiceImpl------------>uploadUrlOne:{}", "创建文件存放路径失败");
                    throw new ResponseException(Status.ERROR, "创建文件存放路径失败");
                }
            }
            // 创建存放文件
            String saveFilePath = saveFileFolder + SLASH + entity.getFileFullName();
            File localFile = new File(saveFilePath);
            try (FileOutputStream fileOutputStream = new FileOutputStream(localFile)) {
                IOUtils.copy(new ByteArrayInputStream(bytes), fileOutputStream);
            } catch (IOException e) {
                log.error("------------LocalFileServiceImpl------存放文件到本地失败------>upload:{}", e.getMessage());
                throw new ResponseException(Status.ERROR, "存放文件到本地失败:" + e.getMessage());
            }
        }

        // 文件信息入库并返回文件信息
        boolean result = super.save(entity);
        if (!result) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "保存文件数据失败");
        }
        return dtoMap.toDto(entity);
    }


    @Override
    public LocalFileUploadBatchDto uploadUrlBatch(List<LocalFileVo> vos) throws RuntimeException {
        List<LocalFileUploadBatchDto.UploadSuccess> successList = new ArrayList<>();
        List<LocalFileUploadBatchDto.UploadFail> failFailList = new ArrayList<>();
        // 结果类型:0-全部成功,1-部分失败,2-全部失败
        int type = 0;
        for (LocalFileVo vo : vos) {
            try {
                LocalFileDto localFileDto = this.uploadUrlOne(vo);
                LocalFileUploadBatchDto.UploadSuccess uploadSuccess = uploadSuccessMap.toEntity(localFileDto);
                uploadSuccess.setCallback(vo.getCallback());
                successList.add(uploadSuccess);
            } catch (Exception e) {
                log.error("------------LocalFileServiceImpl------------>uploadUrlBatch:{}", e.getMessage());
                e.printStackTrace();
                type = 1;
                LocalFileUploadBatchDto.UploadFail uploadFail = uploadFailMap.toEntity(vo);
                uploadFail.setFailCause(e.getMessage());
                failFailList.add(uploadFail);
            }
        }
        if (CollectionUtil.isEmpty(successList)) {
            type = 2;
        }
        LocalFileUploadBatchDto dto = new LocalFileUploadBatchDto();
        dto.setType(type);
        dto.setFailFailList(failFailList);
        dto.setSuccessList(successList);
        return dto;
    }


    @Override
    public void download(String localFileId, HttpServletResponse response) {
        LocalFileDto byId;
        try {
            byId = this.getById(localFileId);
        } catch (Exception e) {
            response.setStatus(Status.REQUEST_NOT_FOUND.getStatus());
            throw new ResponseException(Status.REQUEST_NOT_FOUND, "文件不存在:" + e.getMessage());
        }
        // 设置导出响应文件信息
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        try {
            String fileOriginalName = URLEncoder.encode(byId.getFileOriginalFullName(), String.valueOf(StandardCharsets.UTF_8));
            response.setHeader("Content-disposition", "attachment;filename=" + fileOriginalName);
        } catch (UnsupportedEncodingException e) {
            log.error("------------LocalFileServiceImpl------文件名url编码失败------>download:{}", e.getMessage());
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "文件名url编码失败:" + e.getMessage());
        }
        sendFile(byId, response);
    }


    private void sendFile(LocalFileDto byId, HttpServletResponse response) {
        File file = new File(fileProperty.getUploadFolder() + SLASH + byId.getFileDiskRelativePath());
        try (InputStream inputStream = new FileInputStream(file); OutputStream outputStream = response.getOutputStream()) {
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException io) {
            log.error("------------LocalFileServiceImpl------下载文件失败------>show:{}", io.getMessage());
            throw new ResponseException("下载或显示文件异常:" + io.getMessage());
        }
    }


    @Override
    public void show(String localFileId, HttpServletResponse response) {
        LocalFileDto byId;
        try {
            byId = this.getById(localFileId);
        } catch (Exception e) {
            response.setStatus(Status.REQUEST_NOT_FOUND.getStatus());
            throw new ResponseException(Status.REQUEST_NOT_FOUND, "文件不存在:" + e.getMessage());
        }
        sendFile(byId, response);
    }


    @Override
    public PageDto<LocalFilePageDto> pageByModel(LocalFilePageVo vo) throws RuntimeException {
        return new PageDto<>(mapper.pageByModel(vo.getPage(), vo));
    }


    @Override
    public LocalFileDto getById(String localFileId) throws RuntimeException {
        LocalFileEntity byId = super.getById(localFileId);
        if (Objects.isNull(byId)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "文件信息不存在");
        }
        return dtoMap.toDto(byId);
    }


    @Override
    public void deleteById(String localFileId) throws RuntimeException {
        // 查询数据是否存在
        LocalFileDto byId = this.getById(localFileId);
        // 删除文件数据库信息
        int delete = mapper.physicalDeleteById(localFileId);
        if (delete <= 0) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "删除文件数据失败");
        }
        // 查看是否是最后一个文件，如果是则删除磁盘文件
        LambdaQueryWrapper<LocalFileEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(LocalFileEntity::getFileMd5, byId.getFileMd5());
        List<LocalFileEntity> list = this.list(lambdaQueryWrapper);
        if (list.size() == 1) {
            File file = new File(fileProperty.getUploadFolder() + SLASH + byId.getFileDiskRelativePath());
            if (file.exists() && file.isFile()) {
                boolean fileDelete = file.delete();
                if (!fileDelete) {
                    throw new ResponseException(Status.ERROR, "删除文件失败");
                }
            }
        }
    }


    @Override
    public void deleteBatch(List<String> localFileIds) throws RuntimeException {
        List<LocalFileEntity> localFileEntities = this.listByIds(localFileIds);
        if (CollectionUtil.isEmpty(localFileEntities)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "文件不存在或已经被别人删除");
        }
        List<String> waitDeleteList = new ArrayList<>();
        localFileEntities.forEach(v -> waitDeleteList.add(v.getLocalFileId()));
        waitDeleteList.forEach(v -> {
            TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
            try {
                this.deleteById(v);
            } catch (Exception e) {
                dataSourceTransactionManager.rollback(transactionStatus);
                throw new ResponseException(Status.DATABASE_BASE_ERROR, "批量删除发生了异常:" + e.getMessage());
            }
            dataSourceTransactionManager.commit(transactionStatus);
        });
    }
}