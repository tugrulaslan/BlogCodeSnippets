package com.tugrulaslan.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface AccountStream {

    String ACCOUNT_TRANSACTED_SUBSCRIBABLE_CHANNEL = "accountTransactedSubscribableChannel";
    @Input(ACCOUNT_TRANSACTED_SUBSCRIBABLE_CHANNEL)
    SubscribableChannel accountTransactedSubscribableChannel();
}
