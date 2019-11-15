package com.tugrulaslan.controller;

import com.tugrulaslan.dto.CertificateOrdered;
import com.tugrulaslan.stream.CertificateEventStreamSource;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@EnableBinding(CertificateEventStreamSource.class)
public class CertificatesController {

    private final CertificateEventStreamSource certificateEventStreamSource;

    public CertificatesController(CertificateEventStreamSource certificateEventStreamSource) {
        this.certificateEventStreamSource = certificateEventStreamSource;
    }

    @RequestMapping("/certificates")
    @ResponseBody
    public ResponseEntity<Void> createCertificateOrder(@RequestBody CertificateOrdered certificateOrdered) {
        certificateOrdered.setOrderId(UUID.randomUUID().toString());
        Message<CertificateOrdered> certificateOrder = MessageBuilder
                .withPayload(certificateOrdered)
                .build();
        certificateEventStreamSource.certificateOrdered().send(certificateOrder);
        System.out.println(certificateOrdered.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
