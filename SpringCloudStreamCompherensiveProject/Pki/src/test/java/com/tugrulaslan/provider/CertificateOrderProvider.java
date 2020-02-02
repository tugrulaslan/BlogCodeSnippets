package com.tugrulaslan.provider;

import com.tugrulaslan.dto.CertificateOrderDto;

public final class CertificateOrderProvider {

    private CertificateOrderProvider() {
    }

    public static CertificateOrderDto provide() {
        return CertificateOrderDto.builder()
                .algorithm("sha526")
                .commonName("tugrulaslan.com")
                .build();
    }
}
