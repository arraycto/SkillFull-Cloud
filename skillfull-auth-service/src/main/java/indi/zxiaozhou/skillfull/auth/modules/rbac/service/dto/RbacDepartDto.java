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
 * 组织表查询Response
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:37:18
 * @since JDK11
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class RbacDepartDto implements Serializable {
    private static final long serialVersionUID = 184363207993386273L;

    @ApiModelProperty(name = "orgId", value = "组织id")
    private String orgId;

    @ApiModelProperty(name = "parentId", value = "父级组织id")
    private String parentId;

    @ApiModelProperty(name = "orgName", value = "组织名称")
    private String orgName;

    @ApiModelProperty(name = "orgNameEn", value = "英文名")
    private String orgNameEn;

    @ApiModelProperty(name = "orgNameAbbr", value = "缩写")
    private String orgNameAbbr;

    @ApiModelProperty(name = "orgOrder", value = "排序")
    private Integer orgOrder;

    @ApiModelProperty(name = "orgType", value = "组织机构类型：1-公司,2-部门")
    private Integer orgType;

    @ApiModelProperty(name = "orgSysCode", value = "组织机构编码")
    private String orgSysCode;

    @ApiModelProperty(name = "orgStatus", value = "组织状态：0-无效，1-有效，默认0")
    private Integer orgStatus;

    @ApiModelProperty(name = "autoBind", value = "绑定方式:0-手动,1-自动。默认0")
    private Integer autoBind;

    @ApiModelProperty(name = "phone", value = "手机号")
    private String phone;

    @ApiModelProperty(name = "fax", value = "传真")
    private String fax;

    @ApiModelProperty(name = "address", value = "地址")
    private String address;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "createUserName", value = "创建用户姓名")
    private String createUserName;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}