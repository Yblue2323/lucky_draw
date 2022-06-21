package com.yblue.lucky_draw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yblue.lucky_draw.domain.OperatorLog;
import com.yblue.lucky_draw.mapper.OperatorLogMapper;
import com.yblue.lucky_draw.service.IOperatorLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class OperatorLogImpl extends ServiceImpl<OperatorLogMapper, OperatorLog> implements IOperatorLogService {
}
