package com.tugrulaslan.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Builder
@Getter
@ToString
public final class AccountSummaryDto {
    private final Long accountId;
    private final BigDecimal accountBalance;
}