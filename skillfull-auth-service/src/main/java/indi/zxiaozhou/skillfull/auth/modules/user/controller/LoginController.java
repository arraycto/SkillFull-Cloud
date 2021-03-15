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

import indi.zxiaozhou.skillfull.auth.modules.user.controller.vo.OnlineUserPageVo;
import indi.zxiaozhou.skillfull.auth.modules.user.service.ILoginService;
import indi.zxiaozhou.skillfull.auth.modules.user.service.dto.OnlineUserInfoDto;
import indi.zxiaozhou.skillfull.auth.modules.user.service.dto.RouterInfoDto;
import indi.zxiaozhou.skillfull.auth.modules.user.service.dto.UserInfoDto;
import indi.zxiaozhou.skillfull.auth.modules.user.service.model.TokenInfo;
import indi.zxiaozhou.skillfull.auth.security.model.LoginPicture;
import indi.zxiaozhou.skillfull.auth.security.model.LoginSms;
import indi.zxiaozhou.skillfull.corecommon.annotation.AutoLog;
import indi.zxiaozhou.skillfull.corecommon.base.Result;
import indi.zxiaozhou.skillfull.corecommon.constant.UrlPrefix;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.PathNotBlank;
import indi.zxiaozhou.skillfull.coremvc.annotation.Anonymous;
import indi.zxiaozhou.skillfull.coremvc.base.controller.BaseController;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static indi.zxiaozhou.skillfull.corecommon.constant.CommonConstant.*;

/**
 * 登录相关
 *
 * @author zxiaozhou
 * @date 2020-06-30 15:29
 * @since JDK11
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "Login", value = "LoginApi", description = "登录")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController extends BaseController {
    private final ILoginService service;

    @ApiOperation(value = "获取用户权限信息@(v1.0.0)", notes = "获取用户权限信息")
    @GetMapping(value = UrlPrefix.AUTH + "/user/login/user-info")
    @AutoLog(note = "获取用户权限信息", type = AutoLog.QUERY)
    public Result<UserInfoDto> getUserInfo() {
        return ok(service.getUserInfo());
    }


    @ApiOperation(value = "获取用户路由菜单信息@(v1.0.0)", notes = "获取用户路由菜单信息")
    @GetMapping(value = UrlPrefix.AUTH + "/user/login/router-info")
    public Result<List<RouterInfoDto>> getRouterInfo() {
        return ok(service.getRouterInfo());
    }


    @ApiOperation(value = "分页查询在线用户信息@(v1.0.0)", notes = "分页查询在线用户信息")
    @PostMapping(value = UrlPrefix.AUTH + "/user/login/page-online-user")
    public Result<PageDto<OnlineUserInfoDto>> selectOnlinePage(@RequestBody OnlineUserPageVo vo) {
        return ok(service.selectOnlinePage(vo));
    }


    @ApiOperation(value = "踢用户下线@(v1.0.0)", notes = "踢用户下线(如果需要服务器主动使用户下线,需要消息服务支持)")
    @GetMapping(value = UrlPrefix.AUTH + "/user/login/kick-out/{loginUnique}")
    @ApiImplicitParam(paramType = "path", value = "登录唯一标识", name = "loginUnique", required = true)
    public Result<String> kickOut(@PathVariable(required = false) @PathNotBlank(message = "登录唯一标识不能为空") String loginUnique) {
        service.kickOut(loginUnique);
        return ok("踢除用户下线成功");
    }


    @ApiOperation(value = "账号密码登录@(v1.0.0)", notes = "账号密码登录(这是一个空方法，实际登录交由security处理)")
    @PostMapping(value = PASSWORD_LOGIN)
    @Anonymous
    public Result<TokenInfo> loginPicture(@RequestBody @Valid LoginPicture vo) {
        return ok(new TokenInfo());
    }


    @ApiOperation(value = "短信验证码登录@(v1.0.0)", notes = "短信验证码登录(这是一个空方法，实际登录交由security处理)")
    @PostMapping(value = SMS_LOGIN)
    @Anonymous
    public Result<TokenInfo> loginSmsCode(@RequestBody @Valid LoginSms vo) {
        return ok(new TokenInfo());
    }


    @ApiOperation(value = "退出登录@(v1.0.0)", notes = "退出登录(这是一个空方法，实际登录交由security处理)")
    @GetMapping(value = LOGIN_OUT)
    @Anonymous
    public Result<String> loginOut() {
        return ok("退出登录成功");
    }


    @ApiOperation(value = "请求刷新token@(v1.0.0)", notes = "请求刷新token")
    @GetMapping(value = UrlPrefix.AUTH + "/user/login/refresh-token")
    public Result<String> refreshToken() {
        return ok(service.refreshToken());
    }
}
