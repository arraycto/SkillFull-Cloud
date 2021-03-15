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
 * 角色表分页查询Request
 *
 * @author zxiaozhou
 * @date 2020-10-08 13:44:03
 * @since JDK11
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel
public class RbacRolePageVo extends BasePageVo {
    private static final long serialVersionUID = -92997123798027495L;

    @ApiModelProperty(name = "keyword", value = "角色名称或角色编码")
    private String keyword;

    @ApiModelProperty(name = "roleStatus", value = "角色状态:0-禁用,1-启用,默认0")
    private Integer roleStatus;
}