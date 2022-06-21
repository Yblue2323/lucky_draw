package com.yblue.lucky_draw.consume;


import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consume {


    @RabbitListener(queues = "lucky_draw.test.topic.queue")
    @RabbitHandler
    public void consume(String str ,Message message, Channel channel) {
        //获取消息中id
        try {
            log.info("获取消息中id：{}",str);
//            int i =  10 / 0;
            //签收消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (Exception e) {
            //是否重投了
            Boolean redelivered = message.getMessageProperties().getRedelivered();

            try {
                if (redelivered) {//重新消费了
                    //消息丢弃
                    //放入死信队列
                    channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
//                    log.error("消息丢弃了");
                    log.error("消息放入死信队列了");

                } else {//第一次消费
                    //消息重投
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                    log.error("消息重新回到队列");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }

}
