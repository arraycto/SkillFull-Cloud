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
 * 组织表查询Response
 *
 * @author zxiaozhou
 * @date 2021-01-19 12:59:36
 * @since JDK11
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class RbacCorrelateUserOrgDto implements Serializable {
    private static final long serialVersionUID = -23698600939298122L;

    @ApiModelProperty(name = "orgId", value = "机构id")
    private String orgId;

    @ApiModelProperty(name = "orgCode", value = "组织编码")
    private String orgCode;

    @ApiModelProperty(name = "orgSysCode", value = "组织编码(系统)")
    private String orgSysCode;

    @ApiModelProperty(name = "orgName", value = "组织名称")
    private String orgName;

    @ApiModelProperty(name = "autoBind", value = "绑定方式:0-手动,1-自动。默认0")
    private Integer autoBind;

    @ApiModelProperty(name = "orgType", value = "组织机构类型：1-公司,2-部门")
    private Integer orgType;
}