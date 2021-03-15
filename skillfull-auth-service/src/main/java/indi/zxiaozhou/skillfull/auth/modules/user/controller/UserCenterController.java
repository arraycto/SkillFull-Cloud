// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.user.controller;

import indi.zxiaozhou.skillfull.auth.modules.user.controller.vo.*;
import indi.zxiaozhou.skillfull.auth.modules.user.service.IUserCenterService;
import indi.zxiaozhou.skillfull.corecommon.annotation.AutoLog;
import indi.zxiaozhou.skillfull.corecommon.base.Result;
import indi.zxiaozhou.skillfull.corecommon.constant.UrlPrefix;
import indi.zxiaozhou.skillfull.coremvc.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户中心
 *
 * @author zxiaozhou
 * @date 2020-09-29 17:29
 * @since JDK11
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "UserCenter", description = "用户相关")
@RequestMapping(value = UrlPrefix.AUTH + "/user/center", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserCenterController extends BaseController {
    private final IUserCenterService service;


    @ApiOperation(value = "修改密码@(v1.0.0)", notes = "修改密码")
    @PostMapping(value = "/change/password")
    @AutoLog(note = "修改密码", type = AutoLog.EDIT)
    public Result<String> changePassword(@RequestBody @Valid ChangePasswordVo vo) {
        service.changePassword(vo);
        return ok("修改密码成功,请刷新页面重新登录");
    }


    @ApiOperation(value = "修改用户信息@(v1.0.0)", notes = "修改用户信息")
    @PostMapping(value = "/change/user-info")
    @AutoLog(note = "修改用户信息", type = AutoLog.EDIT)
    public Result<String> changeUserInfo(@RequestBody @Valid ChangeUserInfoVo vo) {
        service.changeUserInfo(vo);
        return ok("修改用户信息成功");
    }


    @ApiOperation(value = "找回密码@(v1.0.0)", notes = "找回密码")
    @PostMapping(value = "/find/password")
    @AutoLog(note = "找回密码", type = AutoLog.EDIT)
    public Result<String> findPassword(@RequestBody @Valid FindPasswordVo vo) {
        service.findPassword(vo);
        return ok("找回密码成功,请返回登录");
    }


    @ApiOperation(value = "修改手机号前验证旧手机验证码@(v1.0.0)", notes = "修改手机号前验证旧手机验证码")
    @PostMapping(value = "/check/old-phone-sms")
    @AutoLog(note = "修改手机号前验证旧手机验证码")
    public Result<String> checkOldUserPhoneSms(@RequestBody @Valid CheckOldUserPhoneVo vo) {
        service.checkOldUserPhoneSms(vo);
        return ok("验证码正确");
    }


    @ApiOperation(value = "修改手机号@(v1.0.0)", notes = "修改手机号")
    @PostMapping(value = "/change/user-phone")
    @AutoLog(note = "修改手机号", type = AutoLog.EDIT)
    public Result<String> changeUserPhone(@RequestBody @Valid ChangeUserPhoneVo vo) {
        service.changeUserPhone(vo);
        return ok("修改手机号码成功");
    }


    @ApiOperation(value = "绑定手机号@(v1.0.0)", notes = "绑定手机号")
    @PostMapping(value = "/bind/user-phone")
    @AutoLog(note = "绑定手机号", type = AutoLog.EDIT)
    public Result<String> bindUserPhone(@RequestBody @Valid BindUserPhoneVo vo) {
        service.bindUserPhone(vo);
        return ok("绑定手机号码成功");
    }
}
