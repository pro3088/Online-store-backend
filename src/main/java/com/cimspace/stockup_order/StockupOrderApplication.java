package com.cimspace.stockup_order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class StockupOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockupOrderApplication.class, args);
    }

}
