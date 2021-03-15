// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.user.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

/**
 * 用户菜单信息
 *
 * @author zxiaozhou
 * @date 2020-07-01 03:21
 * @since JDK11
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class RouterInfoDto implements Serializable {
    private static final long serialVersionUID = -5725052195449698245L;
    @ApiModelProperty(name = "permissionId", value = "路由id")
    private String permissionId;

    @ApiModelProperty(name = "parentId", value = "上级路由id")
    private String parentId;

    @ApiModelProperty(name = "alwaysShow", value = "显示为根路由:0-否1-是,默认0,设置后会一直以根路由形式显示(前端路由为boolean类型)")
    private Boolean alwaysShow;

    @ApiModelProperty(name = "isExternal", value = "是否外部链接，1-是，0-不是，默认不是(前端路由为boolean类型)")
    private Boolean isExternal;

    @ApiModelProperty(name = "externalUrl", value = "外部链接地址")
    private String externalUrl;

    @ApiModelProperty(name = "openType", value = "外部链接打开方式:1-新页面,2-当前页面,。默认1")
    private Integer openType;

    @ApiModelProperty(name = "path", value = "路径")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String path;

    @ApiModelProperty(name = "component", value = "前端组件")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String component;

    @ApiModelProperty(name = "pathName", value = "路由名称")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String pathName;

    @ApiModelProperty(name = "redirect", value = "重定向地址")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String redirect;

    @ApiModelProperty(name = "hidden", value = "隐藏路由:0-隐藏,1-不隐藏,默认0,隐藏后侧边栏不显示(前端路由为boolean类型)")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Boolean hidden;

    @ApiModelProperty(name = "meta", value = "菜单meta")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Meta meta;

    @Data
    public static class Meta {
        @ApiModelProperty(name = "iconType", value = "图标类型:0-系统自带,1-自定义。默认0")
        private Integer iconType;

        @ApiModelProperty(name = "activeMenu", value = "指定侧边栏高亮路由，设置后点击当前路由侧边栏会高亮制定的路由")
        private String activeMenu;

        @ApiModelProperty(name = "icon", value = "图标")
        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        private String icon;

        @ApiModelProperty(name = "keepAlive", value = "缓存路由(开启 multi-tab 是默认值为 true)，0-不缓存,1-缓存(返回前端转为boolean)")
        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        private Boolean keepAlive;

        @ApiModelProperty(name = "permissions", value = "权限,对应路由id")
        private Set<String> permissions = Collections.emptySet();

        @ApiModelProperty(name = "title", value = "菜单名称")
        private String title;

        @ApiModelProperty(name = "breadcrumb", value = "面包屑中显示:0-不显示,1-显示,默认1,(前端路由为boolean类型)")
        private Boolean breadcrumb;

        @ApiModelProperty(name = "affix", value = "是否tags中固定:0-不固定,1-固定,默认0,(前端路由为boolean类型)")
        private Boolean affix;
    }
}

