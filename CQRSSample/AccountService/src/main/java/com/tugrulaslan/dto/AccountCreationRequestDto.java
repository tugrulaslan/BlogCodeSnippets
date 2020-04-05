package com.tugrulaslan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class AccountCreationRequestDto {
    @JsonProperty("balance")
    private BigDecimal balance;
}
