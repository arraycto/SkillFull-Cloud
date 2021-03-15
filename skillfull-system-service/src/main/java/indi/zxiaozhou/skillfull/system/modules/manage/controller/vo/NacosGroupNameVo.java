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

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 所属组名 vo
 *
 * @author zxiaozhou
 * @date 2020-10-11 16:38
 * @since JDK1.8
 */
@Data
public class NacosGroupNameVo implements Serializable {
    private static final long serialVersionUID = 8282161660745482124L;

    @ApiModelProperty(name = "groupName", value = "所属组名")
    private String groupName;
}
