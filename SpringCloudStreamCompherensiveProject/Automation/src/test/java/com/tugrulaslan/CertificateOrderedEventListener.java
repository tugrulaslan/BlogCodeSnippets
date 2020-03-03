package com.tugrulaslan;

import com.tugrulaslan.dto.CertificateOrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(TestEventsStreamSource.class)
public class CertificateOrderedEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(CertificateOrderedEventListener.class);

    @StreamListener(target = TestEventsStreamSource.CERTIFICATE_ORDERED_SUBSCRIBABLE_CHANNEL)
    public void onEvent(CertificateOrderDto certificateOrderDto) {
        LOGGER.info("Received event: {}", certificateOrderDto);
    }
}
