package com.yblue.lucky_draw.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_operator_log")
public class OperatorLog {

    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    private String ip;

    private String method;

    private String requestUrl;

    private String requestParam;

    private String requestType;

    private Long costTime;

    private String responseData;

    private Long userId;


}