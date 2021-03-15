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

import com.alibaba.nacos.api.naming.pojo.Instance;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 服务实例信息
 *
 * @author zxiaozhou
 * @date 2020-10-11 13:24
 * @since JDK1.8
 */
@Data
public class ServiceInstanceDto implements Serializable {
    private static final long serialVersionUID = 2013577984078828501L;

    @ApiModelProperty(name = "instanceNum", value = "实例总数")
    private int instanceNum = 0;

    @ApiModelProperty(name = "healthyNum", value = "健康实例数量")
    private int healthyNum = 0;

    @ApiModelProperty(name = "unhealthyNum", value = "不健康数量")
    private int unhealthyNum = 0;

    @ApiModelProperty(name = "enabledNum", value = "能否接收请求实例数量")
    private int enabledNum = 0;

    @ApiModelProperty(name = "ephemeralNum", value = "临时实例数量")
    private int ephemeralNum = 0;

    @ApiModelProperty(name = "serviceName", value = "服务名称")
    private String serviceName;

    @ApiModelProperty(name = "instanceMap", value = "服务实例id,服务实例信息")
    @JsonIgnore
    private Map<String, Instance> instanceMap;

    @ApiModelProperty(name = "serviceInstanceDetails", value = "服务实例详细")
    private List<ServiceInstanceDetail> serviceInstanceDetails = Collections.emptyList();


    @Data
    public static class ServiceInstanceDetail implements Serializable {
        private static final long serialVersionUID = 4086735209634588642L;

        @ApiModelProperty(name = "instanceId", value = "实例唯一id")
        private String instanceId;

        @ApiModelProperty(name = "ip", value = "实例ip")
        private String ip;

        @ApiModelProperty(name = "port", value = "实例端口")
        private int port;

        @ApiModelProperty(name = "weight", value = "实例权重")
        private double weight = 1.0D;

        @ApiModelProperty(name = "healthy", value = "实例健康状态")
        private boolean healthy = false;

        @ApiModelProperty(name = "enabled", value = "实例是否接受请求")
        private boolean enabled = true;

        @ApiModelProperty(name = "ephemeral", value = "是否临时实例")
        private boolean ephemeral = true;

        @ApiModelProperty(name = "clusterName", value = "实例集群信息")
        private String clusterName;

        @ApiModelProperty(name = "serviceName", value = "实例服务名")
        private String serviceName;

        @ApiModelProperty(name = "metadata", value = "用户扩展属性")
        private Map<String, String> metadata;
    }
}
