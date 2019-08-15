package com.zfh.consumer.seata.at;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @auth zhangfanghui
 * @since 2019-07-01
 */
@Configuration
public class SeataDataSourceProxyConfig {

    @Bean(value= "druidDataSource",initMethod = "init",destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    @Primary
    @Bean("dataSource")
    public DataSourceProxy dataSource(DruidDataSource druidDataSource) {
        return new DataSourceProxy(druidDataSource);
    }

    @Primary
    @Bean("jdbcTemplate")
    public JdbcTemplate dataSource(DataSourceProxy dataSourceProxy) {
        return new JdbcTemplate(dataSourceProxy);
    }
}
