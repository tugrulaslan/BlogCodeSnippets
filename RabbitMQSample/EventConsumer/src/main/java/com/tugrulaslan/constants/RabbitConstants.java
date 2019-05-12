package com.tugrulaslan.constants;

import java.util.HashMap;
import java.util.Map;

public final class RabbitConstants {
    private static final String DEAD_EXCHANGE_PREFIX = "DEAD_";
    private static final String DEAD_ROUTING_KEY_POSTFIX = ".dead";
    public static final boolean DURABLE_QUEUE = Boolean.TRUE;
    public static final boolean EXCLUSIVE_QUEUE = Boolean.FALSE;
    public static final boolean AUTO_DELETE = Boolean.FALSE;

    public static final String CERTIFICATE_EXCHANGE = "CERTIFICATE_EXCHANGE";
    public static final String CERTIFICATE_ROUTING_KEY = "certificate.request.placed";
    public static final String CERTIFICATE_QUEUE = "CERTIFICATE_REQUEST_PLACED_QUEUE";
    public static final Map<String, Object> CERTIFICATE_QUEUE_OPTIONS = new HashMap<>();

    private RabbitConstants() {
        CERTIFICATE_QUEUE_OPTIONS.put("x-dead-letter-exchange", DEAD_EXCHANGE_PREFIX.concat(CERTIFICATE_EXCHANGE));
        CERTIFICATE_QUEUE_OPTIONS.put("x-dead-letter-routing-key", CERTIFICATE_ROUTING_KEY.concat(DEAD_ROUTING_KEY_POSTFIX));
    }
}
