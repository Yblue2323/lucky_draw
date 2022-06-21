package com.yblue.lucky_draw.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("tb_git")
public class Git {

    @TableId(value = "gitId", type = IdType.ASSIGN_ID)
    private Long gitId;

    private String gitName;

    private BigDecimal number;

    private String unit;
}
