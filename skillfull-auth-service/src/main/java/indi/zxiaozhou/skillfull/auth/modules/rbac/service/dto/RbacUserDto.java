// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto;

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
import java.util.Collections;
import java.util.List;

/**
 * 用户表查询Response
 *
 * @author zxiaozhou
 * @date 2020-09-26 17:16:16
 * @since JDK11
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class RbacUserDto implements Serializable {
    private static final long serialVersionUID = 854437047595834340L;

    @ApiModelProperty(name = "userId", value = "用户id")
    private String userId;

    @ApiModelProperty(name = "userName", value = "用户名")
    private String userName;

    @ApiModelProperty(name = "nickName", value = "用户昵称")
    private String nickName;

    @ApiModelProperty(name = "realName", value = "真实姓名")
    private String realName;

    @ApiModelProperty(name = "avatar", value = "头像")
    private String avatar;

    @ApiModelProperty(name = "birthday", value = "生日")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate birthday;

    @ApiModelProperty(name = "sex", value = "性别:0-默认未知,1-男,2-女,默认0")
    private Integer sex;

    @ApiModelProperty(name = "email", value = "邮件")
    private String email;

    @ApiModelProperty(name = "phone", value = "电话号码")
    private String phone;

    @ApiModelProperty(name = "orgIds", value = "负责部门")
    private String orgIds;

    @ApiModelProperty(name = "userStatus", value = "状态:0-未激活,1-正常,2-冻结,默认1")
    private Integer userStatus;

    @ApiModelProperty(name = "workNo", value = "工号，唯一键")
    private String workNo;

    @ApiModelProperty(name = "telephone", value = "座机号")
    private String telephone;

    @ApiModelProperty(name = "currentLoginTime", value = "最近登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime currentLoginTime;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(name = "orgInfo", value = "所属机构")
    private OrgInfoDto orgInfo;

    @ApiModelProperty(name = "positionInfos", value = "职位")
    private List<PositionInfoDto> positionInfos = Collections.emptyList();

    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    @EqualsAndHashCode
    @ApiModel
    public static class OrgInfoDto {
        @ApiModelProperty(name = "orgId", value = "机构id")
        private String orgId;

        @ApiModelProperty(name = "orgCode", value = "组织编码")
        private String orgCode;

        @ApiModelProperty(name = "orgSysCode", value = "组织编码(系统)")
        private String orgSysCode;

        @ApiModelProperty(name = "orgName", value = "组织名称")
        private String orgName;

        @ApiModelProperty(name = "autoBind", value = "绑定方式:0-手动,1-自动。默认0")
        private Integer autoBind;

        @ApiModelProperty(name = "orgType", value = "组织机构类型：1-公司,2-部门")
        private Integer orgType;
    }

    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    @EqualsAndHashCode
    @ApiModel
    public static class PositionInfoDto {
        @ApiModelProperty(name = "positionId", value = "职位id")
        private String positionId;

        @ApiModelProperty(name = "positionCode", value = "职位编码")
        private String positionCode;

        @ApiModelProperty(name = "positionName", value = "职位名称")
        private String positionName;

        @ApiModelProperty(name = "positionRank", value = "职级")
        private Integer positionRank;

        @ApiModelProperty(name = "autoBind", value = "绑定方式:0-手动,1-自动。默认0")
        private Integer autoBind;
    }

}