package com.tugrulaslan.integration;

import com.ibm.mqlight.api.*;
import com.ibm.mqlight.api.impl.JsonDeliveryImpl;
import com.tugrulaslan.dto.CertificateRequest;
import com.tugrulaslan.util.IntegrationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CertificateEventListener {
    private final static Logger logger = LoggerFactory.getLogger(CertificateEventListener.class);

    private static final String EVENT_NAME = "Certificate";
    private static final String TOPIC = "Security/".concat(EVENT_NAME);
    private static final String EVENT_SHARE_NAME = EVENT_NAME.concat("Share");

    private NonBlockingClient nonBlockingClient;

    private final SubscribeOptions subscribeOptions = SubscribeOptions.builder()
            .setAutoConfirm(IntegrationConstants.AUTO_CONFIRM)
            .setShare(EVENT_SHARE_NAME)
            .setQos(IntegrationConstants.QOS_CHOICE)
            .build();

    private final ClientOptions clientOptions = ClientOptions.builder()
            .setId(IntegrationUtils.buildClientId(EVENT_NAME, IntegrationConstants.TYPE_LISTENER))
            .build();

    @PostConstruct
    public void init() {
        try {
            nonBlockingClient = NonBlockingClient.create(IntegrationConstants.SERVICE, clientOptions, certificateRequestNonBlockingClientAdapter(), null);
        } catch (Throwable e) {
            logger.error("Error while creating connection: {}", e);
            e.printStackTrace();
        }
    }

    private NonBlockingClientAdapter<CertificateRequest> certificateRequestNonBlockingClientAdapter() {
        return new NonBlockingClientAdapter<CertificateRequest>() {
            @Override
            public void onStarted(NonBlockingClient nonBlockingClient, CertificateRequest context) {
                super.onStarted(nonBlockingClient, context);
                final String log = String.format("%s Listener has been started", EVENT_NAME);
                logger.info(log);
                try {
                    nonBlockingClient.subscribe(TOPIC, subscribeOptions, new DestinationAdapter<CertificateRequest>() {
                        public void onMessage(NonBlockingClient client, CertificateRequest context, Delivery delivery) {
                            logger.info("Received message type is {}", delivery.getType());
                            final CertificateRequest certificateRequest = ((JsonDeliveryImpl) delivery).getData(CertificateRequest.class);
                            logger.info("Received certificate request: {}", certificateRequest);
                        }
                    }, new CompletionListener<CertificateRequest>() {
                        @Override
                        public void onSuccess(NonBlockingClient nonBlockingClient, CertificateRequest context) {
                            final String log = String.format("%s Listener has been successfully subscribed to the %s topic", EVENT_NAME, TOPIC);
                            logger.info(log);
                        }

                        @Override
                        public void onError(NonBlockingClient nonBlockingClient, CertificateRequest context, Exception exception) {
                            final String log = String.format("%s Listener has been successfully subscribed to the %s topic", EVENT_NAME, TOPIC);
                            logger.error(log);
                        }
                    }, null);
                } catch (Throwable e) {
                    logger.info("Exception occurred: {}", e);
                }
            }
        };
    }
}
