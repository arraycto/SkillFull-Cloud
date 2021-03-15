// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.core.handler;


import indi.zxiaozhou.skillfull.corecommon.base.Result;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import indi.zxiaozhou.skillfull.corecommon.utils.CoreCommonUtils;
import indi.zxiaozhou.skillfull.message.core.base.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Objects;
import java.util.Set;


/**
 * 异常处理器
 *
 * @author zxiaozhou
 * @date 2020-06-22 17:34
 * @since JDK11
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends BaseController {
    /**
     * 处理所有不可知的异常
     *
     * @param e ${@link Exception}
     * @return Mono<Result < String>> ${@link Mono<Result<String>>}
     * @author zxiaozhou
     * @date 2020-08-27 15:21
     */
    @ExceptionHandler(Exception.class)
    public Mono<Result<String>> handleException(Exception e) {
        Throwable cause = e.getCause();
        if (cause instanceof ResponseException) {
            ResponseException exception = (ResponseException) cause;
            Result<Object> result = exception.getResult();
            return Mono.just(fail(result.getCode(), result.getMessage()));
        } else if (Objects.nonNull(cause.getCause()) && cause.getCause() instanceof ResponseException) {
            ResponseException exception = (ResponseException) cause.getCause();
            Result<Object> result = exception.getResult();
            return Mono.just(fail(result.getCode(), result.getMessage()));
        }
        String str = CoreCommonUtils.getStackTrace(e);
        if (StringUtils.isNotBlank(str)) {
            return Mono.just(fail(str));
        }
        return Mono.just(fail("服务器出问题了:" + e.getMessage()));
    }


    /**
     * 处理自定义异常
     *
     * @param e ${@link ResponseException} 处理异常
     * @return Mono<Result < String>> ${@link Mono<Result<String>>} 响应前端
     * @author zxiaozhou
     * @date 2020-08-27 15:17
     */
    @ExceptionHandler(ResponseException.class)
    public Mono<Result<String>> handlerResponseException(ResponseException e) {
        Result<Object> result = e.getResult();
        log.error("------------GlobalExceptionHandler------处理自定义异常------>handlerResponseException:{}", e.getMessage());
        return Mono.just(fail(result.getCode(), result.getMessage()));
    }


    /**
     * 处理请求参数校验(普通传参)异常
     *
     * @param e ${@link ConstraintViolationException}
     * @return Mono<Result < String>> ${@link Mono<Result<String>>}
     * @author zxiaozhou
     * @date 2019-06-18 09:35
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    Mono<Result<String>> handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder sb = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            sb.append(",").append(violation.getMessage());
        }
        String errMeg = sb.toString().replaceFirst(",", "");
        log.error("------------GlobalExceptionHandler------处理请求参数校验(普通传参)异常------>handleConstraintViolationException:{}", errMeg);
        return Mono.just(fail(Status.VERIFICATION_FAILED, errMeg));
    }


    /**
     * 处理请求参数校验(实体对象传参)异常
     *
     * @param e ${@link MethodArgumentNotValidException}
     * @return Mono<Result < String>> ${@link Mono<Result<String>>}
     * @author zxiaozhou
     * @date 2020-08-27 15:27
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Mono<Result<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder sb = new StringBuilder();
        List<ObjectError> errorList = e.getBindingResult().getAllErrors();
        for (ObjectError error : errorList) {
            sb.append(",").append(error.getDefaultMessage());
        }
        String errMeg = sb.toString().replaceFirst(",", "");
        log.error("------------GlobalExceptionHandler------处理请求参数校验(实体对象传参)异常------>handleMethodArgumentNotValidException:{}", errMeg);
        return Mono.just(fail(Status.VERIFICATION_FAILED, errMeg));
    }


    /**
     * 处理数据库数据重复异常
     *
     * @param e ${@link DuplicateKeyException}
     * @return Mono<Result < String>> ${@link Mono<Result<String>>}
     * @author zxiaozhou
     * @date 2020-08-27 15:27
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Mono<Result<String>> handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("------------GlobalExceptionHandler------处理数据库数据重复异常------>handleDuplicateKeyException:{}", e.getMessage());
        return Mono.just(fail("数据库中已存在该记录:" + e.getMessage()));
    }


    /**
     * 处理sql语法错误
     *
     * @param e ${@link BadSqlGrammarException}
     * @return Mono<Result < String>> ${@link Mono<Result<String>>}
     * @author zxiaozhou
     * @date 2020-08-27 15:28
     */
    @ExceptionHandler(BadSqlGrammarException.class)
    public Mono<Result<String>> httpBadSqlGrammarException(BadSqlGrammarException e) {
        log.error("------------GlobalExceptionHandler------处理sql语法错误------>httpBadSqlGrammarException:{}", e.getMessage());
        return Mono.just(fail("数据库sql语法错误:" + e.getMessage()));
    }


    /**
     * post请求缺少body参数
     *
     * @param e ${@link HttpMessageNotReadableException}
     * @return Mono<Result < String>> ${@link Mono<Result<String>>}
     * @author zxiaozhou
     * @date 2020-08-27 15:28
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Mono<Result<String>> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("------------GlobalExceptionHandler------post请求缺少body参数------>httpMessageNotReadableException:{}", e.getMessage());
        return Mono.just(fail("post请求缺少body参数:" + e.getMessage()));
    }


    /**
     * webflux处理数据校验一次
     *
     * @param e ${@link WebExchangeBindException}
     * @return Mono<Result < String>> ${@link Mono<Result<String>>}
     * @author zxiaozhou
     * @date 2021-02-03 17:19
     */
    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<Result<String>> handleBindException(WebExchangeBindException e) {
        StringBuilder sb = new StringBuilder();
        List<ObjectError> errorList = e.getBindingResult().getAllErrors();
        for (ObjectError error : errorList) {
            sb.append(",").append(error.getDefaultMessage());
        }
        String errMeg = sb.toString().replaceFirst(",", "");
        log.error("------------GlobalExceptionHandler------post请求缺少body参数------>handleBindException:{}", e.getMessage());
        return Mono.just(fail(errMeg));
    }


    /**
     * sql异常
     *
     * @param e ${@link MyBatisSystemException}
     * @return Mono<Result < String>> ${@link Mono<Result<String>>}
     * @author zxiaozhou
     * @date 2021-02-03 17:19
     */
    @ExceptionHandler(MyBatisSystemException.class)
    public Mono<Result<String>> handleMyBatisSystemException(MyBatisSystemException e) {
        log.error("------------GlobalExceptionHandler------处理mybatis异常------>handleMyBatisSystemException:{}", e.getMessage());
        Throwable cause = e.getCause();
        if (Objects.nonNull(cause.getCause()) && cause.getCause() instanceof ResponseException) {
            ResponseException exception = (ResponseException) cause.getCause();
            Result<Object> result = exception.getResult();
            return Mono.just(fail(result.getCode(), result.getMessage()));
        } else {
            return Mono.just(fail(e.getMessage()));
        }
    }
}
