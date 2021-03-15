// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.logging.core.handler;


import indi.zxiaozhou.skillfull.corecommon.base.Result;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import indi.zxiaozhou.skillfull.corecommon.utils.CoreCommonUtils;
import indi.zxiaozhou.skillfull.coremvc.base.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
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
     * @return Result<?> ${@link Result<?>}
     * @author zxiaozhou
     * @date 2020-08-27 15:21
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        String str = CoreCommonUtils.getStackTrace(e);
        log.error("------------GlobalExceptionHandler------处理所有不可知的异常------>handleException:{}", str);
        if (StringUtils.isNotBlank(str)) {
            return fail(str);
        }
        return fail("服务器出问题了:" + e.getMessage());
    }


    /**
     * 处理自定义异常
     *
     * @param e ${@link ResponseException} 处理异常
     * @return Result<?> ${@link Result<?>} 响应前端
     * @author zxiaozhou
     * @date 2020-08-27 15:17
     */
    @ExceptionHandler(ResponseException.class)
    public Result<?> handlerResponseException(ResponseException e) {
        Result<Object> result = e.getResult();
        log.error("------------GlobalExceptionHandler------处理自定义异常------>handlerResponseException:{}", e.getMessage());
        return fail(result.getCode(), result.getMessage());
    }


    /**
     * 处理请求参数校验(普通传参)异常
     *
     * @param e ${@link ConstraintViolationException}
     * @return Result<?> ${@link Result<?>}
     * @author zxiaozhou
     * @date 2019-06-18 09:35
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    Result<?> handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder sb = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            sb.append(",").append(violation.getMessage());
        }
        String errMeg = sb.toString().replaceFirst(",", "");
        log.error("------------GlobalExceptionHandler------处理请求参数校验(普通传参)异常------>handleConstraintViolationException:{}", errMeg);
        return fail(Status.VERIFICATION_FAILED, errMeg);
    }


    /**
     * 处理请求参数校验(实体对象传参)异常
     *
     * @param e ${@link MethodArgumentNotValidException}
     * @return Result<?> ${@link Result<?>}
     * @author zxiaozhou
     * @date 2020-08-27 15:27
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder sb = new StringBuilder();
        List<ObjectError> errorList = e.getBindingResult().getAllErrors();
        for (ObjectError error : errorList) {
            sb.append(",").append(error.getDefaultMessage());
        }
        String errMeg = sb.toString().replaceFirst(",", "");
        log.error("------------GlobalExceptionHandler------处理请求参数校验(实体对象传参)异常------>handleMethodArgumentNotValidException:{}", errMeg);
        return fail(Status.VERIFICATION_FAILED, errMeg);
    }


    /**
     * 处理数据库数据重复异常
     *
     * @param e ${@link DuplicateKeyException}
     * @return Result<?> ${@link Result<?>}
     * @author zxiaozhou
     * @date 2020-08-27 15:27
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Result<?> handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("------------GlobalExceptionHandler------处理数据库数据重复异常------>handleDuplicateKeyException:{}", e.getMessage());
        return fail("数据库中已存在该记录:" + e.getMessage());
    }


    /**
     * 处理不支持请求方式
     *
     * @param e ${@link HttpRequestMethodNotSupportedException}
     * @return Result<?> ${@link Result<?>}
     * @author zxiaozhou
     * @date 2020-08-27 15:28
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<?> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("------------GlobalExceptionHandler-----处理不支持请求方式------->HttpRequestMethodNotSupportedException:{}", e.getMessage());
        return fail("请求方式不支持:" + e.getMessage());
    }


    /**
     * 处理sql语法错误
     *
     * @param e ${@link BadSqlGrammarException}
     * @return Result<?> ${@link Result<?>}
     * @author zxiaozhou
     * @date 2020-08-27 15:28
     */
    @ExceptionHandler(BadSqlGrammarException.class)
    public Result<?> httpBadSqlGrammarException(BadSqlGrammarException e) {
        log.error("------------GlobalExceptionHandler------处理sql语法错误------>httpBadSqlGrammarException:{}", e.getMessage());

        return fail("数据库sql语法错误:" + e.getMessage());
    }


    /**
     * post请求缺少body参数
     *
     * @param e ${@link HttpMessageNotReadableException}
     * @return Result<?> ${@link Result<?>}
     * @author zxiaozhou
     * @date 2020-08-27 15:28
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<?> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("------------GlobalExceptionHandler------post请求缺少body参数------>httpMessageNotReadableException:{}", e.getMessage());
        return fail("post请求缺少body参数:" + e.getMessage());
    }
}
