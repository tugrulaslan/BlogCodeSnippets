package com.tugrulaslan.constants;

public class RabbitConstants {
    public static final String EXCHANGE = "CERTIFICATE_EXCHANGE";
    public static final String ROUTING_KEY = "certificate.request.placed";
    public static final String CERTIFICATE_QUEUE = "CERTIFICATE_REQUEST_PLACED_QUEUE";
    public static final boolean DURABLE_CERTIFICATE_QUEUE = Boolean.TRUE;
}
