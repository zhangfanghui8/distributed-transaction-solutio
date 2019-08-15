package com.zfh.provider.seata.at.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.zfh.api.TService;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author zfh
 * @since 2019.3.26
 */
@Service
public class TServiceImpl implements TService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void service() {
        System.out.println("全局事务id ：" + RootContext.getXID());
        System.out.println("提供服务");
        jdbcTemplate.update("insert into USER2(name, age ) values(?, ?)", "123", "212");
    }
}