package com.tugrulaslan.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
public final class AccountSummaryWithHistoryDto {
    private final Long id;
    private final BigDecimal balance;
    private final List<AccountHistoryDto> history;
}
