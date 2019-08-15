package com.zfh.consumer.seata.at.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zfh.api.TService;
import com.zfh.consumer.seata.at.service.ConsumerService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 这个直连地址不是服务提供者启动的地址和端口号，具体要看某服务的地址可以直接去看服务提供者启动日志中某服务的地址
    @Reference(url = "dubbo://172.17.8.138:20888")
    private TService tService;
    @Override
    @GlobalTransactional(timeoutMills = 300000, name = "service-consumer")
    public void consumer() {
        System.out.println("开始全局事务，XID = " + RootContext.getXID());
        tService.service();
        jdbcTemplate.update("insert into USER(name, age ) values(?, ?)", "123", "212");
        System.out.println("本地service执行");

       //验证分布式事务
        throw new RuntimeException("12");

    }
}
