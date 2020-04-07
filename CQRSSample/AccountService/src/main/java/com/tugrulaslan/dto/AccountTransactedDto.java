package com.tugrulaslan.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public final class AccountTransactedDto {
    private final Long accountId;
    private final BigDecimal amount;
    private final BigDecimal currentBalance;
    private final TransactionType type;
    private final LocalDateTime timestamp;
}
