package com.tugrulaslan.listener;

import com.tugrulaslan.dto.CertificateOrderDto;
import com.tugrulaslan.entity.Certificate;
import com.tugrulaslan.provider.CertificateOrderProvider;
import com.tugrulaslan.repository.CertificateRepository;
import com.tugrulaslan.stream.TestCertificationOrderEventStreamSource;
import org.awaitility.Awaitility;
import org.awaitility.Duration;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringRunner.class)
public class CertificationOrderEventListenerIT {
    private static final int TIMEOUT_PERIOD = 10;

    @Autowired
    private TestCertificationOrderEventStreamSource testCertificationOrderEventStreamSource;

    @Autowired
    private CertificateRepository certificateRepository;

    @Test
    @Ignore
    public void shouldListenAndHandleCertificationOrderEvent() {
        //given
        CertificateOrderDto certificateOrderDto = CertificateOrderProvider.provide();

        //when
        sendEvent(certificateOrderDto);

        //then
        Awaitility.await()
                .atMost(TIMEOUT_PERIOD, TimeUnit.SECONDS)
                .pollInterval(Duration.ONE_SECOND)
                .untilAsserted(() -> assertPersistedCertificate());
    }

    private void assertPersistedCertificate() {
        CertificateOrderDto certificateOrderDto = CertificateOrderProvider.provide();
        List<Certificate> certificates = certificateRepository.findAll();
        assertThat(certificates).extracting("commonName", "algorithm")
                .containsOnly(tuple(certificateOrderDto.getCommonName(), certificateOrderDto.getAlgorithm()));
    }

    private void sendEvent(CertificateOrderDto certificateOrderDto) {
        Message<CertificateOrderDto> certificateOrderMessage = MessageBuilder
                .withPayload(certificateOrderDto)
                .build();
        testCertificationOrderEventStreamSource.certificateOrderedChannel().send(certificateOrderMessage);
    }
}
