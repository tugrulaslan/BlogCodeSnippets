package com.tugrulaslan.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public final class AccountTransactedDto {
    private final Long accountId;
    private final BigDecimal balance;
    private final TransactionType type;
    private final LocalDateTime timestamp;
}
