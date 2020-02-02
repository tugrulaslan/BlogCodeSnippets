package com.tugrulaslan.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TestCertificationOrderEventStreamSource {

    @Output("certificateOrderedChannel")
    MessageChannel certificateOrderedChannel();
}
