// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.common.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 分类字典表查询Response
 *
 * @author zxiaozhou
 * @date 2021-01-07 23:39:55
 * @since JDK11
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class CommonCategoryDto implements Serializable {
    private static final long serialVersionUID = 235699223663179437L;

    @ApiModelProperty(name = "categoryId", value = "分类id")
    private String categoryId;

    @ApiModelProperty(name = "parentId", value = "父级id")
    private String parentId;

    @ApiModelProperty(name = "categoryName", value = "分类名称")
    private String categoryName;

    @ApiModelProperty(name = "categoryCommonCode", value = "分类统一编码")
    private String categoryCommonCode;

    @ApiModelProperty(name = "categoryCode", value = "分类编码")
    private String categoryCode;

    @ApiModelProperty(name = "isParent", value = "是否父节:0-不是，1-是，默认0")
    private Integer isParent;

    @ApiModelProperty(name = "createUserId", value = "创建用户id")
    private String createUserId;

    @ApiModelProperty(name = "createUserName", value = "创建用户姓名")
    private String createUserName;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
}