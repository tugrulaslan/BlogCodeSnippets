package com.tugrulaslan.integration;

import com.ibm.mqlight.api.CompletionListener;
import com.ibm.mqlight.api.NonBlockingClient;
import com.ibm.mqlight.api.SendOptions;
import com.tugrulaslan.MockedObjectData;
import com.tugrulaslan.dto.CertificateRequest;
import com.tugrulaslan.dto.CredentialRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CredentialSenderTest {

    @Mock
    private NonBlockingClient nonBlockingClient;

    @InjectMocks
    private CredentialSender credentialSender;

    @Test
    public void shouldSendCertificationRequest() {
        //given-when
        credentialSender.send(MockedObjectData.credentialRequest);

        //then
        ArgumentCaptor<String> serviceArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<CredentialRequest> credentialRequestArgumentCaptor = ArgumentCaptor.forClass(CredentialRequest.class);
        ArgumentCaptor<Map> mapArgumentCaptor = ArgumentCaptor.forClass(Map.class);
        ArgumentCaptor<SendOptions> sendOptionsArgumentCaptor = ArgumentCaptor.forClass(SendOptions.class);
        ArgumentCaptor<CompletionListener> completionListenerArgumentCaptor = ArgumentCaptor.forClass(CompletionListener.class);
        ArgumentCaptor<Object> contextArgumentCaptor = ArgumentCaptor.forClass(Object.class);

        then(nonBlockingClient).should().send(serviceArgumentCaptor.capture(), credentialRequestArgumentCaptor.capture(),
                mapArgumentCaptor.capture(), sendOptionsArgumentCaptor.capture(), completionListenerArgumentCaptor.capture(), contextArgumentCaptor.capture());

        final String capturedService = serviceArgumentCaptor.getValue();
        final CredentialRequest capturedCredentialRequest = credentialRequestArgumentCaptor.getValue();
        final Map capturedOptionsMap = mapArgumentCaptor.getValue();
        final SendOptions capturedSendOptions = sendOptionsArgumentCaptor.getValue();
        final CompletionListener capturedCompletionListener = completionListenerArgumentCaptor.getValue();
        final Object capturedContext = contextArgumentCaptor.getValue();

        assertThat(capturedService).isNotNull();
        assertThat(capturedService).isEqualTo("Security/Credential");
        assertThat(capturedCredentialRequest).isNotNull();
        assertThat(capturedCredentialRequest).isEqualTo(MockedObjectData.credentialRequest);
        assertThat(capturedOptionsMap).isNull();
        assertThat(capturedSendOptions).isNotNull();
        assertThat(capturedSendOptions).isEqualTo(IntegrationConstants.SEND_OPTIONS);
        assertThat(capturedCompletionListener).isNotNull();
        assertThat(capturedContext).isNull();
    }

    private CompletionListener<Void> mockedCompletionListener() {
        return new CompletionListener<Void>() {
            @Override
            public void onSuccess(NonBlockingClient nonBlockingClient, Void aVoid) {
                System.out.println("on test success");
            }

            @Override
            public void onError(NonBlockingClient nonBlockingClient, Void aVoid, Exception e) {
                System.err.println("on test error");
            }
        };
    }
}
