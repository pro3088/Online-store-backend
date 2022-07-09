package com.cimspace.stockup_user.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.cimspace.stockup_user")
@EnableJpaRepositories("com.cimspace.stockup_user")
@EnableTransactionManagement
public class DomainConfig {
}
