// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.coremvc.aspect.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotBlankOrNull;
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
 * 操作日志添加或修改Request
 *
 * @author zxiaozhou
 * @date 2020-09-14 23:39:55
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class OperateModel implements Serializable {
    private static final long serialVersionUID = -72227624406240356L;

    @ApiModelProperty(name = "logNote", value = "日志注解")
    private String logNote;

    @ApiModelProperty(name = "operateType", value = " 操作类型（1查询，2添加，3修改，4删除，5其他）", required = true)
    @NotBlankOrNull(message = " 操作类型不能为空")
    private Integer operateType;

    @ApiModelProperty(name = "fileName", value = "调用文件名")
    private String fileName;

    @ApiModelProperty(name = "methodName", value = "调用方法名")
    private String methodName;

    @ApiModelProperty(name = "methodParams", value = "调用方法参数")
    private String methodParams;

    @ApiModelProperty(name = "exceptionName", value = "异常名")
    private String exceptionName;

    @ApiModelProperty(name = "stackTrace", value = "堆栈信息")
    private String stackTrace;

    @ApiModelProperty(name = "exceptionMessage", value = "异常消息")
    private String exceptionMessage;

    @ApiModelProperty(name = "requestResult", value = "请求结果")
    private String requestResult;

    @ApiModelProperty(name = "requestParam", value = "请求参数")
    private String requestParam;

    @ApiModelProperty(name = "lineNumber", value = "代码行数")
    private Integer lineNumber;

    @ApiModelProperty(name = "requestStartTime", value = "请求开始时间(创建实例时自动处理)", hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime requestStartTime;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    public OperateModel() {
        this.requestStartTime = LocalDateTime.now();
    }
}