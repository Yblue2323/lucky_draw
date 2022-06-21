package com.yblue.lucky_draw.exception.pojo;

import lombok.Getter;
import org.joda.time.DateTime;

/**
 * 异常结果类，封装异常结果信息
 */
@Getter
public class ExceptionResult {
    private Integer status; //异常状态码
    private String message; //异常消息
    private String timestamp;//异常发生时间

    public ExceptionResult(CommonException e){
        this.status = e.getStatus();
        this.message = e.getMessage();
        this.timestamp = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
    }

}
