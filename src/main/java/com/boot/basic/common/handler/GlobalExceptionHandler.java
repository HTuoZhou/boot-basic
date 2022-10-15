package com.boot.basic.common.handler;

import com.boot.basic.common.base.ApiFinalResult;
import com.boot.basic.common.base.BusinessException;
import com.boot.basic.common.base.ResultCodeEnum;
import com.boot.basic.common.base.SystemProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * @author HTuoZhou
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Environment environment;

    public GlobalExceptionHandler(Environment environment) {
        this.environment = environment;
    }

    /**
     * 服务器异常
     */
    @ExceptionHandler(Exception.class)
    public ApiFinalResult<Object> exceptionHandler(Exception e, HttpServletRequest request) {
        log.info("请求URL : {}", request.getRequestURL().toString(), e);
        return ApiFinalResult.error(ResultCodeEnum.ERROR, e.getLocalizedMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ApiFinalResult<Object> businessExceptionHandler(BusinessException e, HttpServletRequest request) {
        log.info("请求URL : {}", request.getRequestURL().toString(), e);
        return ApiFinalResult.error(e.getCode(), e.getMessage(), e.getData());
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiFinalResult<Object> constraintViolationExceptionHandler(ConstraintViolationException e, HttpServletRequest request) {
        log.info("请求URL : {}", request.getRequestURL().toString(), e);

        StringBuilder stringBuilder = new StringBuilder();

        Set<ConstraintViolation<?>> constraintViolationSet = e.getConstraintViolations();
        for (ConstraintViolation<?> x : constraintViolationSet) {
            stringBuilder.append(x.getMessageTemplate()).append("，");
        }
        stringBuilder.substring(0, stringBuilder.length() - 1);

        return ApiFinalResult.error(ResultCodeEnum.FAILED_PARAMETER, stringBuilder);
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiFinalResult<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.info("请求URL : {}", request.getRequestURL().toString(), e);

        StringBuilder stringBuilder = new StringBuilder();

        BeanPropertyBindingResult result = (BeanPropertyBindingResult) e.getBindingResult();
        List<ObjectError> errors = result.getAllErrors();
        for (ObjectError x : errors) {
            stringBuilder.append(x.getDefaultMessage()).append("，");
        }
        stringBuilder.substring(0, stringBuilder.length() - 1);

        return ApiFinalResult.error(ResultCodeEnum.FAILED_PARAMETER, stringBuilder);
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(BindException.class)
    public ApiFinalResult<Object> bindExceptionHandler(BindException e, HttpServletRequest request) {
        log.info("请求URL : {}", request.getRequestURL().toString(), e);

        StringBuilder stringBuilder = new StringBuilder();

        BeanPropertyBindingResult result = (BeanPropertyBindingResult) e.getBindingResult();
        List<ObjectError> errors = result.getAllErrors();
        for (ObjectError x : errors) {
            stringBuilder.append(x.getDefaultMessage()).append("，");
        }
        stringBuilder.substring(0, stringBuilder.length() - 1);

        return ApiFinalResult.error(ResultCodeEnum.FAILED_PARAMETER, stringBuilder);
    }

    /**
     * 文件大小限制异常
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ApiFinalResult<Object> maxUploadSizeExceededExceptionHandler(MaxUploadSizeExceededException e, HttpServletRequest request) {
        log.info("请求URL : {}", request.getRequestURL().toString(), e);
        return ApiFinalResult.error(ResultCodeEnum.FAILED_FILE_SIZE_LIMIT, "文件上传最大允许" + environment.getProperty(SystemProperties.SPRING_SERVLET_MULTIPART_MAX_FILE_SIZE));
    }

}
