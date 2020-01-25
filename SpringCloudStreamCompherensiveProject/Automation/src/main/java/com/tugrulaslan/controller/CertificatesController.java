package com.tugrulaslan.controller;

import com.tugrulaslan.dto.CertificateOrdered;
import com.tugrulaslan.service.CertificateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CertificatesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CertificatesController.class);

    private final CertificateService certificateService;

    public CertificatesController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @RequestMapping("/certificates")
    @ResponseBody
    public ResponseEntity<Void> createCertificateOrder(@RequestBody CertificateOrdered certificateOrdered) {
        LOGGER.info("Received the Certificate Order: ", certificateOrdered);
        certificateService.sendCertificateOrder(certificateOrdered);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
