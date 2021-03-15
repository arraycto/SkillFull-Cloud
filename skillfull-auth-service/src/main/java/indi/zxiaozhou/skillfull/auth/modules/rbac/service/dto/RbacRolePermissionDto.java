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
 * 角色-权限表查询Response
 *
 * @author zxiaozhou
 * @date 2020-10-08 13:29:14
 * @since JDK11
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class RbacRolePermissionDto implements Serializable {
    private static final long serialVersionUID = -63399996952662860L;

    @ApiModelProperty(name = "permissionId", value = "权限id")
    private String permissionId;

    @ApiModelProperty(name = "parentId", value = "父id")
    private String parentId;

    @ApiModelProperty(name = "title", value = "菜单名称")
    private String title;

    @ApiModelProperty(name = "path", value = "路径")
    private String path;

    @ApiModelProperty(name = "target", value = "打开:_blank,_self,_top,_parent,默认:_self")
    private String target;

    @ApiModelProperty(name = "component", value = "前端组件")
    private String component;

    @ApiModelProperty(name = "pathName", value = "路由名称")
    private String pathName;

    @ApiModelProperty(name = "redirect", value = "重定向地址")
    private String redirect;

    @ApiModelProperty(name = "permissionType", value = "权限类型(0:目录; 1:菜单:2:按钮)")
    private Integer permissionType;

    @ApiModelProperty(name = "actionsUri", value = "按钮权限后端对应uri(相对地址,check_action_request为true时必填)")
    private String actionsUri;

    @ApiModelProperty(name = "actions", value = "按钮权限编码，例如：“sys:schedule:list”,多个逗号隔开")
    private String actions;

    @ApiModelProperty(name = "isExternal", value = "是否外部链接，1-是，0-不是，默认不是(前端路由为boolean类型)")
    private Boolean isExternal;

    @ApiModelProperty(name = "buttonStrategy", value = "按钮校验策略：1-显示控制(未授权时前端按钮不显示)，2-编辑控制(未授权时前端按钮显示但后端数据不可操作)")
    private Integer buttonStrategy;

    @ApiModelProperty(name = "checkActionRequest", value = "按钮权限是否校验后端，1-校验,0-不校验(前端路由为boolean类型,当校验时actions_uri必填)")
    private Boolean checkActionRequest;

    @ApiModelProperty(name = "sortNo", value = "排序，值越小越靠前,默认0")
    private Integer sortNo;

    @ApiModelProperty(name = "hiddenHeaderContent", value = "隐藏 PageHeader 组件中的页面带的 面包屑和页面标题栏，1-显示,0-不显示。(前端路由为boolean类型)")
    private Integer hiddenHeaderContent;

    @ApiModelProperty(name = "isShow", value = "配合hideChildrenInMenu使用，用于隐藏菜单时，提供递归到父菜单显示 ,1-隐藏,0-不隐藏，(前端路由为boolean类型,对应meta hidden)")
    private Integer isShow;

    @ApiModelProperty(name = "hideChildren", value = "用于隐藏不需要在菜单中展示的子路由,1-隐藏,0-不隐藏，(前端路由为boolean类型)")
    private Integer hideChildren;

    @ApiModelProperty(name = "icon", value = "图标")
    private String icon;

    @ApiModelProperty(name = "hidden", value = "控制路由和子路由是否显示在 sidebar,1-隐藏，0-不隐藏(前端路由为boolean类型)")
    private Integer hidden;

    @ApiModelProperty(name = "permissionStatus", value = "权限状态:0-无效，1-有效,默认0")
    private Integer permissionStatus;

    @ApiModelProperty(name = "keepAlive", value = "缓存路由1-缓存,0不缓存(开启 multi-tab 是默认值为 true),(前端路由为boolean类型)")
    private Integer keepAlive;

    @ApiModelProperty(name = "roleName", value = "角色名称")
    private String roleName;

    @ApiModelProperty(name = "roleCode", value = "角色编码")
    private String roleCode;

    @ApiModelProperty(name = "roleSysCode", value = "角色系统编码(系统自动创建)")
    private String roleSysCode;

    @ApiModelProperty(name = "roleId", value = "角色id")
    private String roleId;
}