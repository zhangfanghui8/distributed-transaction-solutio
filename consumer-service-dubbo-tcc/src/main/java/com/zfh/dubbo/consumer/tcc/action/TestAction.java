package com.zfh.dubbo.consumer.tcc.action;

import com.zfh.dubbo.consumer.tcc.service.impl.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auth zhangfanghui
 * @since 2019-08-13
 */
@Controller
public class TestAction {
    @Autowired
    private ConsumerService consumerService;

    @RequestMapping("/test")
    public String test(){
        consumerService.consumer();
        return "12";
    }
}
