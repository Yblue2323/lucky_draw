package com.yblue.lucky_draw.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


@Data
@TableName("tb_activity")
public class Activity {

    @TableId(value = "activity_id", type = IdType.ASSIGN_ID)
    private Long activityId;

    private Long creatorId;

    private String activityName;

    private String activityDescribe;

    private String activityCode;

    private Integer activityType;

    private Integer activityNumber;

    @TableLogic
    private Integer isDelete;

    private Date activityStartTime;

    private Date activityEndTime;

    private Date createTime;

    private Integer state;//状态:0未启动1进行中2作废
}
