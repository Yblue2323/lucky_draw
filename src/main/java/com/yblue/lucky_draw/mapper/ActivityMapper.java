package com.yblue.lucky_draw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yblue.lucky_draw.domain.Activity;
import com.yblue.lucky_draw.dto.ActivityDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface ActivityMapper extends BaseMapper<Activity> {

    List<Activity> listActivityByUser(@Param("userId") Long userId,
                                     @Param("isDelete") Integer isDelete);

    List<Activity> listActivityExecute();
}
