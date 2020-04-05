package com.tugrulaslan.controller;

import com.tugrulaslan.dto.AccountDto;
import com.tugrulaslan.service.AccountHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountHistoryController {

    private final AccountHistoryService accountHistoryService;

    public AccountHistoryController(AccountHistoryService accountHistoryService) {
        this.accountHistoryService = accountHistoryService;
    }

    @GetMapping("/accounts/{id}/history")
    @ResponseBody
    public AccountDto getAccountHistoryByAccountId(@PathVariable Long id) {
        return accountHistoryService.retrieveById(id);
    }
}
