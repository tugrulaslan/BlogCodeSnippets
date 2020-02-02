package com.tugrulaslan.configuration;

import com.github.fridujo.rabbitmq.mock.MockConnectionFactory;
import com.tugrulaslan.stream.TestCertificationOrderEventStreamSource;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableBinding(TestCertificationOrderEventStreamSource.class)
public class TestConfiguration {
    @Bean
    @Primary
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(new MockConnectionFactory());
    }
}
