package com.tugrulaslan.constants;

import java.util.HashMap;
import java.util.Map;

public final class RabbitConstants {
    private static final String DEAD_EXCHANGE_POSTFIX = "_DEAD";
    private static final String DEAD_ROUTING_KEY_POSTFIX = ".dead";
    private static final int MESSAGE_TIME_TO_LIVE = 160000;

    public static final boolean DURABLE_QUEUE = Boolean.TRUE;
    public static final boolean EXCLUSIVE_QUEUE = Boolean.FALSE;
    public static final boolean AUTO_DELETE = Boolean.FALSE;

    public static final String CERTIFICATE_ROUTING_KEY = "certificate.request.placed";
    public static final String CERTIFICATE_EXCHANGE = "CERTIFICATE_EXCHANGE";
    public static final String CERTIFICATE_QUEUE = "CERTIFICATE_REQUEST_PLACED_QUEUE";

    public static final String CERTIFICATE_DEAD_LETTER_EXCHANGE = CERTIFICATE_EXCHANGE.concat(DEAD_EXCHANGE_POSTFIX);
    public static final String CERTIFICATE_DEAD_LETTER_QUEUE = CERTIFICATE_QUEUE.concat(DEAD_EXCHANGE_POSTFIX);
    public static final String CERTIFICATE_DEAD_LETTER_ROUTING_KEY = CERTIFICATE_ROUTING_KEY.concat(DEAD_ROUTING_KEY_POSTFIX);

    public static final Map<String, Object> CERTIFICATE_QUEUE_OPTIONS = new HashMap<>();

    private RabbitConstants() {
        CERTIFICATE_QUEUE_OPTIONS.put("x-dead-letter-exchange", CERTIFICATE_DEAD_LETTER_EXCHANGE);
        CERTIFICATE_QUEUE_OPTIONS.put("x-dead-letter-routing-key", CERTIFICATE_DEAD_LETTER_ROUTING_KEY);
        CERTIFICATE_QUEUE_OPTIONS.put("x-message-ttl", MESSAGE_TIME_TO_LIVE);
    }
}
