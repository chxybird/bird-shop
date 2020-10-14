package com.bird.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @Author lipu
 * @Date 2020/9/13 18:11
 * @Description 通用异常处理
 */
@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {
    //参数校验统一异常处理
    @ExceptionHandler(BindException.class)
    public CommonResult<String> BindHandler(BindException e) {
        log.warn("BindException");
        String defaultMessage = e.getAllErrors().get(0).getDefaultMessage();
        return new CommonResult(CommonStatus.VALIDATE_ERROR,defaultMessage);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<String> MethodArgumentNotValidHandler(MethodArgumentNotValidException e){
        log.warn("MethodArgumentNotValidException");
        FieldError fieldError = e.getBindingResult().getFieldError();
        String defaultMessage = fieldError.getDefaultMessage();
        return new CommonResult(CommonStatus.VALIDATE_ERROR,defaultMessage);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public CommonResult<String> ConstraintViolationHandler(ConstraintViolationException e){
        log.warn("ConstraintViolationException");
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        StringBuilder stringBuilder=new StringBuilder();
        for (ConstraintViolation constraintViolation:constraintViolations) {
            stringBuilder.append(constraintViolation.getMessage());
        }
        return new CommonResult(CommonStatus.VALIDATE_ERROR,stringBuilder.toString());
    }

    //非法文件上传请求处理
    @ExceptionHandler(MultipartException.class)
    public CommonResult<String> CommonHandler(){
        return new CommonResult<String>(CommonStatus.VALIDATE_ERROR,"参数必须是文件");
    }

}
