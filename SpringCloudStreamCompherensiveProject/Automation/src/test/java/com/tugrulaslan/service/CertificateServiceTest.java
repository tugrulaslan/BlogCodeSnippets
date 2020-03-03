package com.tugrulaslan.service;

import com.tugrulaslan.dto.CertificateOrderDto;
import com.tugrulaslan.stream.CertificateEventStreamSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class CertificateServiceTest {

    @Mock
    private CertificateEventStreamSource certificateEventStreamSource;

    @Mock
    private MessageChannel messageChannel;

    @InjectMocks
    private CertificateService certificateService;

    @Captor
    private ArgumentCaptor<Message<CertificateOrderDto>> messageArgumentCaptor;

    @Test
    public void shouldSendCertificateOrder() {
        //given
        CertificateOrderDto certificateOrderDto = givenCertificateOrderDto();
        given(certificateEventStreamSource.certificateOrderedChannel()).willReturn(messageChannel);

        //when
        certificateService.sendCertificateOrder(certificateOrderDto);

        //then
        then(messageChannel).should().send(messageArgumentCaptor.capture());
        Message<CertificateOrderDto> value = messageArgumentCaptor.getValue();
        assertThat(value).isNotNull();
        assertThat(value.getHeaders()).hasSize(2);
        assertThat(value.getPayload()).isEqualTo(certificateOrderDto);
    }

    private CertificateOrderDto givenCertificateOrderDto() {
        return CertificateOrderDto.builder()
                .algorithm("sha526")
                .commonName("tugrulaslan.com")
                .build();
    }
}