package com.yblue.lucky_draw.enums;

import lombok.Getter;

/**
 *  @author: JiaXinMa
 *  @description: 活动状态
 *  @date:  2022/1/27
 */
public enum ActivityStateEnum {

    NO_START(0, "未启动"),
    IN_PROGRESS(1, "进行中"),
    INVALID(2,"作废");

    @Getter
    private Integer code;
    @Getter
    private String desc;

    ActivityStateEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}