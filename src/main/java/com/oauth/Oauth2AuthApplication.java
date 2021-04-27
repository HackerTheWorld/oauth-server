package com.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.oauth.dao")
@EnableTransactionManagement
public class Oauth2AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2AuthApplication.class, args);
    }
}
