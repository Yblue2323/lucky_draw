package com.yblue.lucky_draw.exception.pojo;

import lombok.Getter;

/**
 * 自定义异常类，封装自定义异常信息
 */
@Getter
public class CommonException extends RuntimeException{
    private Integer status;

    public CommonException(Integer status, String message){
        super(message);
        this.status = status;
    }

    public CommonException(ExceptionEnum exceptionEnum){
        super(exceptionEnum.getMessage());
        this.status = exceptionEnum.getStatus();
    }
}
