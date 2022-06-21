package com.yblue.lucky_draw.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yblue.lucky_draw.constant.CommomConstants;
import com.yblue.lucky_draw.domain.Activity;
import com.yblue.lucky_draw.dto.ActivityDTO;
import com.yblue.lucky_draw.enums.ActivityStateEnum;
import com.yblue.lucky_draw.enums.CommonEnum;
import com.yblue.lucky_draw.mapper.ActivityMapper;
import com.yblue.lucky_draw.service.IActivityService;
import com.yblue.lucky_draw.util.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {


    @Override
    public boolean saveOrUpdateActivity(ActivityDTO activityDTO) {

        log.info("activityStartTime：{},activityEndTime：{}", activityDTO.getActivityStartTime(), activityDTO.getActivityEndTime());

        //设置活动编码 当前年月日时分秒+6位随机数
        activityDTO.setActivityCode(getCode());
        activityDTO.setCreatorId(Long.valueOf(ThreadLocalUtil.get("userId").toString()));


        Activity activity = new Activity();
        BeanUtils.copyProperties(activityDTO, activity);

        log.info("activityStartTime：{},activityEndTime：{}", activity.getActivityStartTime(), activity.getActivityEndTime());

        return saveOrUpdate(activity);
    }

    @Override
    public boolean deleteActivity(List<Long> ids) {

        List updateList = new ArrayList<Activity>();

        ids.forEach(id -> {
            Activity activity = new Activity();
            activity.setActivityId(id);
            activity.setIsDelete(CommonEnum.HAS_DELETE.getCode());
            activity.setState(ActivityStateEnum.INVALID.getCode());
            updateList.add(activity);
        });


        return updateBatchById(updateList);
    }

    @Override
    public List<Activity> listActivityByUser(Integer isDelete) {
        return getBaseMapper().listActivityByUser(Long.valueOf(ThreadLocalUtil.get(CommomConstants.USER_ID).toString()), isDelete);
    }

    @Override
    public List<Activity> listActivityByCreator(Date createTime) {
        return list(Wrappers.<Activity>lambdaQuery().eq(Activity::getCreatorId, Long.valueOf(ThreadLocalUtil.get("userId").toString())).eq(createTime != null, Activity::getCreateTime, createTime));
    }

    @Override
    public List<Activity> listActivityExecute() {
        return getBaseMapper().listActivityExecute();

    }

    /**
     * @author: JiaXinMa
     * @description: 当前年月日时分秒+6位随机数
     * @date: 2022/1/25
     */
    public String getCode() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date()) + Math.round((Math.random() + 1) * 100000);//当前年月日时分秒+6位随机数
    }
}
