package com.cimspace.stockup_product.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.cimspace.stockup_product")
@EnableJpaRepositories("com.cimspace.stockup_product")
@EnableTransactionManagement
public class DomainConfig {
}
