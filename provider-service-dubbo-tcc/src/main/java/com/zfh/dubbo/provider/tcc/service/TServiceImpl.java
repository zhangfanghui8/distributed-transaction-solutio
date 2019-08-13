package com.zfh.dubbo.provider.tcc.service;

import com.zfh.api.TService;
import org.apache.dubbo.config.annotation.Service;
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
        jdbcTemplate.update("insert into USER2(name, age ) values(?, ?)", "123", "212");
        System.out.println("本地provide service执行");
    }
}