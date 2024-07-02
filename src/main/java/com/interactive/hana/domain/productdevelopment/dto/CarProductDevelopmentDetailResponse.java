package com.interactive.hana.domain.productdevelopment.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.interactive.hana.domain.productdevelopment.domain.CarDevelopment;
import com.interactive.hana.domain.productdevelopment.domain.DevelopmentState;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CarProductDevelopmentDetailResponse {

    private final Long id;
    private final String name;
    private final Long payment;
    private final int expirationDate;
    private final DevelopmentState state;
    private final Long carPrice;
    private final LocalDate carReleaseDate;
    private final Long drivingDistance;

    public static CarProductDevelopmentDetailResponse from(CarDevelopment carDevelopment) {
        return CarProductDevelopmentDetailResponse.builder()
                .id(carDevelopment.getId())
                .name(carDevelopment.getName())
                .payment(carDevelopment.getPayment())
                .expirationDate(carDevelopment.getExpirationDate())
                .state(carDevelopment.getState())
                .carPrice(carDevelopment.getCarPrice())
                .carReleaseDate(carDevelopment.getCarReleaseDate())
                .drivingDistance(carDevelopment.getDrivingDistance())
                .build();
    }

}
