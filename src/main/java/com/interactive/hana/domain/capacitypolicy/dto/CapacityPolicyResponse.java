package com.interactive.hana.domain.capacitypolicy.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.interactive.hana.domain.capacitypolicy.domain.CapacityPolicy;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CapacityPolicyResponse {

    private final Long id;
    private final String name;
    private final String insuranceName;

    public static CapacityPolicyResponse from(CapacityPolicy capacityPolicy) {
        return CapacityPolicyResponse.builder()
                .id(capacityPolicy.getId())
                .name(capacityPolicy.getName())
                .insuranceName(capacityPolicy.getInsurance().getName())
                .build();
    }

}
