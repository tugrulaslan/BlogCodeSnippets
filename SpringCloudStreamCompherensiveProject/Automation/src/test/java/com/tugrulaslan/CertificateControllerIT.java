package com.tugrulaslan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tugrulaslan.dto.CertificateOrderDto;
import org.awaitility.Awaitility;
import org.awaitility.Duration;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(CertificateOrderedEventListener.class)
public class CertificateControllerIT {
    private static final int TIMEOUT_PERIOD = 10;
    private static final String CERTIFICATE_ORDER_ENDPOINT_URL = "/api/certificates";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    private CertificateOrderedEventListener certificateOrderedEventListener;

    @Captor
    ArgumentCaptor<CertificateOrderDto> certificateOrderedArgumentCaptor;

    @Test
    @Ignore
    public void shouldCreateCertificateOrder() throws Exception {
        //given
        CertificateOrderDto certificateOrderDto = givenCertificateOrder();

        //when
        ResultActions resultActions = whenResultsAction(certificateOrderDto);

        //then
        resultActions.andExpect(status().is(HttpStatus.OK.value()));
        Awaitility.await()
                .atMost(TIMEOUT_PERIOD, TimeUnit.SECONDS)
                .pollInterval(Duration.ONE_SECOND)
                .untilAsserted(() -> assertEventTriggered());
    }

    private void assertEventTriggered() {
        CertificateOrderDto certificateOrderDto = givenCertificateOrder();
        then(certificateOrderedEventListener).should().onEvent(certificateOrderedArgumentCaptor.capture());
        CertificateOrderDto capturedCertificateOrder = certificateOrderedArgumentCaptor.getValue();
        assertThat(capturedCertificateOrder).isNotNull();
        assertThat(capturedCertificateOrder.getCommonName()).isEqualTo(certificateOrderDto.getCommonName());
        assertThat(capturedCertificateOrder.getAlgorithm()).isEqualTo(certificateOrderDto.getAlgorithm());
    }

    private CertificateOrderDto givenCertificateOrder() {
        return CertificateOrderDto.builder()
                .commonName("tugrulaslan.com")
                .algorithm("sha256")
                .build();
    }

    private ResultActions whenResultsAction(CertificateOrderDto certificateOrderDto) throws Exception {
        return mockMvc.perform(get(CERTIFICATE_ORDER_ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(certificateOrderDto)));
    }
}
