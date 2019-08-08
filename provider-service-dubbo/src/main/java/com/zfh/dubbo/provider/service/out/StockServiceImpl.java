package com.zfh.dubbo.provider.service.out;

import com.zfh.api.StockService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author zfh
 * @since 2019.8.5
 */
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void reduce(int count) {

    }
}