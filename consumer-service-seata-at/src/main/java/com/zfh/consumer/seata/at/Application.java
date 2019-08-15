package com.zfh.consumer.seata.at;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import io.seata.spring.annotation.GlobalTransactionScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;

/**
 * @author zfh
 * @since  2019.3.26
 */
@SpringBootApplication
@EnableDubbo
@Configuration
@PropertySource("classpath:dubbo-consumer.properties")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @DependsOn("dataSource")
    public GlobalTransactionScanner  globalTransactionScanner(){
        return new GlobalTransactionScanner("service-consumer","my_test_tx_group");
    }
}