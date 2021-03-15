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

import com.fasterxml.jackson.annotation.JsonIgnore;
import indi.zxiaozhou.skillfull.corecommon.base.model.UserAndAuthModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * security user detail
 *
 * @author zxiaozhou
 * @date 2020-06-28 12:19
 * @since JDK11
 */
@RequiredArgsConstructor
@Getter
@ToString(callSuper = true)
public class CustomUserDetails implements UserDetails {
    private static final long serialVersionUID = 704418727943564609L;

    private final UserAndAuthModel userAndAuthModel;

    private final List<CustomGrantedAuthority> authorities = Collections.emptyList();

    private TokenInfo tokenInfo;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.userAndAuthModel.getPassword();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return this.userAndAuthModel.getUserName();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return this.userAndAuthModel.getUserStatus() == 2;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return this.userAndAuthModel.getUserStatus() == 1;
    }

    @JsonIgnore
    public void setTokenInfo(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    @JsonIgnore
    public TokenInfo getTokenInfo() {
        return this.tokenInfo;
    }
}
