package com.tugrulaslan.service;

import com.tugrulaslan.dto.CertificateRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class CertificateProducerService {

    private final RabbitTemplate rabbitTemplate;

    public CertificateProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendCertificateRequest(CertificateRequest certificateRequest) {
        rabbitTemplate.convertAndSend("spring-boot-exchange", certificateRequest);
    }
}
