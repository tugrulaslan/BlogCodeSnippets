package com.tugrulaslan.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface AccountStream {
    String ACCOUNT_TRANSACTED_CHANNEL = "accountTransactedChannel";

    @Output(ACCOUNT_TRANSACTED_CHANNEL)
    MessageChannel accountTransactedChannel();
}
