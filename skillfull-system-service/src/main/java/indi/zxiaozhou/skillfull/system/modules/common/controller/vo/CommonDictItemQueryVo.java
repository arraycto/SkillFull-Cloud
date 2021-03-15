// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.common.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 数据字典配置项表条件查询Request
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:25:25
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel
public class CommonDictItemQueryVo implements Serializable {
    private static final long serialVersionUID = 478308547266973115L;

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

    @ApiModelProperty(name = "createUserId", value = "创建用户id")
    private String createUserId;

    @ApiModelProperty(name = "createUserName", value = "创建用户姓名")
    private String createUserName;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(name = "updateUserId", value = "更新用户id")
    private String updateUserId;

    @ApiModelProperty(name = "updateUserName", value = "更新用户姓名")
    private String updateUserName;

    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "delFlag", value = "删除状态:0-正常,1-已删除,默认0")
    private Integer delFlag;

}