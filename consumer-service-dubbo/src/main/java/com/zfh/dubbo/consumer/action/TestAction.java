package com.zfh.dubbo.consumer.action;

import com.zfh.dubbo.consumer.service.ConsumerService;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @auth zhangfanghui
 * @since 2019-08-2
 */
@Controller
public class TestAction {
    @Autowired
    private ConsumerService consumerService;


    @Resource
    private RocketMQTemplate rocketMQTemplate;


    @RequestMapping("/test")
    public String test(){
        consumerService.consumer();
        return "12";
    }
    @Transactional
    @RequestMapping("/testTransaction")
    public String testTransaction(){
        TransactionSendResult transactionSendResult = null;
        consumerService.consumer();
        // 发送事务消息
        String s = "张fanghui";
        Message msg =  MessageBuilder.withPayload(s).build();
        Map arg = new HashMap<String,String>();
        try {
            arg.put("bizType",1);
            transactionSendResult = rocketMQTemplate.sendMessageInTransaction("Test", "TopicTest", msg, arg);
            if(transactionSendResult!=null && transactionSendResult.getLocalTransactionState().equals(LocalTransactionState.ROLLBACK_MESSAGE)){
                System.out.printf("transaction rollback");
                throw new RuntimeException("transactionSendResult:"+LocalTransactionState.ROLLBACK_MESSAGE);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return "测试事务消息";
    }
}
