package com.zfh.dubbo.consumer.tcc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements com.zfh.dubbo.consumer.tcc.service.impl.ConsumerService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 这个直连地址不是服务提供者启动的地址和端口号，具体要看某服务的地址可以直接去看服务提供者启动日志中某服务的地址
//    @Reference(url = "dubbo://10.18.190.114:20880")
//    private TService tService;
    @Override
    public void consumer() {
        //tService.service();
        jdbcTemplate.update("insert into USER(name, age ) values(?, ?)", "123", "212");
        System.out.println("本地consumer service执行");

    }
}
