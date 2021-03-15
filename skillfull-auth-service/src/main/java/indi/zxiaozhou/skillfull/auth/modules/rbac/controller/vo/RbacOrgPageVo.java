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

import indi.zxiaozhou.skillfull.coremvc.base.controller.vo.BasePageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 组织表分页查询Request
 *
 * @author zxiaozhou
 * @date 2021-01-19 12:59:46
 * @since JDK11
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel
public class RbacOrgPageVo extends BasePageVo {
    private static final long serialVersionUID = -28467079406639001L;

    @ApiModelProperty(name = "orgName", value = "组织名称")
    private String orgName;

    @ApiModelProperty(name = "orgCode", value = "组织编码")
    private String orgCode;
}