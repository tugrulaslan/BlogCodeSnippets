package com.tugrulaslan.integration;

import com.google.gson.JsonElement;
import com.ibm.mqlight.api.*;
import com.ibm.mqlight.api.impl.JsonDeliveryImpl;
import com.tugrulaslan.util.IntegrationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AllEventsListener {
    private final static Logger logger = LoggerFactory.getLogger(AllEventsListener.class);

    private static final String ALL_EVENT_SYMBOL = "#";
    private static final String ALL_EVENTS_NAME = "AllEvents";
    private static final String TOPIC = "Security/".concat(ALL_EVENT_SYMBOL);
    private static final String EVENT_SHARE_NAME = ALL_EVENTS_NAME.concat("Share");

    private NonBlockingClient nonBlockingClient;

    private final SubscribeOptions subscribeOptions = SubscribeOptions.builder()
            .setAutoConfirm(IntegrationConstants.AUTO_CONFIRM)
            .setShare(EVENT_SHARE_NAME)
            .setQos(IntegrationConstants.QOS_CHOICE)
            .build();

    private final ClientOptions clientOptions = ClientOptions.builder()
            .setId(IntegrationUtils.buildClientId("AllEvents", IntegrationConstants.TYPE_LISTENER))
            .build();

    @PostConstruct
    public void init() {
        try {
            nonBlockingClient = NonBlockingClient.create(IntegrationConstants.SERVICE, clientOptions, voidNonBlockingClientAdapter(), null);
        } catch (Throwable e) {
            logger.error("Error while creating connection: {}", e);
            e.printStackTrace();
        }
    }

    private NonBlockingClientAdapter<Void> voidNonBlockingClientAdapter() {
        return new NonBlockingClientAdapter<Void>() {
            @Override
            public void onStarted(NonBlockingClient nonBlockingClient, Void context) {
                super.onStarted(nonBlockingClient, context);
                final String log = String.format("%s Listener has been started", ALL_EVENTS_NAME);
                logger.info(log);
                try {
                    nonBlockingClient.subscribe(TOPIC, subscribeOptions, new DestinationAdapter<Void>() {
                        public void onMessage(NonBlockingClient client, Void context, Delivery delivery) {
                            logger.info("Received message type is {}", delivery.getType());
                            final JsonElement data = ((JsonDeliveryImpl) delivery).getData();
                            logger.info("Received data : {}", data);
                        }
                    }, new CompletionListener<Void>() {
                        @Override
                        public void onSuccess(NonBlockingClient nonBlockingClient, Void context) {
                            final String log = String.format("%s Listener has been successfully subscribed to the %s topic", ALL_EVENTS_NAME, TOPIC);
                            logger.info(log);
                        }

                        @Override
                        public void onError(NonBlockingClient nonBlockingClient, Void context, Exception exception) {
                            final String log = String.format("%s Listener has been successfully subscribed to the %s topic", ALL_EVENTS_NAME, TOPIC);
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
