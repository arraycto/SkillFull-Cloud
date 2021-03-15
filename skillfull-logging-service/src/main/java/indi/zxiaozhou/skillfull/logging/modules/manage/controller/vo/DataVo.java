// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.logging.modules.manage.controller.vo;

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
import java.time.ZoneOffset;
import java.util.Objects;

/**
 * 数据日志添加或修改Request
 *
 * @author zxiaozhou
 * @date 2021-01-12 14:38:32
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class DataVo implements Serializable {
    private static final long serialVersionUID = 221492007594866104L;

    @ApiModelProperty(name = "dataTable", value = "表名")
    private String dataTable;

    @ApiModelProperty(name = "logNote", value = "日志注解")
    private String logNote;

    @ApiModelProperty(name = "dataId", value = "数据id")
    private String dataId;

    @ApiModelProperty(name = "dataContent", value = "数据内容", required = true)
    @NotBlankOrNull(message = "数据内容不能为空")
    private String dataContent;

    @ApiModelProperty(name = "ip", value = "请求ip", required = true)
    @NotBlankOrNull(message = "请求ip不能为空")
    private String ip;

    @ApiModelProperty(name = "requestParam", value = "请求参数")
    private String requestParam;

    @ApiModelProperty(name = "requestMethod", value = "请求方法")
    private String requestMethod;

    @ApiModelProperty(name = "requestUrl", value = "请求路径")
    private String requestUrl;

    @ApiModelProperty(name = "fileName", value = "调用文件名", required = true)
    @NotBlankOrNull(message = "调用文件名不能为空")
    private String fileName;

    @ApiModelProperty(name = "methodName", value = "调用方法名", required = true)
    @NotBlankOrNull(message = "调用方法名不能为空")
    private String methodName;

    @ApiModelProperty(name = "methodParams", value = "调用方法参数")
    private String methodParams;

    @ApiModelProperty(name = "methodStatus", value = "调用方法状态:0-失败,1-成功", required = true)
    @NotBlankOrNull(message = "调用方法状态:0-失败,1-成功不能为空")
    private Integer methodStatus;

    @ApiModelProperty(name = "userId", value = "操作人用户id")
    private String userId;

    @ApiModelProperty(name = "userName", value = "操作人用户名称")
    private String userName;

    @ApiModelProperty(name = "exceptionName", value = "异常名")
    private String exceptionName;

    @ApiModelProperty(name = "stackTrace", value = "堆栈信息")
    private String stackTrace;

    @ApiModelProperty(name = "exceptionMessage", value = "异常消息")
    private String exceptionMessage;

    @ApiModelProperty(name = "lineNumber", value = "代码行数")
    private Integer lineNumber;

    @ApiModelProperty(name = "dataSources", value = "数据来源(服务名)", required = true)
    @NotBlankOrNull(message = "数据来源不能为空")
    private String dataSources;

    @ApiModelProperty(name = "costTime", value = "耗时", hidden = true)
    private Long costTime;

    @ApiModelProperty(name = "requestStartTime", value = "请求开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime requestStartTime;

    @ApiModelProperty(name = "requestEndTime", value = "请求结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime requestEndTime;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    public Long getCostTime() {
        if (Objects.nonNull(requestStartTime) && Objects.nonNull(requestEndTime)) {
            return requestEndTime.toInstant(ZoneOffset.of("+8")).toEpochMilli() - requestStartTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        }
        return null;
    }
}