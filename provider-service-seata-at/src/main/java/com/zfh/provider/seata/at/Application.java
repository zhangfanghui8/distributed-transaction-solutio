package com.zfh.provider.seata.at;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import io.seata.spring.annotation.GlobalTransactionScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;

/**
 * @auth zhangfanghui
 * @since 2019-08-15
 */
@SpringBootApplication
@EnableDubbo
@PropertySource({"classpath:dubbo-provider.properties"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    @DependsOn("dataSource")
    public GlobalTransactionScanner globalTransactionScanner(){
        return new GlobalTransactionScanner("service-provider-seata","my_test_tx_group");
    }
}
