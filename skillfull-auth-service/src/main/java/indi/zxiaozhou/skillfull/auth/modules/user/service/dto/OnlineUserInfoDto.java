// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.user.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户在线信息
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
@ApiModel
public class OnlineUserInfoDto implements Serializable {
    private static final long serialVersionUID = -5725052195449698245L;

    @ApiModelProperty(name = "userName", value = "用户id")
    private String userId;

    @ApiModelProperty(name = "loginUnique", value = "登录唯一标识")
    private String loginUnique;

    @ApiModelProperty(name = "userName", value = "用户名")
    private String userName;

    @ApiModelProperty(name = "nickName", value = "用户昵称")
    private String nickName;

    @ApiModelProperty(name = "realName", value = "真实姓名")
    private String realName;

    @ApiModelProperty(name = "userStatus", value = "状态:0-未激活,1-正常,2-冻结,默认1")
    private Integer userStatus;

    @ApiModelProperty(name = "loginFailErrorNum", value = "连续登录错误次数")
    private Integer loginFailErrorNum;

    @ApiModelProperty(name = "avatar", value = "头像")
    private String avatar;

    @ApiModelProperty(name = "birthday", value = "生日")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate birthday;

    @ApiModelProperty(name = "shortProfile", value = "个人简介")
    private String shortProfile;

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

    @ApiModelProperty(name = "currentDepartCode", value = "当前部门code")
    private String currentDepartCode;

    @ApiModelProperty(name = "currentAreaId", value = "当前区域id(即区域编码)")
    private String currentAreaId;

    @ApiModelProperty(name = "currentAreaName", value = "当前区域名称名称")
    private String currentAreaName;

    @ApiModelProperty(name = "currentWholeName", value = "当前区域完整名称")
    private String currentWholeName;

    @ApiModelProperty(name = "currentPositionId", value = "当前职位id")
    private String currentPositionId;

    @ApiModelProperty(name = "currentPositionCode", value = "当前职位名称")
    private String currentPositionCode;

    @ApiModelProperty(name = "currentPositionName", value = "当前职位名称")
    private String currentPositionName;

    @ApiModelProperty(name = "currentSystemId", value = "当前系统id")
    private String currentSystemId;

    @ApiModelProperty(name = "currentSystemName", value = "当前系统名称")
    private String currentSystemName;

    @ApiModelProperty(name = "currentSystemCode", value = "当前系统编码")
    private String currentSystemCode;

    @ApiModelProperty(name = "orgIds", value = "负责部门")
    private String orgIds;

    @ApiModelProperty(name = "loginType", value = "登录类型:0-单设备登录,1-多设备登录")
    private Integer loginType;

    @ApiModelProperty(name = "loginIp", value = "登录ip")
    private String loginIp;

    @ApiModelProperty(name = "loginEquipment", value = "最近登录设备信息")
    private String loginEquipment;

    @ApiModelProperty(name = "loginTime", value = "登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime loginTime;

    @ApiModelProperty(name = "currentRefreshTokenTime", value = "最近一次刷新token时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime currentRefreshTokenTime;
}

