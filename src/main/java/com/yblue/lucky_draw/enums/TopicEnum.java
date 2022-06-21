package com.yblue.lucky_draw.enums;

public enum TopicEnum {

    TEST_TOPIC("lucky_draw.test.topic.exchange", "lucky_draw.test.topic.queue", "lucky_draw.test.topic.routing.key"),
    DEAD_TOPIC("lucky_draw.dead.topic.exchange", "lucky_draw.dead.topic.queue", "lucky_draw.dead.topic.routing.key");

    /*
     * 切记命名不能重复复
     * */
    /*
     * 交换机命名
     * 建议规范格式方便查阅排查问题: 业务+传播类型(topic/direct/..)+ exchange
     * */
    private String exchangeName;
    /*
     * 队列命名
     * 建议规范格式方便查阅排查问题: 业务+传播类型(topic/direct/..)+ queue
     * */
    private String queueName;
    /*
     * routingKey(记入调度策略)
     * */
    private String routingKey;


    TopicEnum(String exchangeName, String queueName, String routingKey) {
        this.exchangeName = exchangeName;
        this.queueName = queueName;
        this.routingKey = routingKey;
    }


    public String getExchangeName() {
        return exchangeName;
    }

    public String getQueueName() {
        return queueName;
    }


    public String getRoutingKey() {
        return routingKey;
    }
}
