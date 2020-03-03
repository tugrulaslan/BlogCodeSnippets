package com.tugrulaslan;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface TestEventsStreamSource {

    String CERTIFICATE_ORDERED_SUBSCRIBABLE_CHANNEL = "certificateOrderedSubscribableChannel";

    @Input(CERTIFICATE_ORDERED_SUBSCRIBABLE_CHANNEL)
    SubscribableChannel certificateOrderedSubscribableChannel();
}
