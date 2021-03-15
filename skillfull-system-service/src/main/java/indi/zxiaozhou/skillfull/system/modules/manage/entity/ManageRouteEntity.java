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
 * 路由(ManageRoute)Entity
 *
 * @author zxiaozhou
 * @date 2021-02-12 20:27:49
 * @since JDK1.8
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_manage_route")
public class ManageRouteEntity extends BaseEntity {
    private static final long serialVersionUID = -66778532657086283L;

    @TableId
    private String routeId;

    @ApiModelProperty(name = "routeCode", value = "路由编码(唯一)")
    private String routeCode;

    @ApiModelProperty(name = "serviceId", value = "服务id")
    private String serviceId;

    @ApiModelProperty(name = "routeName", value = "路由名称")
    private String routeName;

    @ApiModelProperty(name = "url", value = "路由url地址,当选择非负载均衡器时必填")
    private String url;

    @ApiModelProperty(name = "isLoadBalancer", value = "是否负载均衡器:0-不是,1-时，默认0。选择均衡器时服务名必填，url不填，与服务对应")
    private Integer isLoadBalancer;

    @ApiModelProperty(name = "loadBalancerType", value = "负载均衡器类型:0-普通,1-ws,2-wss")
    private Integer loadBalancerType;

    @ApiModelProperty(name = "metadataJson", value = "路由元数据,数据库json存储,入库前转为字符串")
    private String metadataJson;

    @ApiModelProperty(name = "serviceName", value = "服务名,当选择负载均衡器时使用必填")
    private String serviceName;

    @ApiModelProperty(name = "routeOrder", value = "路由排序,越小越靠前，默认0")
    private Integer routeOrder;

    @ApiModelProperty(name = "routeState", value = "路由状态:0-禁用,1-启用。默认0")
    private Integer routeState;

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