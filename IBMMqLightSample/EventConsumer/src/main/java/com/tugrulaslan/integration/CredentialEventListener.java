package com.tugrulaslan.integration;

import com.ibm.mqlight.api.*;
import com.ibm.mqlight.api.impl.JsonDeliveryImpl;
import com.tugrulaslan.dto.CredentialRequest;
import com.tugrulaslan.util.IntegrationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CredentialEventListener {
    private final static Logger logger = LoggerFactory.getLogger(CredentialEventListener.class);

    private static final String EVENT_NAME = "Credential";
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
            nonBlockingClient = NonBlockingClient.create(IntegrationConstants.SERVICE, clientOptions, credentialRequestNonBlockingClientAdapter(), null);
        } catch (Throwable e) {
            logger.error("Error while creating connection: {}", e);
            e.printStackTrace();
        }
    }

    private NonBlockingClientAdapter<CredentialRequest> credentialRequestNonBlockingClientAdapter() {
        return new NonBlockingClientAdapter<CredentialRequest>() {
            @Override
            public void onStarted(NonBlockingClient nonBlockingClient, CredentialRequest context) {
                super.onStarted(nonBlockingClient, context);
                final String log = String.format("%s Listener has been started", EVENT_NAME);
                logger.info(log);
                try {
                    nonBlockingClient.subscribe(TOPIC, subscribeOptions, new DestinationAdapter<CredentialRequest>() {
                        public void onMessage(NonBlockingClient client, CredentialRequest context, Delivery delivery) {
                            logger.info("Received message type is {}", delivery.getType());
                            final CredentialRequest credentialRequest = ((JsonDeliveryImpl) delivery).getData(CredentialRequest.class);
                            logger.info("Received certificate request: {}", credentialRequest);
                        }
                    }, new CompletionListener<CredentialRequest>() {
                        @Override
                        public void onSuccess(NonBlockingClient nonBlockingClient, CredentialRequest context) {
                            final String log = String.format("%s Listener has been successfully subscribed to the %s topic", EVENT_NAME, TOPIC);
                            logger.info(log);
                        }

                        @Override
                        public void onError(NonBlockingClient nonBlockingClient, CredentialRequest context, Exception exception) {
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
