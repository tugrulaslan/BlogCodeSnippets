package com.tugrulaslan.listener;

import com.tugrulaslan.dto.AccountTransactedDto;
import com.tugrulaslan.service.AccountHistoryService;
import com.tugrulaslan.stream.AccountStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(AccountStream.class)
public class AccountTransactedListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountTransactedListener.class);

    private final AccountHistoryService accountHistoryService;

    public AccountTransactedListener(AccountHistoryService accountHistoryService) {
        this.accountHistoryService = accountHistoryService;
    }

    @StreamListener(AccountStream.ACCOUNT_TRANSACTED_SUBSCRIBABLE_CHANNEL)
    public void onEvent(@Payload AccountTransactedDto accountTransactedDto) {
        LOGGER.info("received event {} will be handled", accountTransactedDto);
        accountHistoryService.handleEvent(accountTransactedDto);
        LOGGER.info("handled event", accountTransactedDto);
    }
}
