package com.interactive.hana.domain.compensation.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CompensationTotalAmountResponse {

    private final Long totalAmount;

    public static CompensationTotalAmountResponse from(Long totalAmount) {
        return new CompensationTotalAmountResponse(totalAmount);
    }
}
