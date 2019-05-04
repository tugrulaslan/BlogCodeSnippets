package com.tugrulaslan.integration;

import com.ibm.mqlight.api.QOS;

public final class IntegrationConstants {
    public static final String SERVICE = "amqp://virtualcentos:5672";
    public static final QOS QOS_CHOICE = QOS.AT_LEAST_ONCE;
    public static final Boolean AUTO_CONFIRM = Boolean.TRUE;
    public static final long TTL_24_H = 60 * 60 * 24 * 1000;
    public static final String TYPE_LISTENER = "Listener";

    private IntegrationConstants() {
    }
}