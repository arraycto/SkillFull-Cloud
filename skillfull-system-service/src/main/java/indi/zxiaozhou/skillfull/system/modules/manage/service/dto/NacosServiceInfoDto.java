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

/**
 * @author zxiaozhou
 * @date 2020-10-11 21:04
 * @since JDK1.8
 */
@Data
public class NacosServiceInfoDto implements Serializable {
    private static final long serialVersionUID = 2784324896203301262L;

    @ApiModelProperty(name = "name", value = "服务名")
    private String name;

    @ApiModelProperty(name = "groupName", value = "分组名称")
    private String groupName;

    @ApiModelProperty(name = "clusterCount", value = "集群数目")
    private Integer clusterCount;

    @ApiModelProperty(name = "ipCount", value = "实例数")
    private Integer ipCount;

    @ApiModelProperty(name = "healthyInstanceCount", value = "健康实例数")
    private Integer healthyInstanceCount;

    @ApiModelProperty(name = "triggerFlag", value = "触发保护阈值")
    private Boolean triggerFlag;
}
