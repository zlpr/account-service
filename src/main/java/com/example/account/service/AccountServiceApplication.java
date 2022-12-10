package com.example.account.service;

import com.example.account.service.service.MetricService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class AccountServiceApplication {
    public static void main(String[] args) {
        var context =  SpringApplication.run(AccountServiceApplication.class, args);
        var ms = context.getBean(MetricService.class);
        ms .createCounter("getAmount");
        ms .createCounter("addAmount");
    }

}
