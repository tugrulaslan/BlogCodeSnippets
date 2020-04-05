package com.tugrulaslan.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public final class AccountSummaryDto {
    private final Long accountId;
    private final BigDecimal accountBalance;
}
