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

import java.io.Serializable;
import java.util.Date;

/**
 * 在线用户信息
 *
 * @author zxiaozhou
 * @date 2020-07-19 12:46
 * @since JDK11
 */
@Data
public class QueryOnlineUser implements Serializable {
    private static final long serialVersionUID = 3826720098300941717L;
    @ApiModelProperty(name = "loginDate", value = "登录开始时间")
    private Date startTime;

    @ApiModelProperty(name = "endTime", value = "登录结束时间")
    private Date endTime;

    @ApiModelProperty(name = "userName", value = "用户名")
    private String userName;

    @ApiModelProperty(name = "realName", value = "真实姓名")
    private String realName;

    @ApiModelProperty(name = "email", value = "邮件")
    private String email;

    @ApiModelProperty(name = "phone", value = "电话号码")
    private String phone;
}
