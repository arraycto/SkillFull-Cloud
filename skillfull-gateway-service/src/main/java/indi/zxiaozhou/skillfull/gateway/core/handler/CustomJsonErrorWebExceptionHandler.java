// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.gateway.core.handler;


import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.*;

/**
 * @author zxiaozhou
 * @date 2020-09-11 18:10
 * @since JDK11
 */
@Slf4j
public class CustomJsonErrorWebExceptionHandler extends DefaultErrorWebExceptionHandler {
    /**
     * 本地接口验证异常标识
     */
    private static final String VALIDATION_FAILED = "Validation failed";

    public CustomJsonErrorWebExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
                                              ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }


    /**
     * 构建响应消息
     *
     * @param request ${@link ServerRequest}
     * @param options ${@link ErrorAttributeOptions}
     * @return Map<String, Object> ${@link Map<String, Object>}
     * @author zxiaozhou
     * @date 2020-09-11 19:26
     */
    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        int code = 0;
        Throwable error = super.getError(request);
        if (error instanceof ResponseStatusException) {
            status = HttpStatus.NOT_FOUND.value();
        } else if (error instanceof ResponseException) {
            ResponseException responseException = (ResponseException) error;
            code = responseException.getResult().getCode();
        }
        return response(status, code, this.buildMessage(request, error));
    }


    /**
     * 指定响应处理方法为JSON处理的方法
     *
     * @param errorAttributes ${@link ErrorAttributes}
     * @return RouterFunction<ServerResponse> ${@link RouterFunction<ServerResponse>}
     * @author zxiaozhou
     * @date 2020-09-11 19:25
     */

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }


    /**
     * 最终响应数据渲染,去掉不想显示的部分
     *
     * @param request ${@link ServerRequest}
     * @return ServerResponse> ${@link ServerResponse>}
     * @author zxiaozhou
     * @date 2020-09-11 19:25
     */
    @Override
    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        Map<String, Object> error = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL));
        int httpStatus = getHttpStatus(error);
        error.remove("status");
        return ServerResponse.status(httpStatus).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(error));
    }


    /**
     * 构建异常信息
     *
     * @param request
     * @param ex
     * @return
     */
    private String buildMessage(ServerRequest request, Throwable ex) {
        StringBuilder message = new StringBuilder("请求失败[");
        message.append(request.methodName());
        message.append(" ");
        message.append(request.uri());
        message.append("]; 原因:");
        if (ex != null) {
            String exMessage = ex.getMessage();
            if (ex instanceof ResponseException) {
                ResponseException responseException = (ResponseException) ex;
                exMessage = responseException.getResult().getMessage();
            } else if (ex instanceof WebExchangeBindException) {
                WebExchangeBindException webExchangeBindException = (WebExchangeBindException) ex;
                List<ObjectError> allErrors = webExchangeBindException.getBindingResult().getAllErrors();
                StringBuilder sb = new StringBuilder();
                Set<ObjectError> violations = new HashSet<>(allErrors);
                for (ObjectError violation : violations) {
                    sb.append(",").append(violation.getDefaultMessage());
                }
                exMessage = sb.toString().replaceFirst(",", "");
            }
            message.append(exMessage);
        }
        return message.toString();
    }


    /**
     * 构建异常响应消息
     *
     * @param status       ${@link Integer} Http自身状态码
     * @param code         ${@link Integer} 自定义状态码
     * @param errorMessage ${@link String} 异常消息
     * @return Map<String, Object> ${@link Map<String, Object>}
     * @author zxiaozhou
     * @date 2020-09-11 19:23
     */
    public static Map<String, Object> response(int status, int code, String errorMessage) {
        Map<String, Object> result = new HashMap<>(4);
        result.put("code", code);
        result.put("success", status == HttpStatus.OK.value() && code == 0);
        result.put("status", status);
        result.put("message", errorMessage);
        return result;
    }
}
