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

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * swagger信息
 *
 * @author zxiaozhou
 * @date 2020-09-14 02:33
 * @since JDK1.8
 */
@Data
@EqualsAndHashCode
public class SwaggerInfoDto {
    /**
     * 服务名
     */
    private String serviceName;

    /**
     * swagger tag显示名称
     */
    private String swaggerTag;

    /**
     * swagger doc uri,即凭借上网关ip信息就能访问的uri
     */
    private String swaggerDocUri;

}
