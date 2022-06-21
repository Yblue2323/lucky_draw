package com.yblue.lucky_draw.config.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.yblue.lucky_draw.domain.Activity;
import com.yblue.lucky_draw.service.impl.ActivityServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;


@Slf4j
@Component
@AllArgsConstructor
public class ActivityCache {

    private ActivityServiceImpl activityService;

    private static Cache<Long, Activity> cache = Caffeine.newBuilder()
            .expireAfterWrite(120, TimeUnit.MINUTES)
            .maximumSize(10)
            .build();

    //在bean初始化之前调用
    @PostConstruct
    public void initCache() {


    }
}
