package com.yblue.lucky_draw.config.cache;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.yblue.lucky_draw.domain.User;
import com.yblue.lucky_draw.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;


@Slf4j
@Component
@AllArgsConstructor
public class UserCache {

    private UserMapper userMapper;

    private static Cache<Long, User> cache = Caffeine.newBuilder()
            .expireAfterWrite(120, TimeUnit.MINUTES)
            .maximumSize(1000000)
            .build();

    //在bean初始化之前调用
    @PostConstruct
    public void initCache() {
        Map<Long, User> map = userMapper.selectList(Wrappers.query()).stream().collect(Collectors.toMap(User::getUserId, Function.identity()));
        cache.putAll(map);
    }

    public User getUserById(Long userId) {
        User user = cache.getIfPresent(userId);
        if (user == null) {
            user = userMapper.selectById(userId);
        }
        return user;
    }
}
