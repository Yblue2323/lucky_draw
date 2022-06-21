package com.yblue.lucky_draw.service.impl;

import com.yblue.lucky_draw.util.ThreadLocalUtil;
import com.yblue.lucky_draw.vo.GitVO;
import org.springframework.stereotype.Service;

@Service
public class LuckyDrawService {


    public GitVO luckyDraw(Long activityId) {

        //匹配抽奖活动
        matchActivity(activityId);


        return new GitVO();
    }

    private void matchActivity(Long activityId) {
//        Long.valueOf(ThreadLocalUtil.get("userId"));
    }
}
