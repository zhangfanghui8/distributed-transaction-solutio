package com.zfh.dubbo.provider.mq;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @auth zhangfanghui
 * @since 2019-08-08
 */
@Component
@RocketMQMessageListener(consumerGroup = "my-group", topic ="TopicTest")
public class Consumer implements RocketMQListener<MessageExt> {
    @Resource
    private RocketMQTemplate rocketMQTemplate;


    @Override
    public void onMessage(MessageExt o) {
        System.out.println("消费消息，body："+ new String(o.getBody()));
    }
}
