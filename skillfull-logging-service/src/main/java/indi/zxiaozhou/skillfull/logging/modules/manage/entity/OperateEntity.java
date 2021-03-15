// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.logging.modules.manage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.zxiaozhou.skillfull.coremvc.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 操作日志(Operate)Entity
 *
 * @author zxiaozhou
 * @date 2021-02-12 19:49:28
 * @since JDK1.8
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("logging_operate")
public class OperateEntity extends BaseEntity {
    private static final long serialVersionUID = -62192312849020833L;

    @TableId
    private String sysLogId;

    @ApiModelProperty(name = "logNote", value = "日志注解")
    private String logNote;

    @ApiModelProperty(name = "businessCode", value = "业务编码")
    private String businessCode;

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

    @ApiModelProperty(name = "requestParam", value = "请求参数")
    private String requestParam;

    @ApiModelProperty(name = "requestResult", value = "请求结果")
    private String requestResult;

    @ApiModelProperty(name = "requestStatus", value = "请求状态")
    private Integer requestStatus;

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

    @ApiModelProperty(name = "lineNumber", value = "代码行数")
    private Integer lineNumber;

    @ApiModelProperty(name = "dataSources", value = "数据来源")
    private String dataSources;

    @ApiModelProperty(name = "costTime", value = "耗时")
    private Long costTime;

    @ApiModelProperty(name = "requestStartTime", value = "请求开始时间")
    private LocalDateTime requestStartTime;

    @ApiModelProperty(name = "requestEndTime", value = "请求结束时间")
    private LocalDateTime requestEndTime;

    @ApiModelProperty(name = "createAreaCode", value = "创建区域编码")
    private String createAreaCode;

    @ApiModelProperty(name = "createPositionCode", value = "创建职位编码")
    private String createPositionCode;

    @ApiModelProperty(name = "createOrgSysCode", value = "创建机构系统编码")
    private String createOrgSysCode;

    @ApiModelProperty(name = "createSystemCode", value = "创建系统编码")
    private String createSystemCode;

    @ApiModelProperty(name = "createTenantId", value = "创建租户id")
    private String createTenantId;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
}