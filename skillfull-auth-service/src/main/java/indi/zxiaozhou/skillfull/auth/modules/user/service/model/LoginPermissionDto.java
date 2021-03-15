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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
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
@AllArgsConstructor
public class LoginPermissionDto implements Serializable {
    private static final long serialVersionUID = -5725052195449698245L;

    @ApiModelProperty(name = "auths", value = "按钮权限信息")
    private Set<String> auths = Collections.emptySet();

    @ApiModelProperty(name = "menus", value = "用户菜单信息")
    private List<LoginPermissionInfo.Menu> menus = Collections.emptyList();

    @ApiModelProperty(name = "userInfo", value = "用户信息")
    private TokenUserInfo.UserInfo userInfo;

}

