package com.yblue.lucky_draw.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ApiModel(value = "活动编辑DTO")
public class ActivityDTO {

    @ApiModelProperty(value = "活动id")
    private Long activityId;

    @ApiModelProperty(value = "创建人id")
    private Long creatorId;

    @ApiModelProperty(value = "活动名称", required = true)
    private String activityName;

    @ApiModelProperty(value = "活动描述")
    private String activityDescribe;

    @ApiModelProperty(value = "活动编码")
    private String activityCode;

    @ApiModelProperty(value = "活动类型")
    private Integer activityType;

    @ApiModelProperty(value = "是否删除:0否,1是")
    private Integer isDelete;

    @NotNull(message = "活动开始时间不能为空！")
    @ApiModelProperty(value = "活动开始时间", required = true)
    private Date activityStartTime;

    @NotNull(message = "活动结束时间不能为空！")
    @ApiModelProperty(value = "活动结束时间", required = true)
    private Date activityEndTime;

    @ApiModelProperty(value = "状态:0未启动1进行中2终止3结束")
    private Integer state;//状态:0未启动1进行中2终止3结束
}
