package com.tugrulaslan.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
public class AccountHistoryDto {
    private final BigDecimal amount;
    private final TransactionType type;
    private LocalDateTime createdDateTime;
}
