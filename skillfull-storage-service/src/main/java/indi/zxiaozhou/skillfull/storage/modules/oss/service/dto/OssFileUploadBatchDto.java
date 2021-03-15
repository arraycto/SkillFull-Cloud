// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.storage.modules.oss.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * url文件上传结果
 *
 * @author zxiaozhou
 * @date 2020-10-24 04:19
 * @since JDK11
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class OssFileUploadBatchDto implements Serializable {
    private static final long serialVersionUID = 7561219482591890756L;
    @ApiModelProperty(name = "successList", value = "成功文件合集")
    private List<UploadSuccess> successList;

    @ApiModelProperty(name = "type", value = "结果类型:0-全部成功,1-部分失败,2-全部失败")
    private int type;

    @ApiModelProperty(name = "failFailList", value = "失败文件合集")
    private List<UploadFail> failFailList;


    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    public static class UploadFail implements Serializable {
        private static final long serialVersionUID = -47966425117798554L;

        @ApiModelProperty(name = "fileOriginalFullName", value = "原始文件名全称(包括扩展名)")
        private String fileOriginalFullName;

        @ApiModelProperty(name = "fileDirPrefix", value = "文件存放文件夹名称")
        private String fileDirPrefix;

        @ApiModelProperty(name = "remark", value = "备注")
        private String remark;

        @ApiModelProperty(name = "url", value = "文件url地址")
        private String url;

        @ApiModelProperty(name = "callback", value = "回调,传入原路返回")
        private String callback;

        @ApiModelProperty(name = "failCause", value = "失败原因")
        private String failCause;
    }


    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    public static class UploadSuccess implements Serializable {
        private static final long serialVersionUID = -45887591128358710L;

        @ApiModelProperty(name = "ossFileId", value = "文件id")
        private String ossFileId;

        @ApiModelProperty(name = "fileOriginalName", value = "原始文件名(不包括扩展名)")
        private String fileOriginalName;

        @ApiModelProperty(name = "fileFullName", value = "文件名全名称(包括扩展名)")
        private String fileFullName;

        @ApiModelProperty(name = "fileOriginalFullName", value = "原始文件名全称(包括扩展名)")
        private String fileOriginalFullName;

        @ApiModelProperty(name = "fileType", value = "文件类型")
        private String fileType;

        @ApiModelProperty(name = "fileSize", value = "文件大小")
        private String fileSize;

        @ApiModelProperty(name = "fileSizeDetail", value = "文件详细大小")
        private Long fileSizeDetail;

        @ApiModelProperty(name = "ossTag", value = "oss tag信息")
        private String ossTag;

        @ApiModelProperty(name = "fileDirPrefix", value = "文件存放文件夹名称")
        private String fileDirPrefix;

        @ApiModelProperty(name = "fileMd5", value = "文件md5值")
        private String fileMd5;

        @ApiModelProperty(name = "endpoint", value = "endpoint")
        private String endpoint;

        @ApiModelProperty(name = "bucket", value = "bucket名称")
        private String bucket;

        @ApiModelProperty(name = "remark", value = "备注")
        private String remark;

        @ApiModelProperty(name = "createTime", value = "创建时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
        private LocalDateTime createTime;

        @ApiModelProperty(name = "callback", value = "回调,传入原路返回")
        private String callback;
    }
}
