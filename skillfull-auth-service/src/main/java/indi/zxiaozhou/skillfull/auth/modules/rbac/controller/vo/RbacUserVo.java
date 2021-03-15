// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.rbac.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotBlankOrNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * 用户表添加或修改Request
 *
 * @author zxiaozhou
 * @date 2020-09-26 17:16:16
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class RbacUserVo implements Serializable {
    private static final long serialVersionUID = -95485480358197097L;

    @ApiModelProperty(name = "userName", value = "用户名", required = true)
    @NotBlankOrNull(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty(name = "nickName", value = "用户昵称")
    private String nickName;

    @ApiModelProperty(name = "realName", value = "真实姓名", required = true)
    @NotBlankOrNull(message = "真实姓名不能为空")
    private String realName;

//    @ApiModelProperty(name = "password", value = "密码", required = true)
//    @NotBlankOrNull(message = "密码不能为空")
//    private String password;

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

    @ApiModelProperty(name = "userStatus", value = "状态:0-未激活,1-正常,2-冻结,默认1", required = true)
    @NotBlankOrNull(message = "状态不能为空")
    @Min(value = 0, message = "状态只能为0、1、2")
    @Max(value = 2, message = "状态只能为0、1、2")
    private Integer userStatus;

    @ApiModelProperty(name = "workNo", value = "工号，唯一键")
    private String workNo;

    @ApiModelProperty(name = "telephone", value = "座机号")
    private String telephone;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "orgInfo", value = "所属机构")
    @Valid
    private OrgInfoVo orgInfo;

    @ApiModelProperty(name = "positionInfos", value = "职位")
    @Valid
    private List<PositionInfoVo> positionInfos;

    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    @EqualsAndHashCode
    @ApiModel
    public static class OrgInfoVo {
        @ApiModelProperty(name = "orgId", value = "机构id", required = true)
        @NotBlank(message = "机构id不能为空")
        private String orgId;

        @ApiModelProperty(name = "orgCode", value = "组织编码", required = true)
        @NotBlank(message = "组织编码不能为空")
        private String orgCode;

        @ApiModelProperty(name = "orgSysCode", value = "组织编码(系统)", required = true)
        @NotBlank(message = "组织编码(系统)不能为空")
        private String orgSysCode;

        @ApiModelProperty(name = "orgName", value = "组织名称", required = true)
        @NotBlank(message = "组织名称不能为空")
        private String orgName;
    }

    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    @EqualsAndHashCode
    @ApiModel
    public static class PositionInfoVo {
        @ApiModelProperty(name = "positionId", value = "职位id", required = true)
        @NotBlank(message = "职位id不能为空")
        private String positionId;

        @ApiModelProperty(name = "positionCode", value = "职位编码", required = true)
        @NotBlank(message = "职位编码不能为空")
        private String positionCode;

        @ApiModelProperty(name = "positionName", value = "职位名称", required = true)
        @NotBlank(message = "职位名称不能为空")
        private String positionName;

        @ApiModelProperty(name = "positionRank", value = "职级", required = true)
        @NotNull(message = "职级不能为空")
        private Integer positionRank;
    }

}