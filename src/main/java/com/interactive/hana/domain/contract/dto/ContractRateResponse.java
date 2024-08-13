package com.interactive.hana.domain.contract.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContractRateResponse {

    private final int rate;

    public static ContractRateResponse from(int rate) {
        return new ContractRateResponse(rate);
    }
}
