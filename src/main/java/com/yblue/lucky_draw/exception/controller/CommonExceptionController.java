package com.yblue.lucky_draw.exception.controller;

import com.yblue.lucky_draw.exception.pojo.CommonException;
import com.yblue.lucky_draw.exception.pojo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常拦截
 */
@ControllerAdvice  //该注解会把所有Controller的异常拦截
public class CommonExceptionController {

    /**
     * 拦截具体异常方法
     */
    @ExceptionHandler(value = CommonException.class)
    @ResponseBody
    public ResponseEntity<ExceptionResult> handlerException(CommonException e) {
        return ResponseEntity.status(e.getStatus()).body(new ExceptionResult(e));
    }


}
