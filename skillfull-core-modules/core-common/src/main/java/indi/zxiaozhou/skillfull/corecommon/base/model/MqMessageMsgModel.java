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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 业务消息
 *
 * @author zxiaozhou
 * @date 2021-01-14 01:14
 * @since JDK11
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class MqMessageMsgModel implements Serializable {
    private static final long serialVersionUID = 2718987608915965865L;

    @ApiModelProperty(name = "msg", value = "消息内容", required = true)
    @NotBlank(message = "消息内容不能为空")
    private String msg;

    @ApiModelProperty(name = "type", value = "消息类型:1-一对一,2-广播,3-更新token，具体看MsgType", required = true)
    @NotNull(message = "消息类型不能为空")
    @Min(value = 1, message = "消息类型只能为1、2")
    @Min(value = 2, message = "消息类型只能为1、2")
    private Integer type;

    @ApiModelProperty(name = "sendUserId", value = "发送人用户id")
    private String sendUserId;

    @ApiModelProperty(name = "sendUserName", value = "发送人用户姓名")
    private String sendUserName;

    @ApiModelProperty(name = "businessType", value = "业务数据标识")
    private String businessType;

    @ApiModelProperty(name = "receiverUserIds", value = "接收人用户id,非广播必填")
    private Set<String> receiverUserIds;

    @ApiModelProperty(name = "showRemind", value = "是否需要提示框(前端收到消息使用),默认false-不需要")
    private boolean showRemind;

    public MqMessageMsgModel(String msg, int type) {
        this.msg = msg;
        this.type = type;
    }


    /**
     * 添加接收人
     *
     * @param userId 接收用户id
     */
    public void addUserId(String userId) {
        if (Objects.isNull(this.receiverUserIds)) {
            this.receiverUserIds = new HashSet<>();
        }
        this.receiverUserIds.add(userId);
    }


    /**
     * 添加接收人
     *
     * @param userIds 接收用户ids
     */
    public void addUserId(List<String> userIds) {
        if (Objects.isNull(this.receiverUserIds)) {
            this.receiverUserIds = new HashSet<>();
        }
        this.receiverUserIds.addAll(userIds);
    }
}
