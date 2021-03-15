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
public class ServiceStatDto implements Serializable {
    private static final long serialVersionUID = 2784324896203301262L;

    @ApiModelProperty(name = "totalService", value = "总服务数")
    private Integer totalService = 0;

    @ApiModelProperty(name = "healthyInstanceCount", value = "健康实例数")
    private Integer healthyInstanceCount = 0;

    @ApiModelProperty(name = "noHealthyInstanceCount", value = "不健康实例数")
    private Integer noHealthyInstanceCount = 0;
}
