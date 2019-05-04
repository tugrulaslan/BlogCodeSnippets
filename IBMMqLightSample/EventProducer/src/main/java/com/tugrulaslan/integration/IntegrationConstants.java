package com.tugrulaslan.integration;

import com.ibm.mqlight.api.QOS;
import com.ibm.mqlight.api.SendOptions;

public final class IntegrationConstants {
    public static final String SERVICE = "amqp://virtualcentos:5672";
    public static final QOS QOS_CHOICE = QOS.AT_LEAST_ONCE;
    public static final long TTL_24_H = 60 * 60 * 24 * 1000;
    public static final String TYPE_PRODUCER = "Producer";

    public static final SendOptions SEND_OPTIONS = SendOptions.builder()
            .setTtl(IntegrationConstants.TTL_24_H)
            .setQos(IntegrationConstants.QOS_CHOICE)
            .build();

    private IntegrationConstants() {
    }
}