package com.cimspace.stockup_product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class StockupProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockupProductApplication.class, args);
    }

}
