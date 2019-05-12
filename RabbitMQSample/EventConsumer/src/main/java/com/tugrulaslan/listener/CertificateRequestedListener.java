package com.tugrulaslan.listener;

import com.tugrulaslan.constants.RabbitConstants;
import com.tugrulaslan.dto.CertificateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CertificateRequestedListener {
    private final static Logger logger = LoggerFactory.getLogger(CertificateRequestedListener.class);

    @RabbitListener(queues = {RabbitConstants.CERTIFICATE_QUEUE})
    public void receive(CertificateRequest certificateRequest) {
        logger.info("Received Certification Request: {}", certificateRequest);
        if (certificateRequest.isReject()) {
            final String exceptionMessage = String.format("Producer declared to reject this request. Request Body: %s", certificateRequest);
            logger.error(exceptionMessage);
            throw new AmqpRejectAndDontRequeueException(exceptionMessage);
        }
    }
}
