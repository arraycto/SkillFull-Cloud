// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.corecommon.base.model;

import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotBlankOrNull;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotNullSize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * websocket消息model
 *
 * @author zxiaozhou
 * @date 2021-01-08 15:57
 * @since JDK11
 */
@Data
public class WebSocketMsgModel implements Serializable {
    private static final long serialVersionUID = -7991672291592110700L;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 消息数据
     */
    @ApiModelProperty(name = "msgData", value = "消息信息", required = true)
    @NotBlankOrNull(message = "消息信息不能为空")
    private String msgData;


    /**
     * 接收用户,必填
     */
    @ApiModelProperty(name = "userIds", value = "接收用户id", required = true)
    @NotNullSize(message = "接收用户id不能为空")
    private List<String> userIds;
}
