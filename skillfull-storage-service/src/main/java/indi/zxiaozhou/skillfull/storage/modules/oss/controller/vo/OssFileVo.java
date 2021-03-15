// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.storage.modules.oss.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * oss文件添加或修改Request
 *
 * @author zxiaozhou
 * @date 2020-10-26 12:25:51
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class OssFileVo implements Serializable {
    private static final long serialVersionUID = 241666211161657770L;

    @ApiModelProperty(name = "fileOriginalFullName", value = "原始文件名全称(包括扩展名)")
    private String fileOriginalFullName;

    @ApiModelProperty(name = "fileDirPrefix", value = "文件存放文件夹名称")
    private String fileDirPrefix;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "url", value = "文件url地址", required = true)
    @NotBlank(message = "文件url地址不能为空")
    private String url;

    @ApiModelProperty(name = "callback", value = "回调,传入原路返回")
    private String callback;

    @ApiModelProperty(name = "fileFolder", value = "存放文件夹")
    private String fileFolder;

}