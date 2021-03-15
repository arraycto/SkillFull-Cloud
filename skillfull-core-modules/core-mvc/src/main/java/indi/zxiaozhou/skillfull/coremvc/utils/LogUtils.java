// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.coremvc.utils;

import indi.zxiaozhou.skillfull.corecommon.base.model.DataLogModel;
import indi.zxiaozhou.skillfull.corecommon.base.model.OperateLogModel;
import indi.zxiaozhou.skillfull.corecommon.base.model.UserAndAuthModel;
import indi.zxiaozhou.skillfull.coremvc.aspect.mapstruct.DataLogModelMap;
import indi.zxiaozhou.skillfull.coremvc.aspect.mapstruct.OperateLogModelMap;
import indi.zxiaozhou.skillfull.coremvc.aspect.model.DataModel;
import indi.zxiaozhou.skillfull.coremvc.aspect.model.OperateModel;
import indi.zxiaozhou.skillfull.coremvc.config.properties.AppProperty;
import indi.zxiaozhou.skillfull.coremvc.message.LogSendProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 日志工具类
 *
 * @author zxiaozhou
 * @date 2020-09-15 00:24
 * @since JDK11
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class LogUtils {
    private static LogUtils utils;
    private final LogSendProcessor sendProcessor;
    private final DataLogModelMap dataLogModelMap;
    private final OperateLogModelMap operateLogModelMap;
    private final AppProperty property;


    @PostConstruct
    private void init() {
        utils = this;
    }


    /**
     * 发送操作数据日志
     *
     * @param model ${@link DataLogModel} 数据日志
     * @author zxiaozhou
     * @date 2020-09-15 00:28
     */
    public static void sedDataLog(DataModel model) {
        DataLogModel logModel = utils.dataLogModelMap.toEntity(model);
        // 补全请求信息
        HttpServletRequest request = ServletUtils.getRequest();
        if (Objects.nonNull(request)) {
            logModel.setIp(ServletUtils.getIpAddr(request));
            logModel.setRequestMethod(request.getMethod().toUpperCase());
            logModel.setRequestUrl(request.getRequestURL().toString());
        }
        logModel.setDataSources(utils.property.getServiceName());
        logModel.setRequestEndTime(LocalDateTime.now());
        // 获取用户信息
        try {
            UserAndAuthModel userAndAuthModel = ContextHolderUtils.getUserAndAuthModel();
            logModel.setUserId(userAndAuthModel.getUserId());
            logModel.setUserName(StringUtils.isNotBlank(userAndAuthModel.getRealName()) ? userAndAuthModel.getRealName() : userAndAuthModel.getNickName());
        } catch (Exception e) {
            log.error("------------LogUtils------获取用户信息失败------>sedDataLog--->\n异常消息:{}", e.getMessage());
        }
        Message<DataLogModel> stringMessage = MessageBuilder.withPayload(logModel).build();
        utils.sendProcessor.sendDataLogMessage().send(stringMessage);
    }


    /**
     * 发送数据日志
     *
     * @param model ${@link OperateLogModel} 操作日志
     * @author zxiaozhou
     * @date 2020-09-15 00:28
     */
    public static void sedDataLog(OperateModel model) {
        OperateLogModel logModel = utils.operateLogModelMap.toEntity(model);
        logModel.setDataSources(utils.property.getServiceName());
        logModel.setRequestEndTime(LocalDateTime.now());
        HttpServletRequest request = ServletUtils.getRequest();
        if (Objects.nonNull(request)) {
            logModel.setRequestMethod(request.getMethod().toUpperCase());
            logModel.setRequestUrl(request.getRequestURL().toString());
            logModel.setIp(ServletUtils.getIpAddr(request));
        }
        HttpServletResponse response = ServletUtils.getResponse();
        if (Objects.nonNull(response)) {
            logModel.setRequestStatus(response.getStatus());
        }
        // 获取用户信息
        try {
            UserAndAuthModel userAndAuthModel = ContextHolderUtils.getUserAndAuthModel();
            logModel.setUserId(userAndAuthModel.getUserId());
            logModel.setUserName(StringUtils.isNotBlank(userAndAuthModel.getRealName()) ? userAndAuthModel.getRealName() : userAndAuthModel.getNickName());
        } catch (Exception e) {
            log.error("------------LogUtils------获取用户信息失败------>sedDataLog--->\n异常消息:{}", e.getMessage());
        }
        Message<OperateLogModel> stringMessage = MessageBuilder.withPayload(logModel).build();
        utils.sendProcessor.sendOperateLogMessage().send(stringMessage);
    }

}
