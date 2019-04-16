package com.tugrulaslan.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.tugrulaslan.repository")
@ComponentScan(basePackages = "com.tugrulaslan")
public class TestConfiguration {
}
