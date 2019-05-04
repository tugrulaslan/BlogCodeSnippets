package com.tugrulaslan.integration;

import com.ibm.mqlight.api.*;
import com.tugrulaslan.dto.CertificateRequest;
import com.tugrulaslan.util.IntegrationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CertificateSender extends BaseSender{
    private static final Logger logger = LoggerFactory.getLogger(CertificateSender.class);

    private static final String EVENT_NAME = "Certificate";
    private static final String TOPIC = "Security/".concat(EVENT_NAME);

    private final ClientOptions clientOptions = ClientOptions.builder()
            .setId(IntegrationUtils.buildClientId(EVENT_NAME, IntegrationConstants.TYPE_PRODUCER))
            .build();

    public CertificateSender() {
        super(EVENT_NAME);
    }

    @PostConstruct
    public void init() {
        nonBlockingClient = NonBlockingClient.create(IntegrationConstants.SERVICE, clientOptions, nonBlockingClientAdaptorOnStartup(), null);
    }

    public void send(CertificateRequest certificateRequest) {
        nonBlockingClient.send(TOPIC, certificateRequest, null, IntegrationConstants.SEND_OPTIONS, new CompletionListener<Void>() {
            public void onSuccess(NonBlockingClient nonBlockingClient, Void context) {
                logger.info("Certification request has been sent");
            }
            public void onError(NonBlockingClient nonBlockingClient, Void context, Exception exception) {
                logger.error("Error while sending the request: ", exception);
            }
        }, null);
    }
}
