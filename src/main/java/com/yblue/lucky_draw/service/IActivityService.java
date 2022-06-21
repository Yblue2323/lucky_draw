package com.yblue.lucky_draw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yblue.lucky_draw.domain.Activity;
import com.yblue.lucky_draw.dto.ActivityDTO;

import java.util.Date;
import java.util.List;

public interface IActivityService extends IService<Activity> {

    //修改或保存
    boolean saveOrUpdateActivity(ActivityDTO activityDTO);

    boolean deleteActivity(List<Long> ids);

    List<Activity> listActivityByUser(Integer isDelete);

    List<Activity> listActivityByCreator(Date createTime);

    //获取所有正在执行的活动(未删除)
    List<Activity> listActivityExecute();

}
