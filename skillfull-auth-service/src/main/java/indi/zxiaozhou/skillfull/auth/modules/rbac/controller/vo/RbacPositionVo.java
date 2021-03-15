// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo;

import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotBlankOrNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 职位表添加或修改Request
 *
 * @author zxiaozhou
 * @date 2021-01-19 18:17:46
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class RbacPositionVo implements Serializable {
    private static final long serialVersionUID = -94931366772464032L;

    @ApiModelProperty(name = "positionName", value = "职位名称", required = true)
    @NotBlankOrNull(message = "职位名称不能为空")
    private String positionName;

    @ApiModelProperty(name = "positionCode", value = "职位编码", required = true)
    @NotBlankOrNull(message = "职位编码不能为空")
    private String positionCode;

    @ApiModelProperty(name = "autoBind", value = "绑定方式:0-手动,1-自动。默认0", required = true)
    @NotBlankOrNull(message = "绑定方式:0-手动,1-自动。默认0不能为空")
    private Integer autoBind;

    @ApiModelProperty(name = "positionRank", value = "职级", required = true)
    @NotBlankOrNull(message = "职级不能为空")
    private Integer positionRank;

    @ApiModelProperty(name = "positionStatus", value = "职位状态：0-无效，1-有效，默认0", required = true)
    @NotBlankOrNull(message = "职位状态：0-无效，1-有效，默认0不能为空")
    private Integer positionStatus;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
}