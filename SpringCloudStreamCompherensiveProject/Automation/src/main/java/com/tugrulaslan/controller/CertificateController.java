package com.tugrulaslan.controller;

import com.tugrulaslan.dto.CertificateOrderDto;
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
public class CertificateController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CertificateController.class);

    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @RequestMapping("/certificates")
    @ResponseBody
    public ResponseEntity<Void> createCertificateOrder(@RequestBody CertificateOrderDto certificateOrderDto) {
        LOGGER.info("Received the Certificate Order: {}", certificateOrderDto);
        certificateService.sendCertificateOrder(certificateOrderDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
