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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Objects;

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
public class MessageMsgModel implements Serializable {
    private static final long serialVersionUID = 2718987608915965865L;

    /**
     * 消息内容
     */
    private String message;


    /**
     * 发送人用户id
     */
    private String sendUserId;

    /**
     * 发送人用户姓名
     */
    private String sendUserName;

    /**
     * 业务数据标识
     */
    private String businessType;

    /**
     * 是否需要提示框(前端收到消息使用),默认false-不需要
     */
    private boolean showRemind;


    public MessageMsgModel(MqMessageMsgModel model) {
        if (Objects.nonNull(model)) {
            this.message = model.getMsg();
            this.sendUserId = model.getSendUserId();
            this.sendUserName = model.getSendUserName();
            this.businessType = model.getBusinessType();
            this.showRemind = model.isShowRemind();
        }
    }
}
