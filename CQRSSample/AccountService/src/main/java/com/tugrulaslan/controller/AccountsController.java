package com.tugrulaslan.controller;

import com.tugrulaslan.dto.AccountCreationRequestDto;
import com.tugrulaslan.dto.AccountDto;
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

    @GetMapping("/accounts/{id}")
    @ResponseBody
    public AccountDto getAccountById(@Valid @NotNull @PathVariable Long id) {
        return accountService.retrieveById(id);
    }

    @PostMapping("/accounts/")
    @ResponseBody
    public AccountDto createAccount(@Valid @NotNull @RequestBody AccountCreationRequestDto requestDto) {
        return accountService.create(requestDto);
    }
}
