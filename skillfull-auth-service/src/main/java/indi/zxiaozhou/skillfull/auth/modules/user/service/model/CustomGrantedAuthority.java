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

import org.springframework.security.core.GrantedAuthority;

/**
 * 自定义权限字段
 *
 * @author zxiaozhou
 * @date 2020-07-20 19:01
 * @since JDK11
 */
public class CustomGrantedAuthority implements GrantedAuthority {
    private static final long serialVersionUID = -9177586441196475946L;
    /**
     * 1-角色,2-按钮权限
     */
    private int type;
    /**
     * 权限指令
     */
    private String auth;
    /**
     * 接口地址-当为按钮时一定有该值
     */
    private String url;

    @Override
    public String getAuthority() {
        return this.auth;
    }

    public CustomGrantedAuthority(int type, String auth, String url) {
        this.type = type;
        this.auth = auth;
        this.url = url;
    }

    public CustomGrantedAuthority(String auth) {
        this.type = 1;
        this.auth = auth;
    }

    public CustomGrantedAuthority(String auth, String url) {
        this.type = 2;
        this.auth = auth;
        this.url = url;
    }
}
