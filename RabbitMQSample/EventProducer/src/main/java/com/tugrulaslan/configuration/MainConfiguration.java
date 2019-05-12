package com.tugrulaslan.configuration;

import com.tugrulaslan.constants.RabbitConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class MainConfiguration {

    @Bean
    Queue certificateQueue() {
        return new Queue(RabbitConstants.CERTIFICATE_QUEUE,
                RabbitConstants.DURABLE_QUEUE,
                RabbitConstants.EXCLUSIVE_QUEUE,
                RabbitConstants.AUTO_DELETE,
                RabbitConstants.CERTIFICATE_QUEUE_OPTIONS
        );
    }

    @Bean
    Queue certificateDeadLetterQueue() {
        return new Queue(RabbitConstants.CERTIFICATE_DEAD_LETTER_QUEUE,
                RabbitConstants.DURABLE_QUEUE,
                RabbitConstants.EXCLUSIVE_QUEUE,
                RabbitConstants.AUTO_DELETE,
                Collections.emptyMap()
        );
    }

    @Bean
    TopicExchange certificateExchange() {
        return new TopicExchange(RabbitConstants.CERTIFICATE_EXCHANGE);
    }

    @Bean
    TopicExchange certificateDeadLetterExchange() {
        return new TopicExchange(RabbitConstants.CERTIFICATE_DEAD_LETTER_EXCHANGE);
    }

    @Bean
    Binding certificateBinding(Queue certificateQueue, TopicExchange certificateExchange) {
        return BindingBuilder.bind(certificateQueue).to(certificateExchange).with(RabbitConstants.CERTIFICATE_ROUTING_KEY);
    }

    @Bean
    Binding certificateDeadLetterBinding(Queue certificateDeadLetterQueue, TopicExchange certificateDeadLetterExchange) {
        return BindingBuilder.bind(certificateDeadLetterQueue).to(certificateDeadLetterExchange).with(RabbitConstants.CERTIFICATE_DEAD_LETTER_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
