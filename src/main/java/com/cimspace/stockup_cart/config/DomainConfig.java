package com.cimspace.stockup_cart.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.cimspace.stockup_cart")
@EnableJpaRepositories("com.cimspace.stockup_cart")
@EnableTransactionManagement
public class DomainConfig {
}
