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
 * 数据日志分页查询Response
 *
 * @author zxiaozhou
 * @date 2021-01-12 14:38:43
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel
public class DataPageDto implements Serializable {
    private static final long serialVersionUID = -27572495096401185L;

    @ApiModelProperty(name = "dataLogId", value = "数据日志id")
    private String dataLogId;

    @ApiModelProperty(name = "dataTable", value = "表名")
    private String dataTable;

    @ApiModelProperty(name = "logNote", value = "日志注解")
    private String logNote;

    @ApiModelProperty(name = "dataId", value = "数据id")
    private String dataId;

    @ApiModelProperty(name = "ip", value = "请求ip")
    private String ip;

    @ApiModelProperty(name = "requestMethod", value = "请求方法")
    private String requestMethod;

    @ApiModelProperty(name = "requestUrl", value = "请求路径")
    private String requestUrl;

    @ApiModelProperty(name = "fileName", value = "调用文件名")
    private String fileName;

    @ApiModelProperty(name = "methodName", value = "调用方法名")
    private String methodName;

    @ApiModelProperty(name = "methodStatus", value = "调用方法状态:0-失败,1-成功")
    private Integer methodStatus;

    @ApiModelProperty(name = "userId", value = "操作人用户id")
    private String userId;

    @ApiModelProperty(name = "userName", value = "操作人用户名称")
    private String userName;

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

    @ApiModelProperty(name = "dataVersion", value = "版本号")
    private Integer dataVersion;

    @ApiModelProperty(name = "createUserName", value = "创建用户姓名")
    private String createUserName;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
}