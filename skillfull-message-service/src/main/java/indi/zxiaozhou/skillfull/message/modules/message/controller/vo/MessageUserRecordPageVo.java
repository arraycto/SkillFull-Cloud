// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.modules.message.controller.vo;

import indi.zxiaozhou.skillfull.message.core.base.controller.vo.BasePageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 用户消息记录分页查询Request
 *
 * @author zxiaozhou
 * @date 2021-01-26 16:48:19
 * @since JDK11
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel
public class MessageUserRecordPageVo extends BasePageVo {
    private static final long serialVersionUID = -11316623778570933L;

    @ApiModelProperty(name = "readStatus", value = "阅读状态:0-未阅读,1-阅读。默认0")
    private Integer readStatus;

    @ApiModelProperty(name = "msgType", value = "消息类型:0-系统公告,1-代办事项")
    private Integer msgType;

    @ApiModelProperty(name = "userId", value = "消息用户id", hidden = true)
    private String userId;
}