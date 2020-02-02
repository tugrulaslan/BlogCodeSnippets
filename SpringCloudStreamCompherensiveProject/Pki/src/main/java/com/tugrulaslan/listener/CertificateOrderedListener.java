package com.tugrulaslan.listener;

import com.tugrulaslan.dto.CertificateOrderDto;
import com.tugrulaslan.service.CertificateService;
import com.tugrulaslan.stream.CertificateEventStreamSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(CertificateEventStreamSource.class)
public class CertificateOrderedListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(CertificateOrderedListener.class);
    private final CertificateService certificateService;

    public CertificateOrderedListener(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @StreamListener(target = CertificateEventStreamSource.CERTIFICATE_ORDERED_CHANNEL)
    public void listen(CertificateOrderDto certificateOrderDto) {
        LOGGER.info("Certificate Order Received: {}", certificateOrderDto);
        certificateService.process(certificateOrderDto);
    }
}
