// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.logging.modules.manage.service.dto;

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
 * 操作日志分页查询Response
 *
 * @author zxiaozhou
 * @date 2021-01-12 14:40:31
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class OperatePageDto implements Serializable {
    private static final long serialVersionUID = 871979287975608061L;

    @ApiModelProperty(name = "sysLogId", value = "系统日志id")
    private String sysLogId;

    @ApiModelProperty(name = "logNote", value = "日志注解")
    private String logNote;

    @ApiModelProperty(name = "operateType", value = " 操作类型（1查询，2添加，3修改，4删除，5其他）")
    private Integer operateType;

    @ApiModelProperty(name = "userId", value = "操作人用户id")
    private String userId;

    @ApiModelProperty(name = "userName", value = "操作人用户名称")
    private String userName;

    @ApiModelProperty(name = "ip", value = "请求ip")
    private String ip;

    @ApiModelProperty(name = "requestUrl", value = "请求路径")
    private String requestUrl;

    @ApiModelProperty(name = "requestMethod", value = "请求方法")
    private String requestMethod;

    @ApiModelProperty(name = "requestStatus", value = "请求状态")
    private Integer requestStatus;

    @ApiModelProperty(name = "fileName", value = "调用文件名")
    private String fileName;

    @ApiModelProperty(name = "methodName", value = "调用方法名")
    private String methodName;

    @ApiModelProperty(name = "exceptionName", value = "异常名")
    private String exceptionName;

    @ApiModelProperty(name = "lineNumber", value = "代码行数")
    private Integer lineNumber;

    @ApiModelProperty(name = "dataSources", value = "数据来源")
    private String dataSources;

    @ApiModelProperty(name = "costTime", value = "耗时")
    private Long costTime;

    @ApiModelProperty(name = "requestStartTime", value = "请求开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime requestStartTime;

    @ApiModelProperty(name = "requestEndTime", value = "请求结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime requestEndTime;

    @ApiModelProperty(name = "createUserId", value = "创建用户id")
    private String createUserId;

    @ApiModelProperty(name = "createUserName", value = "创建用户姓名")
    private String createUserName;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
}