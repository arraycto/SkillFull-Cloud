// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto;

import indi.zxiaozhou.skillfull.corecommon.utils.tree.model.BaseTree;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 权限表查询Response
 *
 * @author zxiaozhou
 * @date 2020-10-06 23:14:39
 * @since JDK11
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel
public class RbacPermissionTreeDto extends BaseTree<RbacPermissionTreeDto> implements Serializable {
    private static final long serialVersionUID = -21159540080828469L;

    @ApiModelProperty(name = "permissionId", value = "权限id")
    private String permissionId;

    @ApiModelProperty(name = "parentId", value = "父id")
    private String parentId;

    @ApiModelProperty(name = "title", value = "菜单名称")
    private String title;

    @ApiModelProperty(name = "path", value = "路径")
    private String path;

    @ApiModelProperty(name = "permissionType", value = "权限类型(0:目录; 1:菜单:2:按钮)")
    private Integer permissionType;

    @ApiModelProperty(name = "permissionStatus", value = "权限状态:0-无效，1-有效,默认0")
    private Integer permissionStatus;
}