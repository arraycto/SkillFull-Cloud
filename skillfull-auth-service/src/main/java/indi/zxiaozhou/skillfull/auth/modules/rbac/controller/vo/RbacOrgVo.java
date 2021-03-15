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
 * 组织表添加或修改Request
 *
 * @author zxiaozhou
 * @date 2021-01-19 12:59:41
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class RbacOrgVo implements Serializable {
    private static final long serialVersionUID = -55129698456654917L;

    @ApiModelProperty(name = "parentId", value = "父级组织id")
    private String parentId;

    @ApiModelProperty(name = "orgName", value = "组织名称", required = true)
    @NotBlankOrNull(message = "组织名称不能为空")
    private String orgName;

    @ApiModelProperty(name = "orgNameEn", value = "英文名")
    private String orgNameEn;

    @ApiModelProperty(name = "orgNameAbbr", value = "缩写")
    private String orgNameAbbr;

    @ApiModelProperty(name = "orgOrder", value = "排序,默认0")
    private Integer orgOrder = 0;

    @ApiModelProperty(name = "orgType", value = "组织机构类型：1-公司,2-部门", required = true)
    @NotBlankOrNull(message = "组织机构类型：1-公司,2-部门不能为空")
    private Integer orgType;

    @ApiModelProperty(name = "orgCode", value = "组织编码", required = true)
    @NotBlankOrNull(message = "组织编码不能为空")
    private String orgCode;

    @ApiModelProperty(name = "orgStatus", value = "组织状态：0-无效，1-有效，默认0", required = true)
    @NotBlankOrNull(message = "组织状态：0-无效，1-有效，默认0不能为空")
    private Integer orgStatus;

    @ApiModelProperty(name = "autoBind", value = "绑定方式:0-手动,1-自动。默认0", required = true)
    @NotBlankOrNull(message = "绑定方式:0-手动,1-自动。默认0不能为空")
    private Integer autoBind;

    @ApiModelProperty(name = "phone", value = "手机号")
    private String phone;

    @ApiModelProperty(name = "fax", value = "传真")
    private String fax;

    @ApiModelProperty(name = "address", value = "地址")
    private String address;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
}