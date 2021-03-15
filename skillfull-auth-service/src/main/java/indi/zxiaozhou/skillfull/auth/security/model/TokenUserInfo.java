// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.security.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 登录响应token信息
 *
 * @author zxiaozhou
 * @date 2020-06-29 14:33
 * @since JDK11
 */
@Data
public class TokenUserInfo implements Serializable {
    private static final long serialVersionUID = 783443996779878395L;

    @ApiModelProperty(name = "token", value = "token")
    private String token;

    @ApiModelProperty(name = "userInfo", value = "用户信息")
    private UserInfo userInfo;

    @ApiModelProperty(name = "roles", value = "角色信息")
    private Set<String> roles;


    @Data
    public static class UserInfo implements Serializable {
        private static final long serialVersionUID = -7216984493036628645L;

        @ApiModelProperty(name = "userName", value = "用户id")
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

        @ApiModelProperty(name = "currentDepartId", value = "当前部门id")
        private String currentDepartId;

        @ApiModelProperty(name = "currentDepartName", value = "当前部门名称")
        private String currentDepartName;

        @ApiModelProperty(name = "orgIds", value = "负责部门")
        private String orgIds;
    }

}
