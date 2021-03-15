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
 * 按钮权限
 *
 * @author zxiaozhou
 * @date 2020-09-26 17:16:14
 * @since JDK11
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class ActionPermissionDto implements Serializable {
    private static final long serialVersionUID = -45340336683549641L;
    @ApiModelProperty(name = "permissionId", value = "权限id(对应相应路由菜单id)")
    private String permissionId;

    @ApiModelProperty(name = "permissionName", value = "权限名称(对应相应路由菜单名称)")
    private String permissionName;

    @ApiModelProperty(name = "actions", value = "指令")
    private String actions;

    @ApiModelProperty(name = "title", value = "指令名称")
    private String title;

    @ApiModelProperty(name = "actionsUri", value = "按钮权限后端对应uri(相对地址,check_action_request为true时必填)")
    private String actionsUri;

    @ApiModelProperty(name = "buttonStrategy", value = "按钮校验策略：1-显示控制(未授权时前端按钮不显示)，2-编辑控制(未授权时前端按钮显示但后端数据不可操作)")
    private Integer buttonStrategy;

    @ApiModelProperty(name = "checkActionRequest", value = "按钮权限是否校验后端，1-校验,0-不校验(前端路由为boolean类型,当校验时actions_uri必填)")
    private Boolean checkActionRequest;
}