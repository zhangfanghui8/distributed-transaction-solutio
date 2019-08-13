package com.zfh.dubbo.provider.tcc;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author zfh
 * @since  2019.7.31
 */
@SpringBootApplication
@EnableDubbo
@PropertySource({"classpath:dubbo-provider.properties"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}