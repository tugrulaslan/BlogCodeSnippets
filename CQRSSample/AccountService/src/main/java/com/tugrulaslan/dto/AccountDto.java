package com.tugrulaslan.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public final class AccountDto {
    private final Long id;
    private final BigDecimal balance;
}
