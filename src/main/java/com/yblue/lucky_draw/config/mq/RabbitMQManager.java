package com.yblue.lucky_draw.config.mq;


import com.yblue.lucky_draw.enums.TopicEnum;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: Created by HYF on 2020/2/19
 *
 * @Version 1.0
 */
@Configuration
public class RabbitMQManager {

    /**
     * 声明topic交换机
     */
    @Bean("testExchange")
    public Exchange testTopicExchange() {
        return ExchangeBuilder.topicExchange(TopicEnum.TEST_TOPIC.getExchangeName()).build();
    }

    /**
     * 声明一个队列
     */
    @Bean("testQueue")
    public Queue testTopicQueue() {
        return QueueBuilder.durable(TopicEnum.TEST_TOPIC.getQueueName())
                .withArgument("x-dead-letter-exchange",TopicEnum.DEAD_TOPIC.getExchangeName())
                .withArgument("x-dead-letter-routing-key",TopicEnum.DEAD_TOPIC.getQueueName())
                .withArgument("x-message-ttl",5000)
                .build();
    }

    /**
     * 通过绑定键 将指定队列绑定到一个指定的交换机 .
     *
     * @param queue    队列
     * @param exchange 交换机
     * @return 绑定
     */
    @Bean
    public Binding testTopicBinding(@Qualifier("testQueue") Queue queue,
                                       @Qualifier("testExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(TopicEnum.TEST_TOPIC.getRoutingKey()).noargs();

    }

    /**
     * 声明topic交换机
     */
    @Bean("deadExchange")
    public Exchange deadTopicExchange() {
        return ExchangeBuilder.topicExchange(TopicEnum.DEAD_TOPIC.getExchangeName()).build();
    }

    /**
     * 声明一个队列
     */
    @Bean("deadQueue")
    public Queue deadTopicQueue() {
        return QueueBuilder.durable(TopicEnum.DEAD_TOPIC.getQueueName()).build();
    }

    /**
     * 通过绑定键 将指定队列绑定到一个指定的交换机 .
     *
     * @param queue    队列
     * @param exchange 交换机
     * @return 绑定
     */
    @Bean
    public Binding deadTopicBinding(@Qualifier("deadQueue") Queue queue,
                                       @Qualifier("deadExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(TopicEnum.DEAD_TOPIC.getRoutingKey()).noargs();

    }

}
