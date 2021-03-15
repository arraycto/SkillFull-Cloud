// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.user.service.model;


import indi.zxiaozhou.skillfull.auth.security.model.TokenUserInfo;
import indi.zxiaozhou.skillfull.corecommon.utils.tree.model.BaseTree;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

/**
 * 用户所有信息
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
@ApiModel(value = "用户权限查询Response", description = "用户权限查询响应")
public class LoginPermissionInfo implements Serializable {
    private static final long serialVersionUID = -5725052195449698245L;

    @ApiModelProperty(name = "roles", value = "用户角色信息")
    private Set<String> roles = Collections.emptySet();

    @ApiModelProperty(name = "userInfo", value = "用户信息")
    private TokenUserInfo.UserInfo userInfo;


    @Data
    public static class Auth {

        @ApiModelProperty(name = "perms", value = "按钮权限编码")
        private String perms;

        @ApiModelProperty(name = "url", value = "路径")
        private String url;

        @ApiModelProperty(name = "describe", value = "按钮权限描述，与即菜单名称公用一个字段")
        private String describe;
    }


    @Data
    public static class Meta {
        /**
         * 权限
         */
        private Set<String> roles = Collections.emptySet();
        /**
         * 路由标题
         */
        private String title;
        /**
         * 路由图标
         */
        private String icon;
        /**
         * 是否缓存
         */
        private boolean noCache;
        /**
         * breadcrumb面包屑中显示
         */
        private boolean breadcrumb = true;
        /**
         * 固定在tags-view中
         */
        private boolean affix;
        /**
         * 高亮相对应的侧边栏
         */
        private String activeMenu;
    }


    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Menu extends BaseTree<Menu> {
        /**
         * id
         */
        private String id;
        /**
         * 父级id
         */
        private String parentId;

        /**
         * 侧边栏出现
         */
        private boolean hidden = false;
        /**
         * noRedirect:面包屑不可点击
         * redirect:面包屑可点击
         */
        private String redirect;
        /**
         * 是否根路由
         */
        private boolean alwaysShow;
        /**
         * 路由名称
         */
        private String name;

        /**
         * 路由路径
         */
        private String path;
        /**
         * 组件
         */
        private String component;

        /**
         * 菜单meta
         */
        private Meta meta;
    }
}

