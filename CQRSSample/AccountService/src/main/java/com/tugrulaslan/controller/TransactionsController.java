package com.tugrulaslan.controller;

import com.tugrulaslan.dto.AccountSummaryDto;
import com.tugrulaslan.dto.TransactionRequestDto;
import com.tugrulaslan.service.AccountService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Validated
public class TransactionsController {

    private final AccountService accountService;

    public TransactionsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/transactions/")
    @ResponseBody
    public AccountSummaryDto makeTransaction(@Valid @NotNull @RequestBody TransactionRequestDto transactionRequestDto) {
        AccountSummaryDto accountSummary = accountService.update(transactionRequestDto);
        return accountSummary;
    }
}
