// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 职位表查询Response
 *
 * @author zxiaozhou
 * @date 2021-01-19 18:17:43
 * @since JDK11
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class RbacPositionDto implements Serializable {
    private static final long serialVersionUID = -93521238569350886L;

    @ApiModelProperty(name = "positionId", value = "职位id")
    private String positionId;

    @ApiModelProperty(name = "positionCode", value = "职位编码")
    private String positionCode;

    @ApiModelProperty(name = "positionName", value = "职位名称")
    private String positionName;

    @ApiModelProperty(name = "autoBind", value = "绑定方式:0-手动,1-自动。默认0")
    private Integer autoBind;

    @ApiModelProperty(name = "positionRank", value = "职级")
    private Integer positionRank;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

}