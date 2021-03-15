// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.common.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 数据字典配置项表分页查询Response
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:25:25
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class CommonDictItemPageDto implements Serializable {
    private static final long serialVersionUID = -82769428547369081L;

    @ApiModelProperty(name = "itemId", value = "字典项id")
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

    @ApiModelProperty(name = "itemStatus", value = "字典项状态：1启用，0不启用，默认0")
    private Integer itemStatus;

    @ApiModelProperty(name = "createUserName", value = "创建用户姓名")
    private String createUserName;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
}