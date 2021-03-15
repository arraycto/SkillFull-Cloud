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
 * 数据字典表分页查询Response
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:25:18
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class CommonDictPageDto implements Serializable {
    private static final long serialVersionUID = 656946092026147735L;

    @ApiModelProperty(name = "dictId", value = "字典id")
    private String dictId;

    @ApiModelProperty(name = "dictName", value = "字典名称")
    private String dictName;

    @ApiModelProperty(name = "dictCode", value = "字典编码")
    private String dictCode;

    @ApiModelProperty(name = "dictType", value = "字典类型：0-字符串,1-数字。默认0")
    private String dictType;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "createUserName", value = "创建用户姓名")
    private String createUserName;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(name = "dictStatus", value = "字典状态：1启用，0禁用，默认0")
    private Integer dictStatus;
}