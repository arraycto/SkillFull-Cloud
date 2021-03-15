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
import indi.zxiaozhou.skillfull.auth.modules.user.controller.vo.OnlineUserPageVo;
import indi.zxiaozhou.skillfull.auth.modules.user.service.dto.OnlineUserInfoDto;
import indi.zxiaozhou.skillfull.auth.modules.user.service.dto.RouterInfoDto;
import indi.zxiaozhou.skillfull.auth.modules.user.service.dto.UserInfoDto;
import indi.zxiaozhou.skillfull.auth.modules.user.service.model.LoginPermissionInfo;
import indi.zxiaozhou.skillfull.corecommon.base.model.UserAndAuthModel;
import indi.zxiaozhou.skillfull.coremvc.base.service.BaseService;
import indi.zxiaozhou.skillfull.coremvc.base.service.dto.PageDto;

import java.util.List;

/**
 * 登录相关业务层接口
 *
 * @author zxiaozhou
 * @date 2020-07-17 20:21
 * @since JDK11
 */
public interface ILoginService extends BaseService<RbacUserEntity> {

    /**
     * 获取菜单路由信息
     *
     * @return List<RouterInfoDto> ${@link List<RouterInfoDto>}
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-27 01:29
     */
    List<RouterInfoDto> getRouterInfo() throws RuntimeException;


    /**
     * 获取用户权限信息
     *
     * @return UserInfoDto ${@link UserInfoDto}
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-09-27 01:30
     */
    UserInfoDto getUserInfo() throws RuntimeException;


    /**
     * 获取登录用户信息
     *
     * @param rbacUserEntity ${@link RbacUserEntity} 用户信息
     * @param orgId          ${@link String} 部门id
     * @param systemId       ${@link String} 系统id
     * @return LoginPermissionInfo ${@link LoginPermissionInfo}
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2020-07-01 03:27
     */
    UserAndAuthModel getPermissionInfo(RbacUserEntity rbacUserEntity, String orgId, String systemId) throws RuntimeException;


    /**
     * 刷新token
     *
     * @return String ${@link String}
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-01-26 09:31
     */
    String refreshToken() throws RuntimeException;


    /**
     * 分页查询在线用户信息
     *
     * @param vo ${@link OnlineUserPageVo}
     * @return PageDto<OnlineUserInfoDto> ${@link PageDto<OnlineUserInfoDto>}
     * @author zxiaozhou
     * @date 2021-02-25 17:24
     */
    PageDto<OnlineUserInfoDto> selectOnlinePage(OnlineUserPageVo vo);


    /**
     * 踢用户下线
     *
     * @param loginUnique ${@link String} 登录唯一标识
     * @author zxiaozhou
     * @date 2021-02-25 17:49
     */
    void kickOut(String loginUnique);
}
