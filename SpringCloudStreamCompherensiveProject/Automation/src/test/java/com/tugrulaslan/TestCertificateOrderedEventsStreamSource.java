package com.tugrulaslan;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TestCertificateOrderedEventsStreamSource {
    @Output("certificateOrderedChannel")
    MessageChannel certificateOrderedChannel();
}
