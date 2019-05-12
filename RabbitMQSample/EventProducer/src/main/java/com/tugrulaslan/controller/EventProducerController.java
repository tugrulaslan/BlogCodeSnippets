package com.tugrulaslan.controller;

import com.tugrulaslan.dto.CertificateRequest;
import com.tugrulaslan.producer.CertificateRequestProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EventProducerController {
    private final static Logger logger = LoggerFactory.getLogger(EventProducerController.class);

    private final CertificateRequestProducer certificateRequestProducer;

    public EventProducerController(CertificateRequestProducer certificateRequestProducer) {
        this.certificateRequestProducer = certificateRequestProducer;
    }

    @RequestMapping(value = "/certificate", method = RequestMethod.POST)
    public ResponseEntity<Void> certificateCreateRequest(@RequestBody CertificateRequest certificateRequest) {
        logger.info("received certificate create request with body: {}", certificateRequest);
        certificateRequestProducer.sendCertificateRequest(certificateRequest);
        return ResponseEntity.noContent().build();
    }
}
