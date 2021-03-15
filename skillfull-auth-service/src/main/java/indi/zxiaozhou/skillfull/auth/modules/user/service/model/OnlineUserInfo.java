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

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 条件查询在线用户信息
 *
 * @author zxiaozhou
 * @date 2020-07-19 18:03
 * @since JDK11
 */
@Data
public class OnlineUserInfo {
    @ApiModelProperty(name = "onlineUsers", value = "在线用户集合")
    private List<OnlineUser> onlineUsers;

    @ApiModelProperty(name = "totalNum", value = "在线用户数量")
    private Long totalNum;
}
