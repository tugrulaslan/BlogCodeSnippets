package com.tugrulaslan.service;

import com.tugrulaslan.dto.CertificateOrderDto;
import com.tugrulaslan.entity.Certificate;
import com.tugrulaslan.repository.CertificateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CertificateService {
    private static final String SHA1 = "sha1";
    private static final Logger LOGGER = LoggerFactory.getLogger(CertificateService.class);

    private final CertificateRepository certificateRepository;

    public CertificateService(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    public void process(CertificateOrderDto certificateOrderDto) {
        LOGGER.info("will persist certificate order {}", certificateOrderDto);
        certificateAlgorithmCheck(certificateOrderDto);
        persistCertificate(certificateOrderDto);
    }

    private void certificateAlgorithmCheck(CertificateOrderDto certificateOrderDto) {
        if (certificateOrderDto.getAlgorithm().equalsIgnoreCase(SHA1)) {
            throwException(certificateOrderDto.getAlgorithm());
        }
    }

    private void throwException(String algorithm) {
        String message = String.format("I dont except '%s' certificates", algorithm);
        RuntimeException exception = new RuntimeException(message);
        LOGGER.error("Exception occurred ", exception);
        throw exception;
    }

    private void persistCertificate(CertificateOrderDto certificateOrderDto) {
        Certificate certificate = toCertificateEntity(certificateOrderDto);
        Certificate persistedCertificate = certificateRepository.save(certificate);
        LOGGER.info("persisted certificate {}", persistedCertificate);
    }

    private Certificate toCertificateEntity(CertificateOrderDto dto) {
        Certificate certificate = new Certificate();
        certificate.setAlgorithm(dto.getAlgorithm());
        certificate.setCommonName(dto.getCommonName());
        return certificate;
    }
}
