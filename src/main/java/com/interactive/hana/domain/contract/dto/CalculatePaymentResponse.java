package com.interactive.hana.domain.contract.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CalculatePaymentResponse {

    private final Long payment;

    public static CalculatePaymentResponse from(Long payment) {
        return new CalculatePaymentResponse(payment);
    }

}
