package com.tugrulaslan.producer;

import com.tugrulaslan.constants.RabbitConstants;
import com.tugrulaslan.dto.CertificateRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class CertificateRequestProducer {

    private final RabbitTemplate rabbitTemplate;

    public CertificateRequestProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendCertificateRequest(CertificateRequest certificateRequest) {
        rabbitTemplate.convertAndSend(RabbitConstants.CERTIFICATE_EXCHANGE, RabbitConstants.CERTIFICATE_ROUTING_KEY, certificateRequest);
    }
}
