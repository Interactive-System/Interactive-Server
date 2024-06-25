package com.interactive.hana.domain.productdevelopment.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.interactive.hana.domain.productdevelopment.domain.DevelopmentState;
import com.interactive.hana.domain.productdevelopment.domain.FireDevelopment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class FireProductDevelopmentDetailResponse {

    private final Long id;
    private final String name;
    private final Long payment;
    private final int expirationDate;
    private final DevelopmentState state;
    private final Long buildingPrice;
    private final LocalDate constructionDate;
    private final Long siteArea;
    private final Integer numberOfFloors;

    public static FireProductDevelopmentDetailResponse from(FireDevelopment fireDevelopment) {
        return FireProductDevelopmentDetailResponse.builder()
                .id(fireDevelopment.getId())
                .name(fireDevelopment.getName())
                .payment(fireDevelopment.getPayment())
                .expirationDate(fireDevelopment.getExpirationDate())
                .state(fireDevelopment.getState())
                .buildingPrice(fireDevelopment.getBuildingPrice())
                .constructionDate(fireDevelopment.getConstructionDate())
                .siteArea(fireDevelopment.getSiteArea())
                .numberOfFloors(fireDevelopment.getNumberOfFloors())
                .build();
    }


}
