// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.coremvc.base.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entity基类
 *
 * @author zxiaozhou
 * @date 2020-06-22 14:59
 * @since JDK11
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -7242240142513530183L;

    @ApiModelProperty(name = "createUserId", value = "创建人用户id")
    @TableField(fill = FieldFill.INSERT)
    private String createUserId;

    @ApiModelProperty(name = "createUserName", value = "创建用户姓名")
    @TableField(fill = FieldFill.INSERT)
    private String createUserName;

    @ApiModelProperty(name = "createTime", value = "创建日期")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(name = "updateUserId", value = "更新人用户id")
    @TableField(fill = FieldFill.UPDATE)
    private String updateUserId;

    @ApiModelProperty(name = "updateUserName", value = "更新用户姓名")
    @TableField(fill = FieldFill.UPDATE)
    private String updateUserName;

    @ApiModelProperty(name = "updateTime", value = "更新日期")
    @TableField(fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(name = "delFlag", value = "删除标识0-正常,1-已删除")
    @TableLogic
    private Integer delFlag;
}
