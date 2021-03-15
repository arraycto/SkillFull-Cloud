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
 * 职位表分页查询Response
 *
 * @author zxiaozhou
 * @date 2021-01-19 18:17:52
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class RbacPositionPageDto implements Serializable {
    private static final long serialVersionUID = 291438573006729075L;

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

    @ApiModelProperty(name = "positionStatus", value = "职位状态：0-无效，1-有效，默认0")
    private Integer positionStatus;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "createUserName", value = "创建用户姓名")
    private String createUserName;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime createTime;

}