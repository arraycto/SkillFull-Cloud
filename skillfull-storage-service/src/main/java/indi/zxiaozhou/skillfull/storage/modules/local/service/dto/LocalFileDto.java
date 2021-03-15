// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.storage.modules.local.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 本地文件服务查询Response
 *
 * @author zxiaozhou
 * @date 2020-10-23 22:25:29
 * @since JDK11
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class LocalFileDto implements Serializable {
    private static final long serialVersionUID = -45887591128358710L;

    @ApiModelProperty(name = "localFileId", value = "文件id")
    private String localFileId;

    @ApiModelProperty(name = "fileOriginalName", value = "原始文件名(不包括扩展名)")
    private String fileOriginalName;

    @ApiModelProperty(name = "fileOriginalFullName", value = "原始文件名全称(包括扩展名)")
    private String fileOriginalFullName;

    @ApiModelProperty(name = "fileFullName", value = "文件名全名称(包括扩展名)")
    private String fileFullName;

    @ApiModelProperty(name = "fileType", value = "文件类型")
    private String fileType;

    @ApiModelProperty(name = "fileSize", value = "文件大小")
    private String fileSize;

    @ApiModelProperty(name = "fileSizeDetail", value = "文件详细大小")
    private Long fileSizeDetail;

    @ApiModelProperty(name = "fileMd5", value = "文件md5值")
    private String fileMd5;

    @ApiModelProperty(name = "fileDiskRelativePath", value = "文件磁盘相对路径(即系统定义文件文件存放磁盘开始路径除外)")
    private String fileDiskRelativePath;

    @ApiModelProperty(name = "fileMappingPath", value = "文件预览相对路径(即加了file的映射路径)")
    private String fileMappingPath;

    @ApiModelProperty(name = "fileDirPrefix", value = "文件存放文件夹名称")
    private String fileDirPrefix;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "createUserName", value = "创建用户姓名")
    private String createUserName;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime createTime;
}