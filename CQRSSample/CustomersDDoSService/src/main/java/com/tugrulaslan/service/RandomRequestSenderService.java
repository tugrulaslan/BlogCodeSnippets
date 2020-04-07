package com.tugrulaslan.service;

import com.tugrulaslan.dto.AccountSummaryDto;
import com.tugrulaslan.dto.TransactionRequestDto;
import com.tugrulaslan.dto.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class RandomRequestSenderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomRequestSenderService.class);
    private final static int MIN_ACCOUNT_ID_NUMBER = 1;
    private final static int MAX_ACCOUNT_ID_NUMBER = 4;
    private final static int MIN_AMOUNT = 1;
    private final static int MAX_AMOUNT = 150;

    private final AccountService accountService;
    private TransactionType lastTransactionType = TransactionType.DEPOSIT;

    public RandomRequestSenderService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void send() {
        while (true) {
            TransactionRequestDto transactionRequestDto = createRandomTransactionRequest();
            LOGGER.info("Will send Transaction Request: {}", transactionRequestDto);
            AccountSummaryDto accountSummary = accountService.makeTransaction(transactionRequestDto);
            LOGGER.info("Send Transaction Request: {}, Account Summary {}", transactionRequestDto, accountSummary);
        }
    }

    private TransactionRequestDto createRandomTransactionRequest() {
        return TransactionRequestDto
                .builder()
                .type(createTransactionType())
                .accountId(Long.valueOf(createRandomAccountId(MIN_ACCOUNT_ID_NUMBER, MAX_ACCOUNT_ID_NUMBER)))
                .amount(BigDecimal.valueOf(createRandomAccountId(MIN_AMOUNT, MAX_AMOUNT)))
                .build();
    }

    private TransactionType createTransactionType() {
        if (lastTransactionType == TransactionType.DEPOSIT) {
            lastTransactionType = TransactionType.WITHDRAWAL;
        } else {
            lastTransactionType = TransactionType.DEPOSIT;
        }
        return lastTransactionType;
    }

    private int createRandomAccountId(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
