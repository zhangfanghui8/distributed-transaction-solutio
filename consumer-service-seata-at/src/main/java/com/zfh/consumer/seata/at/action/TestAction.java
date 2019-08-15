package com.zfh.consumer.seata.at.action;

import com.zfh.consumer.seata.at.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auth zhangfanghui
 * @since 2019-07-28
 */
@Controller
public class TestAction {
    @Autowired
    private ConsumerService consumerService;
    @RequestMapping("/test")
    public String test(){
        consumerService.consumer();
        return "测试分布式事务";
    }
}
