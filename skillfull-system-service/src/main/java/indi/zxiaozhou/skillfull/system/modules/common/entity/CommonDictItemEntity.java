// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.common.entity;

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
 * 数据字典配置项表(CommonDictItem)Entity
 *
 * @author zxiaozhou
 * @date 2021-02-12 20:26:57
 * @since JDK1.8
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_common_dict_item")
public class CommonDictItemEntity extends BaseEntity {
    private static final long serialVersionUID = 127826571553333406L;

    @TableId
    private String itemId;

    @ApiModelProperty(name = "dictId", value = "字典id")
    private String dictId;

    @ApiModelProperty(name = "itemText", value = "字典项名称")
    private String itemText;

    @ApiModelProperty(name = "itemValue", value = "字典项值")
    private String itemValue;

    @ApiModelProperty(name = "dictCode", value = "字典编码")
    private String dictCode;

    @ApiModelProperty(name = "sortOrder", value = "排序,越小越靠前,默认0")
    private Integer sortOrder;

    @ApiModelProperty(name = "itemStatus", value = "字典项状态：1启用，0禁用，默认0")
    private Integer itemStatus;

    @ApiModelProperty(name = "uniqueHelp", value = "唯一索引帮助字段,默认1，如果删除改值未主键")
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

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
}