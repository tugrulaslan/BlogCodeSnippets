package com.tugrulaslan.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CertificateEventStreamSource {

    String CERTIFICATE_ORDERED_CHANNEL = "certificateOrderedChannel";
    @Input(CERTIFICATE_ORDERED_CHANNEL)
    SubscribableChannel certificateOrdered();
}
