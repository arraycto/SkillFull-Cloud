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
 * 服务管理(ManageService)Entity
 *
 * @author zxiaozhou
 * @date 2021-02-12 20:29:28
 * @since JDK1.8
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_manage_service")
public class ManageServiceEntity extends BaseEntity {
    private static final long serialVersionUID = -67690461135583795L;

    @TableId
    private String serviceId;

    @ApiModelProperty(name = "swaggerTag", value = "swagger tag显示名称")
    private String swaggerTag;

    @ApiModelProperty(name = "swaggerDocUri", value = "swagger doc uri,即凭借上网关ip信息就能访问的uri")
    private String swaggerDocUri;

    @ApiModelProperty(name = "serviceName", value = "服务名,注册中心名称")
    private String serviceName;

    @ApiModelProperty(name = "isLoadBalancer", value = "是否负载均衡器:0-不是,1-是，默认0。选择均衡器时监听信息才可以使用,同时该字段与路由对应")
    private Integer isLoadBalancer;

    @ApiModelProperty(name = "subscribeChange", value = "是否监听服务变化:0-不订阅,1-订阅,默认0")
    private Integer subscribeChange;

    @ApiModelProperty(name = "noticeChange", value = "是否发送变化通知:0-不通知,1-通知。默认0")
    private Integer noticeChange;

    @ApiModelProperty(name = "noticeType", value = "通知类型:0-邮件,1-短信,2-微信消息，当选择监听服务变化并且通知时必填")
    private Integer noticeType;

    @ApiModelProperty(name = "serviceState", value = "服务状态:0-禁用,1-启用。默认0")
    private Integer serviceState;

    @ApiModelProperty(name = "noticeTemplateId", value = "通知模板id，当选择监听服务变化并且通知时必填")
    private String noticeTemplateId;

    @ApiModelProperty(name = "headUserName", value = "负责人姓名，当选择监听服务变化并且通知时必填")
    private String headUserName;

    @ApiModelProperty(name = "headUserId", value = "负责人用户id，当选择监听服务变化并且通知时必填")
    private String headUserId;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "uniqueHelp", value = "唯一索引帮助字段,默认1，如果删除该值为主键")
    private String uniqueHelp;

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