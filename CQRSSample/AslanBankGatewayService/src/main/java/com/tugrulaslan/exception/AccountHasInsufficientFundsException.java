package com.tugrulaslan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AccountHasInsufficientFundsException extends RuntimeException {
    public AccountHasInsufficientFundsException(String message) {
        super(message);
    }
}
