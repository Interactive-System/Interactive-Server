package com.interactive.hana.domain.contract.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TopInsuranceResponse {

    private final String dtype;
    private final String insuranceName;
    private final int percentage;

    public static TopInsuranceResponse from(String dtype, String insuranceName, int percentage) {
        return new TopInsuranceResponse(dtype, insuranceName, percentage);
    }
}
