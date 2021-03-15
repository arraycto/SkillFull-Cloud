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


import indi.zxiaozhou.skillfull.auth.modules.user.service.model.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * security user detail(微信)
 *
 * @author zxiaozhou
 * @date 2020-06-28 12:17
 * @since JDK11
 */
@RequiredArgsConstructor
@Service("userDetailsWeiXinService")
@Slf4j
public class UserDetailsWeiXinServiceImpl implements UserDetailsService {


    @SneakyThrows
    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public CustomUserDetails loadUserByUsername(String principal) {
        log.info("------------UserDetailsWeiXinServiceImpl------只是微信登录------>loadUserByUsername:{}", principal);
        return null;
    }
}

