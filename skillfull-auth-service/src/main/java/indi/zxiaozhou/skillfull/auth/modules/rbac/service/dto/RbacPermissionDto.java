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
 * 权限表查询Response
 *
 * @author zxiaozhou
 * @date 2021-01-14 22:02:19
 * @since JDK11
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class RbacPermissionDto implements Serializable {
    private static final long serialVersionUID = 657463154206688336L;

    @ApiModelProperty(name = "permissionId", value = "权限id")
    private String permissionId;

    @ApiModelProperty(name = "parentId", value = "父id")
    private String parentId;

    @ApiModelProperty(name = "title", value = "菜单名称")
    private String title;

    @ApiModelProperty(name = "path", value = "路径")
    private String path;

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

    @ApiModelProperty(name = "externalUrl", value = "外部链接地址")
    private String externalUrl;

    @ApiModelProperty(name = "openType", value = "外部链接打开方式:1-新页面,2-当前页面,。默认1")
    private Integer openType;

    @ApiModelProperty(name = "buttonStrategy", value = "按钮校验策略：1-显示控制(未授权时前端按钮不显示)，2-编辑控制(未授权时前端按钮显示但后端数据不可操作)")
    private Integer buttonStrategy;

    @ApiModelProperty(name = "checkActionRequest", value = "按钮权限是否校验后端，1-校验,0-不校验(前端路由为boolean类型,当校验时actions_uri必填)")
    private Boolean checkActionRequest;

    @ApiModelProperty(name = "sortNo", value = "排序，值越小越靠前,默认0")
    private Integer sortNo;

    @ApiModelProperty(name = "iconType", value = "图标类型:0-系统自带,1-自定义。默认0")
    private Integer iconType;

    @ApiModelProperty(name = "activeMenu", value = "指定侧边栏高亮路由，设置后点击当前路由侧边栏会高亮制定的路由")
    private String activeMenu;

    @ApiModelProperty(name = "icon", value = "图标")
    private String icon;

    @ApiModelProperty(name = "alwaysShow", value = "显示为根路由:0-否1-是,默认0,设置后会一直以根路由形式显示(前端路由为boolean类型)")
    private Boolean alwaysShow;

    @ApiModelProperty(name = "hidden", value = "隐藏路由:0-隐藏,1-不隐藏,默认0,隐藏后侧边栏不显示(前端路由为boolean类型)")
    private Boolean hidden;

    @ApiModelProperty(name = "permissionStatus", value = "权限状态:0-无效，1-有效,默认0")
    private Integer permissionStatus;

    @ApiModelProperty(name = "keepAlive", value = "缓存路由:1-缓存,0不缓存,前端路由为boolean类型)")
    private Boolean keepAlive;

    @ApiModelProperty(name = "breadcrumb", value = "面包屑中显示:0-不显示,1-显示,默认1,(前端路由为boolean类型)")
    private Boolean breadcrumb;

    @ApiModelProperty(name = "affix", value = "是否tags中固定:0-不固定,1-固定,默认0,(前端路由为boolean类型)")
    private Boolean affix;

    @ApiModelProperty(name = "enableDelete", value = "是否可删除:0-不可删除,1-可删除。默认1(用户系统内置数据不可删除)")
    private Integer enableDelete;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "createUserId", value = "创建用户id")
    private String createUserId;

    @ApiModelProperty(name = "permissionSysCode", value = "系统内置编码(系统自动生成)")
    private String permissionSysCode;

    @ApiModelProperty(name = "createUserName", value = "创建用户姓名")
    private String createUserName;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime createTime;
}