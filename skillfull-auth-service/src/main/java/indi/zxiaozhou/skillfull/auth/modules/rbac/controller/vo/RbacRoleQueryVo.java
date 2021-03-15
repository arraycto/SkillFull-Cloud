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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色表条件查询Request
 *
 * @author zxiaozhou
 * @date 2020-10-08 13:44:03
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel
public class RbacRoleQueryVo implements Serializable {
    private static final long serialVersionUID = -79723127630031510L;

    @ApiModelProperty(name = "roleId", value = "角色id")
    private String roleId;

    @ApiModelProperty(name = "roleName", value = "角色名称")
    private String roleName;

    @ApiModelProperty(name = "roleSysCode", value = "角色系统编码(系统自动创建)")
    private String roleSysCode;

    @ApiModelProperty(name = "roleCode", value = "角色编码")
    private String roleCode;

    @ApiModelProperty(name = "parentRoleId", value = "上级角色id")
    private String parentRoleId;

    @ApiModelProperty(name = "enableDelete", value = "是否可删除:0-不可删除,1-可删除。默认1(用户系统内置数据不可删除)")
    private Integer enableDelete;

    @ApiModelProperty(name = "autoBind", value = "绑定方式:0-手动,1-自动。默认0")
    private Integer autoBind;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "uniqueHelp", value = "唯一索引帮助字段,默认1，如果删除该值为主键")
    private String uniqueHelp;

    @ApiModelProperty(name = "createUserId", value = "创建用户id")
    private String createUserId;

    @ApiModelProperty(name = "createUserName", value = "创建用户姓名")
    private String createUserName;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(name = "updateUserId", value = "更新用户id")
    private String updateUserId;

    @ApiModelProperty(name = "updateUserName", value = "更新用户姓名")
    private String updateUserName;

    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(name = "delFlag", value = "删除状态:0-正常,1-已删除,默认0")
    private Integer delFlag;

}