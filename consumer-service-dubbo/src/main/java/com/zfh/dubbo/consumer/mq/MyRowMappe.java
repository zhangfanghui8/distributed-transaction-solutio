package com.zfh.dubbo.consumer.mq;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @auth zhangfanghui
 * @since 2019-08-08
 */
public class MyRowMappe implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Map res = new HashMap<String,Object>();
        res.put("bizUniNo",resultSet.getString("bizUniNo"));
        return res;
    }
}
