package com.interactive.hana.domain.contract.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.interactive.hana.domain.contract.domain.Contract;
import com.interactive.hana.domain.contract.domain.UwDueProcessType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ContractResponse {
    private final Long contractId;
    private final String insuranceName;
    private final UwDueProcessType approveSate;
    private final LocalDate expirationDate;

    public static ContractResponse from(Contract<?> contract) {
        return ContractResponse.builder()
                .contractId(contract.getId())
                .insuranceName(contract.getInsurance().getName())
                .approveSate(contract.getUwDueProcessType())
                .expirationDate(contract.getExpirationDate())
                .build();
    }
}
