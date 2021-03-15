// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.modules.message.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 用户消息记录查询Response
 *
 * @author zxiaozhou
 * @date 2021-01-26 16:48:01
 * @since JDK11
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel
public class MessageUserNoReadRecordDto implements Serializable {
    private static final long serialVersionUID = 264963135404655627L;

    @ApiModelProperty(name = "systemMessages", value = "系统消息")
    private List<MessageUserRecordDto> systemMessages = Collections.emptyList();

    @ApiModelProperty(name = "waitHandleMessages", value = "代办消息")
    private List<MessageUserRecordDto> waitHandleMessages = Collections.emptyList();
}