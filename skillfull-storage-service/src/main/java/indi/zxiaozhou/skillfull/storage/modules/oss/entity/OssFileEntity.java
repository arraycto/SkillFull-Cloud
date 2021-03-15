// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.storage.modules.oss.entity;

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
 * oss文件(OssFile)Entity
 *
 * @author zxiaozhou
 * @date 2021-02-12 20:21:10
 * @since JDK1.8
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("storage_oss_file")
public class OssFileEntity extends BaseEntity {
    private static final long serialVersionUID = -91555873637884425L;

    @TableId
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