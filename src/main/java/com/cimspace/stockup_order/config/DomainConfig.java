package com.cimspace.stockup_order.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.cimspace.stockup_order")
@EnableJpaRepositories("com.cimspace.stockup_order")
@EnableTransactionManagement
public class DomainConfig {
}
