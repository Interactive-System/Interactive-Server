package com.interactive.hana.domain.compensation.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CompensationAmountResponse {

    private final Long minAmount;
    private final Long maxAmount;

    public static CompensationAmountResponse from(Long minAmount, Long maxAmount) {
        return new CompensationAmountResponse(minAmount, maxAmount);
    }
}
