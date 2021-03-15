// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.common.entity;

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
 * 用户-代理人表(CommonUserAgent)Entity
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:40:28
 * @since JDK1.8
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("auth_common_user_agent")
public class CommonUserAgentEntity extends BaseEntity {
    private static final long serialVersionUID = 648427818064987718L;

    @TableId
    private String agentId;

    @ApiModelProperty(name = "userId", value = "用户名id")
    private String userId;

    @ApiModelProperty(name = "agentUserId", value = "代理人用户id")
    private String agentUserId;

    @ApiModelProperty(name = "agentStartTime", value = "代理开始时间")
    private LocalDateTime agentStartTime;

    @ApiModelProperty(name = "agentEndTime", value = "代理结束时间")
    private LocalDateTime agentEndTime;

    @ApiModelProperty(name = "agentStatus", value = "状态：0-无效，1-有效")
    private String agentStatus;

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