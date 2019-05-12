package com.tugrulaslan.controller;

import com.tugrulaslan.dto.CertificateRequest;
import com.tugrulaslan.dto.CredentialRequest;
import com.tugrulaslan.integration.CertificateSender;
import com.tugrulaslan.integration.CredentialSender;
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

    private final CertificateSender certificateSender;
    private final CredentialSender credentialSender;

    public EventProducerController(CertificateSender certificateSender, CredentialSender credentialSender) {
        this.certificateSender = certificateSender;
        this.credentialSender = credentialSender;
    }

    @RequestMapping(value = "/certificate", method = RequestMethod.POST)
    public ResponseEntity<Void> certificateCreateRequest(@RequestBody CertificateRequest certificateRequest) {
        logger.info("received certificate create request with body: {}", certificateRequest);
        certificateSender.send(certificateRequest);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/credential", method = RequestMethod.POST)
    public ResponseEntity<Void> credentialCreateRequest(@RequestBody CredentialRequest credentialRequest) {
        logger.info("received credential create request with body: {} ", credentialRequest);
        credentialSender.send(credentialRequest);
        return ResponseEntity.noContent().build();
    }
}
