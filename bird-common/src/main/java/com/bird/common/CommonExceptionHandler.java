package com.bird.common;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @Author lipu
 * @Date 2020/9/13 18:11
 * @Description 通用异常处理
 */
@RestControllerAdvice
public class CommonExceptionHandler {
    //参数校验统一异常处理
    @ExceptionHandler(BindException.class)
    public CommonResult<String> BindHandler(BindException e) {
        String defaultMessage = e.getAllErrors().get(0).getDefaultMessage();
        return new CommonResult(CommonStatus.VALIDATE_ERROR,defaultMessage);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<String> MethodArgumentNotValidHandler(MethodArgumentNotValidException e){
        FieldError fieldError = e.getBindingResult().getFieldError();
        String defaultMessage = fieldError.getDefaultMessage();
        return new CommonResult(CommonStatus.VALIDATE_ERROR,defaultMessage);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public CommonResult<String> ConstraintViolationHandler(ConstraintViolationException e){
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        StringBuilder stringBuilder=new StringBuilder();
        for (ConstraintViolation constraintViolation:constraintViolations) {
            stringBuilder.append(constraintViolation.getMessage());
        }
        return new CommonResult(CommonStatus.VALIDATE_ERROR,stringBuilder.toString());
    }


    //通用异常处理
//    @ExceptionHandler(Exception.class)
//    public CommonResult<String> CommonHandler(){
//        return new CommonResult<String>(CommonStatus.ERROR,null);
//    }

}
