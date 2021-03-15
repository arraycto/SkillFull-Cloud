// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.manage.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 路由条件查询Request
 *
 * @author zxiaozhou
 * @date 2020-12-31 23:08:51
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel
public class ManageRouteQueryVo implements Serializable {
    private static final long serialVersionUID = -19925857451326598L;

    @ApiModelProperty(name = "routeId", value = "路由id")
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