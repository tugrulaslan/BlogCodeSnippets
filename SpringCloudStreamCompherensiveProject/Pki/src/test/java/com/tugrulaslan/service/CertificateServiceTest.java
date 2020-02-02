package com.tugrulaslan.service;

import com.tugrulaslan.dto.CertificateOrderDto;
import com.tugrulaslan.entity.Certificate;
import com.tugrulaslan.provider.CertificateOrderProvider;
import com.tugrulaslan.repository.CertificateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class CertificateServiceTest {
    private static final String SHA1 = "sha1";

    @Mock
    private CertificateRepository certificateRepository;

    @InjectMocks
    private CertificateService certificateService;

    @Captor
    private ArgumentCaptor<Certificate> certificateArgumentCaptor;

    @Test
    public void shouldPersistCertificate() {
        //given
        CertificateOrderDto certificateOrderDto = CertificateOrderProvider.provide();
        Certificate certificate = givenCertificate();
        given(certificateRepository.save(any())).willReturn(certificate);

        //when
        certificateService.process(certificateOrderDto);

        //then
        then(certificateRepository).should().save(certificateArgumentCaptor.capture());
        assertCertificate(certificateOrderDto);
    }

    @Test
    public void shouldThrowExceptionWhenAlgorithmIsSha1() {
        //given
        CertificateOrderDto certificateOrderDto = CertificateOrderProvider.provide();
        certificateOrderDto.setAlgorithm(SHA1);

        //when
        Throwable throwable = catchThrowable(() -> certificateService.process(certificateOrderDto));

        //then
        String expectedException = String.format("I don't accept '%s' certificates", SHA1);
        assertThat(throwable).isNotNull();
        assertThat(throwable)
                .isInstanceOf(RuntimeException.class)
                .hasMessage(expectedException);
    }

    private void assertCertificate(CertificateOrderDto certificateOrderDto) {
        Certificate capturedCertificate = certificateArgumentCaptor.getValue();
        assertThat(capturedCertificate).isNotNull();
        assertThat(capturedCertificate.getCommonName()).isEqualTo(certificateOrderDto.getCommonName());
        assertThat(capturedCertificate.getAlgorithm()).isEqualTo(certificateOrderDto.getAlgorithm());
    }

    private Certificate givenCertificate() {
        CertificateOrderDto orderDto = CertificateOrderProvider.provide();
        Certificate certificate = new Certificate();
        certificate.setCommonName(orderDto.getCommonName());
        certificate.setAlgorithm(orderDto.getAlgorithm());
        return certificate;
    }
}