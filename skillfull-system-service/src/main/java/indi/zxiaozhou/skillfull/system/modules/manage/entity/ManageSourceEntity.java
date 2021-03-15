// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.manage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.zxiaozhou.skillfull.coremvc.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 数据源表(ManageSource)Entity
 *
 * @author zxiaozhou
 * @date 2021-02-12 20:29:53
 * @since JDK1.8
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_manage_source")
public class ManageSourceEntity extends BaseEntity {
    private static final long serialVersionUID = 138368021289492577L;

    @TableId
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

    @ApiModelProperty(name = "createTenantId", value = "创建租户id")
    private String createTenantId;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
}