package com.yblue.lucky_draw.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GitVO {

    private Long userId;

    private Long gitId;

    private String gitName;

    private BigDecimal number;

    private String unit;
}
