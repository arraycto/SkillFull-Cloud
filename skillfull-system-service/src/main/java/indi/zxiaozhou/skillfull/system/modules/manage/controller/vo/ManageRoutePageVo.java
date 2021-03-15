// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.manage.controller.vo;

import indi.zxiaozhou.skillfull.coremvc.base.controller.vo.BasePageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 路由分页查询Request
 *
 * @author zxiaozhou
 * @date 2020-09-12 16:33:36
 * @since JDK1.8
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel
public class ManageRoutePageVo extends BasePageVo {
    private static final long serialVersionUID = 478784893627749058L;

    @ApiModelProperty(name = "routeCode", value = "路由编码(唯一)")
    private String routeCode;

    @ApiModelProperty(name = "routeName", value = "路由名称")
    private String routeName;

    @ApiModelProperty(name = "routeState", value = "路由状态:0-禁用,1-启用。默认0")
    private Integer routeState;
}