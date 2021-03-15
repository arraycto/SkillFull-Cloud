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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.zxiaozhou.skillfull.auth.modules.rbac.entity.RbacUserEntity;
import indi.zxiaozhou.skillfull.auth.modules.rbac.mapper.RbacUserMapper;
import indi.zxiaozhou.skillfull.auth.modules.user.controller.vo.*;
import indi.zxiaozhou.skillfull.auth.modules.user.service.IUserCenterService;
import indi.zxiaozhou.skillfull.auth.security.token.TokenStore;
import indi.zxiaozhou.skillfull.auth.security.validate.CheckDto;
import indi.zxiaozhou.skillfull.auth.security.validate.CheckModel;
import indi.zxiaozhou.skillfull.auth.security.validate.IValidate;
import indi.zxiaozhou.skillfull.auth.security.validate.impl.SmsValidate;
import indi.zxiaozhou.skillfull.auth.utils.CryptAuthUtils;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import indi.zxiaozhou.skillfull.corecommon.utils.ConvertUtil;
import indi.zxiaozhou.skillfull.coremvc.utils.ContextHolderUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static indi.zxiaozhou.skillfull.corecommon.constant.SysBaseConstant.USER_LOGIN_PREFIX;

/**
 * 用户信息相关
 *
 * @author zxiaozhou
 * @date 2021-01-10 16:37
 * @since JDK11
 */
@Service
@RequiredArgsConstructor
public class UserCenterServiceImpl extends ServiceImpl<RbacUserMapper, RbacUserEntity> implements IUserCenterService {
    private final TokenStore tokenStore;
    private static final String CHANGE_PHONE_CHECK_OLD_SMS_RESULT = "CHANGE_PHONE_CHECK_OLD_SMS_RESULT_";
    private IValidate validate;

    @Autowired
    public void setValidate(SmsValidate validate) {
        this.validate = validate;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void changePassword(ChangePasswordVo vo) throws RuntimeException {
        String userId = ContextHolderUtils.getUserId();
        RbacUserEntity byId = this.getById(userId);
        if (Objects.isNull(byId)) {
            throw new ResponseException(Status.VERIFICATION_FAILED, "用户信息不存在或已被删除");
        }
        // 判断密码是否正确
        if (!CryptAuthUtils.matches(vo.getOldPassword(), byId.getSalt(), byId.getPassword())) {
            throw new ResponseException(Status.VERIFICATION_FAILED, "原密码不正确,请确定原密码");
        }
        // 修改密码
        CryptAuthUtils.PasswordInfo passwordInfo = CryptAuthUtils.getPasswordInfo(vo.getNewPassword());
        RbacUserEntity entity = new RbacUserEntity();
        entity.setUserId(userId);
        entity.setIsInitialPassword(0);
        entity.setPassword(passwordInfo.getEncodedPassword());
        entity.setSalt(passwordInfo.getSalt());
        boolean b = this.updateById(entity);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "修改密码失败");
        }
        // 清空用户所有登录信息
        tokenStore.removeDataLike(USER_LOGIN_PREFIX + userId + "*");
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void changeUserInfo(ChangeUserInfoVo vo) throws RuntimeException {
        String userId = ContextHolderUtils.getUserId();
        RbacUserEntity byId = this.getById(userId);
        if (Objects.isNull(byId)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "用户信息不存在或已被删除");
        }
        RbacUserEntity entity = ConvertUtil.map(vo, RbacUserEntity.class);
        entity.setUserId(byId.getUserId());
        boolean b = this.updateById(entity);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "更新用户信息失败");
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void findPassword(FindPasswordVo vo) throws RuntimeException {
        // 验证短信验证码是否正确
        CheckDto checkDto = validate.checkVerification(new CheckModel(vo.getPhone(), vo.getCode()));
        if (!checkDto.isResult()) {
            throw new ResponseException(Status.VERIFICATION_FAILED, checkDto.getMsg());
        }
        // 查看用户手机号码信息
        LambdaQueryWrapper<RbacUserEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RbacUserEntity::getPhone, vo.getPhone());
        RbacUserEntity one = this.getOne(lambdaQueryWrapper);
        if (Objects.isNull(one)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "当前手机号关联的用户信息不存在");
        }
        RbacUserEntity entity = new RbacUserEntity();
        entity.setUserId(one.getUserId());
        CryptAuthUtils.PasswordInfo passwordInfo = CryptAuthUtils.getPasswordInfo(vo.getPassword());
        entity.setSalt(passwordInfo.getSalt());
        entity.setIsInitialPassword(0);
        entity.setPassword(passwordInfo.getEncodedPassword());
        boolean b = this.updateById(entity);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "重置密码失败，请稍后再试");
        }
    }


    @Override
    public void checkOldUserPhoneSms(CheckOldUserPhoneVo vo) throws RuntimeException {
        // 验证短信验证码是否正确
        CheckDto checkDto = validate.checkVerification(new CheckModel(vo.getOldPhone(), vo.getCode()));
        if (!checkDto.isResult()) {
            throw new ResponseException(Status.VERIFICATION_FAILED, checkDto.getMsg());
        }
        // 保存检测结果
        tokenStore.save(CHANGE_PHONE_CHECK_OLD_SMS_RESULT + vo.getOldPhone(), 1, 60 * 30);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void changeUserPhone(ChangeUserPhoneVo vo) throws RuntimeException {
        // 验证旧手机验证码是否验证通过
        Object data = tokenStore.getData(CHANGE_PHONE_CHECK_OLD_SMS_RESULT + vo.getOldPhone());
        if (Objects.isNull(data)) {
            throw new ResponseException(Status.VERIFICATION_FAILED, "旧手机号码验证码验证未通过或未验证,请返回上一步");
        }
        // 验证新手机短信验证码
        CheckDto checkDto = validate.checkVerification(new CheckModel(vo.getPhone(), vo.getCode()));
        if (!checkDto.isResult()) {
            throw new ResponseException(Status.VERIFICATION_FAILED, checkDto.getMsg());
        }
        // 更新用户信息
        RbacUserEntity byId = this.getById(ContextHolderUtils.getUserId());
        if (StringUtils.isBlank(byId.getPhone())) {
            throw new ResponseException(Status.VERIFICATION_FAILED, "当前用户未绑定手机号码,请先绑定");
        }
        RbacUserEntity entity = new RbacUserEntity();
        entity.setUserId(byId.getUserId());
        entity.setPhone(vo.getPhone());
        boolean b = this.updateById(entity);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "修改手机号码失败");
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void bindUserPhone(BindUserPhoneVo vo) throws RuntimeException {
        // 验证新手机短信验证码
        CheckDto checkDto = validate.checkVerification(new CheckModel(vo.getPhone(), vo.getCode()));
        if (!checkDto.isResult()) {
            throw new ResponseException(Status.VERIFICATION_FAILED, checkDto.getMsg());
        }
        RbacUserEntity entity = new RbacUserEntity();
        entity.setUserId(ContextHolderUtils.getUserId());
        entity.setPhone(vo.getPhone());
        boolean b = this.updateById(entity);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "绑定手机号码失败");
        }
    }
}
