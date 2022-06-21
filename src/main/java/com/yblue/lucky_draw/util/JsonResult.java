package com.yblue.lucky_draw.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult<T> {

    private Integer code;

    private String msg;

    private T data;

    public JsonResult(T data, ResultEnum jsonEnum) {
        this.data = data;
        this.code = jsonEnum.getCode();
        this.msg = jsonEnum.getMsg();
    }


    public JsonResult(ResultEnum jsonEnum) {
        this.code = jsonEnum.getCode();
        this.msg = jsonEnum.getMsg();
    }
}