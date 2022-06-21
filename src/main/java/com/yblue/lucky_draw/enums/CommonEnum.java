package com.yblue.lucky_draw.enums;

import lombok.Getter;

/**
 * @author: JiaXinMa
 * @description: 通过枚举类
 * @date: 2022/1/27
 */
public enum CommonEnum {


    NO_DELETE(0, "未删除"),
    HAS_DELETE(1, "已删除");

    @Getter
    private Integer code;
    @Getter
    private String desc;

    CommonEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
