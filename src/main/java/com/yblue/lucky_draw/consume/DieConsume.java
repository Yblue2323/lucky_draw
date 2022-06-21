package com.yblue.lucky_draw.consume;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author JiaXinMa
 * @description 死信队列消费者
 * @date 2022/3/25
 */
@Slf4j
@Component
public class DieConsume {

    @RabbitListener(queues = "lucky_draw.test.topic.queue")
    @RabbitHandler
    public void consume(String str, Message message, Channel channel) {
        //获取消息中id
        try {
//            int i =  10 / 0;
            log.info("死信队列获取到消息id：{}", str);
            //签收消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }
}
