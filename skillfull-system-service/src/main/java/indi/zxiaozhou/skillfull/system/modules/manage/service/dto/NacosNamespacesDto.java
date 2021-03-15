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
 * @date 2020-10-11 21:08
 * @since JDK1.8
 */
@Data
public class NacosNamespacesDto implements Serializable {

    private static final long serialVersionUID = -625547796053966768L;

    @ApiModelProperty(name = "namespace", value = "命名空间ID")
    private String namespace;

    @ApiModelProperty(name = "namespaceShowName", value = "命名空间名称")
    private String namespaceShowName;

    @ApiModelProperty(name = "quota", value = "quota")
    private Integer quota;

    @ApiModelProperty(name = "configCount", value = "配置数")
    private Integer configCount;

    @ApiModelProperty(name = "type", value = "type")
    private Integer type;
}
