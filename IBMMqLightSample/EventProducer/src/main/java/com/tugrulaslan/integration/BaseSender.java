package com.tugrulaslan.integration;

import com.ibm.mqlight.api.NonBlockingClient;
import com.ibm.mqlight.api.NonBlockingClientAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseSender {
    private static final Logger logger = LoggerFactory.getLogger(BaseSender.class);
    private final String eventName;
    protected NonBlockingClient nonBlockingClient;

    public BaseSender(String eventName) {
        this.eventName = eventName;
    }

    protected NonBlockingClientAdapter<Void> nonBlockingClientAdaptorOnStartup() {
        return new NonBlockingClientAdapter<Void>() {
            @Override
            public void onStarted(NonBlockingClient client, Void context) {
                super.onStarted(client, context);
                final String logMessage = String.format("%s event has started for production", eventName);
                logger.info(logMessage);
            }
        };
    }
}
