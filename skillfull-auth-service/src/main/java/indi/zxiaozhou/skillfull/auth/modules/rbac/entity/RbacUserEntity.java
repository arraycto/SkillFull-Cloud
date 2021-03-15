// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.rbac.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.zxiaozhou.skillfull.coremvc.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 用户表(RbacUser)Entity
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:27:27
 * @since JDK1.8
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("auth_rbac_user")
public class RbacUserEntity extends BaseEntity {
    private static final long serialVersionUID = -14419317533629046L;

    @TableId
    private String userId;

    @ApiModelProperty(name = "userName", value = "用户名")
    private String userName;

    @ApiModelProperty(name = "nickName", value = "用户昵称")
    private String nickName;

    @ApiModelProperty(name = "realName", value = "真实姓名")
    private String realName;

    @ApiModelProperty(name = "password", value = "密码")
    private String password;

    @ApiModelProperty(name = "salt", value = "密码盐")
    private String salt;

    @ApiModelProperty(name = "shortProfile", value = "个人简介")
    private String shortProfile;

    @ApiModelProperty(name = "avatar", value = "头像")
    private String avatar;

    @ApiModelProperty(name = "birthday", value = "生日")
    private LocalDateTime birthday;

    @ApiModelProperty(name = "sex", value = "性别:0-默认未知,1-男,2-女,默认0")
    private Integer sex;

    @ApiModelProperty(name = "email", value = "邮件")
    private String email;

    @ApiModelProperty(name = "isInitialPassword", value = "是否初始密码:0-不是,1-是,默认1")
    private Integer isInitialPassword;

    @ApiModelProperty(name = "phone", value = "电话号码")
    private String phone;

    @ApiModelProperty(name = "currentOrgId", value = "当前登录部门id")
    private String currentOrgId;

    @ApiModelProperty(name = "orgIds", value = "负责部门")
    private String orgIds;

    @ApiModelProperty(name = "userStatus", value = "状态:0-未激活,1-正常,2-冻结,默认1")
    private Integer userStatus;

    @ApiModelProperty(name = "workNo", value = "工号，唯一键")
    private String workNo;

    @ApiModelProperty(name = "telephone", value = "座机号")
    private String telephone;

    @ApiModelProperty(name = "loginFailErrorNum", value = "连续登录错误次数")
    private Integer loginFailErrorNum;

    @ApiModelProperty(name = "currentLoginTime", value = "最近登录时间")
    private LocalDateTime currentLoginTime;

    @ApiModelProperty(name = "uniqueHelp", value = "唯一索引帮助字段,默认1，如果删除该值为主键")
    private String uniqueHelp;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "createAreaCode", value = "创建区域编码")
    private String createAreaCode;

    @ApiModelProperty(name = "createPositionCode", value = "创建职位编码")
    private String createPositionCode;

    @ApiModelProperty(name = "createOrgSysCode", value = "创建机构系统编码")
    private String createOrgSysCode;

    @ApiModelProperty(name = "createSystemCode", value = "创建系统编码")
    private String createSystemCode;

    @ApiModelProperty(name = "createTenantId", value = "创建租户id")
    private String createTenantId;
}