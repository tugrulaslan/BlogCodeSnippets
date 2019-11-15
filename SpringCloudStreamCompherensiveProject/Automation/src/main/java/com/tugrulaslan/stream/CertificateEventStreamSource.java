package com.tugrulaslan.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CertificateEventStreamSource {
    @Output("certificateOrderedChannel")
    MessageChannel certificateOrdered();
}
