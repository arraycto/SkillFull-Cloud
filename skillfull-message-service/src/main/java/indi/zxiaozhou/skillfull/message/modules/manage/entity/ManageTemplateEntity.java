// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.modules.manage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.zxiaozhou.skillfull.message.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 消息模板(ManageTemplate)Entity
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:57:50
 * @since JDK1.8
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("msg_manage_template")
public class ManageTemplateEntity extends BaseEntity {
    private static final long serialVersionUID = 314226524460214165L;

    @TableId
    private String templateId;

    @ApiModelProperty(name = "templateName", value = "模板名称")
    private String templateName;

    @ApiModelProperty(name = "templateCode", value = "模板code")
    private String templateCode;

    @ApiModelProperty(name = "templateType", value = "模板类型")
    private String templateType;

    @ApiModelProperty(name = "templateContent", value = "模板内容")
    private String templateContent;

    @ApiModelProperty(name = "templateTestJson", value = "模板测试json")
    private String templateTestJson;

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