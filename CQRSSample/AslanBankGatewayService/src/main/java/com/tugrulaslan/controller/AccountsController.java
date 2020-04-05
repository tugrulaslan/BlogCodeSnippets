package com.tugrulaslan.controller;

import com.tugrulaslan.dto.AccountSummaryDto;
import com.tugrulaslan.dto.AccountSummaryWithHistoryDto;
import com.tugrulaslan.dto.TransactionRequestDto;
import com.tugrulaslan.service.AccountService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Validated
public class AccountsController {

    private final AccountService accountService;

    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts/{id}/history")
    @ResponseBody
    public AccountSummaryWithHistoryDto getAccountHistoryByAccountId(@Valid @NotNull @PathVariable Long id) {
        return accountService.retrieveById(id);
    }

    @PostMapping("/transactions/")
    @ResponseBody
    public AccountSummaryDto makeTransaction(@Valid @NotNull @RequestBody TransactionRequestDto transactionRequestDto) {
        return accountService.makeTransaction(transactionRequestDto);
    }
}
