package com.yblue.lucky_draw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yblue.lucky_draw.domain.User;
import com.yblue.lucky_draw.mapper.UserMapper;
import com.yblue.lucky_draw.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
