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
public class CapacityPolicyDetailResponse {

    private final Long id;
    private final String name;
    private final String insurance_name;
    private final String physical;
    private final String economical;
    private final String environmental;

    public static CapacityPolicyDetailResponse from(CapacityPolicy capacityPolicy) {
        return CapacityPolicyDetailResponse.builder()
                .id(capacityPolicy.getId())
                .name(capacityPolicy.getName())
                .insurance_name(capacityPolicy.getInsurance().getName())
                .physical(capacityPolicy.getPhysical())
                .economical(capacityPolicy.getEconomical())
                .environmental(capacityPolicy.getEnvironmental())
                .build();
    }

}
