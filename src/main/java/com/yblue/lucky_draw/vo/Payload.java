package com.yblue.lucky_draw.vo;

import lombok.Data;

import java.util.Date;

/**
 * 载荷
 */
@Data
public class Payload<T> {
    private String id;
    private T info;
    private Date expiration;
}