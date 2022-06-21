package com.yblue.lucky_draw.controller;

import com.yblue.lucky_draw.config.JwtProperties;
import com.yblue.lucky_draw.config.mq.RabbitCallback;
import com.yblue.lucky_draw.enums.TopicEnum;
import com.yblue.lucky_draw.util.JsonResult;
import com.yblue.lucky_draw.util.PasswordConfig;
import com.yblue.lucky_draw.util.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author JiaXinMa
 * @description 测试控制层
 * @date 2022/1/29
 */
@Slf4j
@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {

    PasswordConfig passwordConfig;

    JwtProperties jwtProperties;

    RabbitTemplate rabbitTemplate;

    @GetMapping("/mq/{id}")
    public JsonResult mq(@PathVariable Integer id) {
        rabbitTemplate.convertAndSend(TopicEnum.TEST_TOPIC.getExchangeName(),
                TopicEnum.TEST_TOPIC.getRoutingKey(), id, message -> {
                    message.getMessageProperties().setCorrelationId(UUID.randomUUID().toString());
                    return message;
                });

        return new JsonResult(ResultEnum.SUCCESS);
    }

    @GetMapping("/mq2/{id}")
    public JsonResult mq2(@PathVariable Integer id, HttpServletResponse response) {

        rabbitTemplate.setConfirmCallback(new RabbitCallback());

        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString());

        rabbitTemplate.convertAndSend(TopicEnum.TEST_TOPIC.getExchangeName(),
                TopicEnum.TEST_TOPIC.getRoutingKey(), id, correlationData);

        return new JsonResult(ResultEnum.SUCCESS);

    }

    @GetMapping("{password}")
    public String encode(@PathVariable String password){
        return passwordConfig.passwordEncoder().encode(password);
    }
}
