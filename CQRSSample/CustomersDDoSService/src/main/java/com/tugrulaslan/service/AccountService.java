package com.tugrulaslan.service;

import com.tugrulaslan.dto.AccountSummaryDto;
import com.tugrulaslan.dto.TransactionRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {
    private static final String accountServiceUrl = "http://localhost:8080/transactions/";

    private final RestTemplate restTemplate;

    public AccountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AccountSummaryDto makeTransaction(TransactionRequestDto transactionRequestDto) {
        AccountSummaryDto accountSummary = restTemplate.postForObject(accountServiceUrl,
                transactionRequestDto,
                AccountSummaryDto.class);
        return accountSummary;
    }
}
