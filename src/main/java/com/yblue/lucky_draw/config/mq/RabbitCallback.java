package com.yblue.lucky_draw.config.mq;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


@Slf4j
public class RabbitCallback implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {



    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        //当MQ服务器成功接受到消息
        log.info("服务器消息接收成功:{}",ack);
        if (ack) {
            log.info("服务器消息接收成功：ID:{},Future:{},Returned:{},ReturnMessage:{}", correlationData.getId(), correlationData.getFuture(), correlationData.getReturned(), correlationData.getReturnedMessage());
        } else {
            log.info("服务器消息接受失败：{}", cause);
        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {

    }
}
