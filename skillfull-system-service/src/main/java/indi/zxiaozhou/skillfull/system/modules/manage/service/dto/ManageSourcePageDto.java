// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.manage.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 数据源表分页查询Response
 *
 * @author zxiaozhou
 * @date 2020-11-02 07:58:34
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class ManageSourcePageDto implements Serializable {
    private static final long serialVersionUID = 588582583571505084L;

    @ApiModelProperty(name = "dataSourceId", value = "数据源id")
    private String dataSourceId;

    @ApiModelProperty(name = "dbCode", value = "数据源编码")
    private String dbCode;

    @ApiModelProperty(name = "dbSourceName", value = "数据源名称")
    private String dbSourceName;

    @ApiModelProperty(name = "dbType", value = "数据库类型")
    private String dbType;

    @ApiModelProperty(name = "dbDriver", value = "驱动类")
    private String dbDriver;

    @ApiModelProperty(name = "dbUrl", value = "数据源地址")
    private String dbUrl;

    @ApiModelProperty(name = "dbName", value = "数据库名称")
    private String dbName;

    @ApiModelProperty(name = "dbUsername", value = "用户名")
    private String dbUsername;

    @ApiModelProperty(name = "dbPassword", value = "密码")
    private String dbPassword;

    @ApiModelProperty(name = "createAreaCode", value = "创建区域编码")
    private String createAreaCode;

    @ApiModelProperty(name = "createPositionCode", value = "创建职位编码")
    private String createPositionCode;

    @ApiModelProperty(name = "createOrgSysCode", value = "创建机构系统编码")
    private String createOrgSysCode;

    @ApiModelProperty(name = "createSystemCode", value = "创建系统编码")
    private String createSystemCode;

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

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "delFlag", value = "删除状态:0-正常,1-已删除,默认0")
    private Integer delFlag;

}