package com.tugrulaslan.listener;

import com.tugrulaslan.dto.CertificateOrdered;
import com.tugrulaslan.stream.CertificateEventStreamSource;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(CertificateEventStreamSource.class)
public class CertificateOrderedListener {
    @StreamListener(target = CertificateEventStreamSource.CERTIFICATE_ORDERED_CHANNEL)
    public void listen(CertificateOrdered certificateOrdered) {
        System.out.println("Certificate Order Received: " + certificateOrdered);
        if (certificateOrdered.getAlgorithm().equalsIgnoreCase("sha1")) {
            throw new RuntimeException("I dont except sha1 certificates");
        }
    }
}
