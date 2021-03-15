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

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 服务状态
 *
 * @author zxiaozhou
 * @date 2020-09-13 16:34
 * @since JDK1.8
 */
@Data
public class ServiceInfoDto implements Serializable {
    private static final long serialVersionUID = -3111823557051184286L;

    @ApiModelProperty(name = "serviceName", value = "服务名称")
    private String serviceName;

    @ApiModelProperty(name = "serviceDetails", value = "服务详细信息")
    private List<ServiceDetail> serviceDetails;

    @ApiModelProperty(name = "instanceNum", value = "实例总数")
    private int instanceNum = 0;

    @ApiModelProperty(name = "healthyNum", value = "健康实例数量")
    private int healthyNum = 0;

    @ApiModelProperty(name = "unhealthyNum", value = "不健康数量")
    private int unhealthyNum = 0;


    @Data
    public static class ServiceDetail {
        @ApiModelProperty(name = "healthy", value = "服务监控状态")
        private boolean healthy;

        @ApiModelProperty(name = "instanceId", value = "服务实例id")
        private String instanceId;

        @ApiModelProperty(name = "host", value = "服务ip")
        private String host;

        @ApiModelProperty(name = "port", value = "服务端口")
        private Integer port;

    }
}
