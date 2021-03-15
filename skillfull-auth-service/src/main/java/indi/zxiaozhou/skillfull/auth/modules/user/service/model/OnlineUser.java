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

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class OnlineUser implements Serializable {
    private static final long serialVersionUID = 3826720098300941717L;
    @ApiModelProperty(name = "loginDate", value = "登录时间")
    private Date loginDate;

    @ApiModelProperty(name = "token", value = "token")
    private String token;

    @ApiModelProperty(name = "loginIp", value = "登录ip")
    private String loginIp;

    @ApiModelProperty(name = "equipmentInfo", value = "设备信息")
    private String equipmentInfo;

    @ApiModelProperty(name = "address", value = "登录地址")
    private String address;

    @ApiModelProperty(name = "userId", value = "用户id")
    private String userId;

    @ApiModelProperty(name = "userName", value = "用户名")
    private String userName;

    @ApiModelProperty(name = "realName", value = "真实姓名")
    private String realName;

    @ApiModelProperty(name = "avatar", value = "头像")
    private String avatar;

    @ApiModelProperty(name = "birthday", value = "生日")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    @ApiModelProperty(name = "sex", value = "性别:0-默认未知,1-男,2-女,默认0")
    private Integer sex;

    @ApiModelProperty(name = "email", value = "邮件")
    private String email;

    @ApiModelProperty(name = "phone", value = "电话号码")
    private String phone;
}
