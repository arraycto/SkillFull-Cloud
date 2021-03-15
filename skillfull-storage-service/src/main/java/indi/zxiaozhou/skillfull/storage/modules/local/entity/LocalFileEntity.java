// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.storage.modules.local.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.zxiaozhou.skillfull.coremvc.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 本地文件服务(LocalFile)Entity
 *
 * @author zxiaozhou
 * @date 2021-02-12 20:20:41
 * @since JDK1.8
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("storage_local_file")
public class LocalFileEntity extends BaseEntity {
    private static final long serialVersionUID = -15721424592334726L;

    @TableId
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

    @ApiModelProperty(name = "createAreaCode", value = "创建区域编码")
    private String createAreaCode;

    @ApiModelProperty(name = "createPositionCode", value = "创建职位编码")
    private String createPositionCode;

    @ApiModelProperty(name = "createOrgSysCode", value = "创建机构系统编码")
    private String createOrgSysCode;

    @ApiModelProperty(name = "createSystemCode", value = "创建系统编码")
    private String createSystemCode;

    @ApiModelProperty(name = "createTenantId", value = "创建租户id")
    private String createTenantId;
}