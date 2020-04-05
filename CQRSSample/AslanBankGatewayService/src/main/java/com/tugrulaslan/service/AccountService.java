package com.tugrulaslan.service;

import com.tugrulaslan.dto.AccountSummaryDto;
import com.tugrulaslan.dto.AccountSummaryWithHistoryDto;
import com.tugrulaslan.dto.TransactionRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {
    private static final String accountServiceUrl = "http://localhost:7000/transactions/";
    private static final String accountHistoryServiceUrl = "http://localhost:8000/accounts/%s/history";

    private final RestTemplate restTemplate;

    public AccountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AccountSummaryWithHistoryDto retrieveById(Long id) {
        String url = String.format(accountHistoryServiceUrl, id);
        return restTemplate.getForObject(url, AccountSummaryWithHistoryDto.class);
    }

    public AccountSummaryDto makeTransaction(TransactionRequestDto transactionRequestDto) {
        AccountSummaryDto accountSummary = restTemplate.postForObject(accountServiceUrl,
                transactionRequestDto,
                AccountSummaryDto.class);
        return accountSummary;
    }
}
