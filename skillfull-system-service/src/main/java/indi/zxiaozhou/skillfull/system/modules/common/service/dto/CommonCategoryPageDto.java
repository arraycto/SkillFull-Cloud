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
 * 分类字典表分页查询Response
 *
 * @author zxiaozhou
 * @date 2021-01-07 23:40:12
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class CommonCategoryPageDto implements Serializable {
    private static final long serialVersionUID = 999040784753501720L;

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

    @ApiModelProperty(name = "createAreaCode", value = "创建区域编码")
    private String createAreaCode;

    @ApiModelProperty(name = "createUserId", value = "创建用户id")
    private String createUserId;

    @ApiModelProperty(name = "createUserName", value = "创建用户姓名")
    private String createUserName;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(name = "hasChildren", value = "是否有子节点,默认false")
    private boolean hasChildren;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
}