package com.tugrulaslan.controller;

import com.tugrulaslan.dto.AccountDto;
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
    public AccountDto makeTransaction(@Valid @NotNull @RequestBody TransactionRequestDto transactionRequestDto) {
        return accountService.update(transactionRequestDto);
    }
}
