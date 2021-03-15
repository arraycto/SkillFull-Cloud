// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import indi.zxiaozhou.skillfull.auth.modules.rbac.entity.RbacUserEntity;
import indi.zxiaozhou.skillfull.auth.modules.user.service.ILoginService;
import indi.zxiaozhou.skillfull.auth.modules.user.service.model.CustomUserDetails;
import io.undertow.util.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


/**
 * security user detail
 *
 * @author zxiaozhou
 * @date 2020-06-28 12:17
 * @since JDK11
 */
@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final ILoginService loginService;


    @SneakyThrows
    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public CustomUserDetails loadUserByUsername(String principal) {
        // 获取用户信息
        QueryWrapper<RbacUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(RbacUserEntity::getUserName, principal).or()
                .eq(RbacUserEntity::getEmail, principal).or()
                .eq(RbacUserEntity::getPhone, principal);
        RbacUserEntity user = loginService.getOne(queryWrapper);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        } else if (user.getUserStatus() == 0) {
            throw new BadRequestException("账号未激活,请联系管理员");
        } else if (user.getUserStatus() == 2) {
            throw new BadRequestException("账号已经被冻结,请联系管理员");
        }
        return new CustomUserDetails(loginService.getPermissionInfo(user, null, null));
    }
}

