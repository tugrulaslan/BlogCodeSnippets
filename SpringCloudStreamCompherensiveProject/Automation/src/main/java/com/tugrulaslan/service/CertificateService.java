package com.tugrulaslan.service;

import com.tugrulaslan.dto.CertificateOrdered;
import com.tugrulaslan.stream.CertificateEventStreamSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(CertificateEventStreamSource.class)
public class CertificateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CertificateService.class);
    private final CertificateEventStreamSource certificateEventStreamSource;

    public CertificateService(CertificateEventStreamSource certificateEventStreamSource) {
        this.certificateEventStreamSource = certificateEventStreamSource;
    }

    public void sendCertificateOrder(CertificateOrdered certificateOrdered) {
        LOGGER.debug("will send the Certificate Order: ", certificateOrdered);
        Message<CertificateOrdered> certificateOrderMessage = MessageBuilder
                .withPayload(certificateOrdered)
                .build();
        certificateEventStreamSource.certificateOrdered().send(certificateOrderMessage);
        LOGGER.info("Sent the Certificate Order Message: ", certificateOrderMessage);
    }
}
