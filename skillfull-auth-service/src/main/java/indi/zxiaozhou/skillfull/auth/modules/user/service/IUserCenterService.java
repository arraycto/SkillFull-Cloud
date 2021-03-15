// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.user.service;


import indi.zxiaozhou.skillfull.auth.modules.rbac.entity.RbacUserEntity;
import indi.zxiaozhou.skillfull.auth.modules.user.controller.vo.*;
import indi.zxiaozhou.skillfull.coremvc.base.service.BaseService;

/**
 * 用户信息相关
 *
 * @author zxiaozhou
 * @date 2020-07-17 20:21
 * @since JDK11
 */
public interface IUserCenterService extends BaseService<RbacUserEntity> {
    /**
     * 修改密码
     *
     * @param vo ${@link ChangePasswordVo} 密码信息
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-01-10 16:39
     */
    void changePassword(ChangePasswordVo vo) throws RuntimeException;


    /**
     * 修改用户信息
     *
     * @param vo ${@link ChangeUserInfoVo} 待修改数据
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-01-13 10:04
     */
    void changeUserInfo(ChangeUserInfoVo vo) throws RuntimeException;


    /**
     * 找回密码
     *
     * @param vo ${@link FindPasswordVo} 待验证数据
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-01-13 10:08
     */
    void findPassword(FindPasswordVo vo) throws RuntimeException;


    /**
     * 修改手机号前验证旧手机验证码
     *
     * @param vo ${@link CheckOldUserPhoneVo} 待验证信息
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-01-13 10:45
     */
    void checkOldUserPhoneSms(CheckOldUserPhoneVo vo) throws RuntimeException;


    /**
     * 修改手机号
     *
     * @param vo ${@link ChangeUserPhoneVo} 待修改手机号信息
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-01-13 10:32
     */
    void changeUserPhone(ChangeUserPhoneVo vo) throws RuntimeException;


    /**
     * 绑定手机号码
     *
     * @param vo ${@link BindUserPhoneVo} 待绑定手机号信息
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-01-13 10:31
     */
    void bindUserPhone(BindUserPhoneVo vo) throws RuntimeException;
}
