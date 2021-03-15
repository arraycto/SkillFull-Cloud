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

import indi.zxiaozhou.skillfull.coremvc.base.controller.vo.BasePageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 操作日志分页查询Request
 *
 * @author zxiaozhou
 * @date 2021-01-12 14:40:28
 * @since JDK11
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel
public class OperatePageVo extends BasePageVo {
    private static final long serialVersionUID = -68855804632714072L;

    @ApiModelProperty(name = "logNote", value = "日志注解")
    private String logNote;

    @ApiModelProperty(name = "operateType", value = " 操作类型（1查询，2添加，3修改，4删除，5其他）")
    private Integer operateType;

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

    @ApiModelProperty(name = "dataSources", value = "数据来源")
    private String dataSources;
}