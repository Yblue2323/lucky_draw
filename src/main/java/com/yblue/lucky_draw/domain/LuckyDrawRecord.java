package com.yblue.lucky_draw.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_lucky_draw_record")
public class LuckyDrawRecord {

    @TableId(value = "record_id", type = IdType.AUTO)
    private Integer recordId;

    private Long userId;

    private Long activityId;

    private Long gitId;

    private Date createTime;

}
