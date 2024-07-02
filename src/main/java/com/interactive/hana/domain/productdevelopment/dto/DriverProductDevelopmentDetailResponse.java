package com.interactive.hana.domain.productdevelopment.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.interactive.hana.domain.insurance.domain.DriverLicence;
import com.interactive.hana.domain.productdevelopment.domain.DevelopmentState;
import com.interactive.hana.domain.productdevelopment.domain.DriverDevelopment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DriverProductDevelopmentDetailResponse {

    private final Long id;
    private final String name;
    private final Long payment;
    private final int expirationDate;
    private final DevelopmentState state;
    private final LocalDate DateOfLicenseAcquisition;
    private final DriverLicence driverLicence;

    public static DriverProductDevelopmentDetailResponse from(DriverDevelopment driverDevelopment) {
        return DriverProductDevelopmentDetailResponse.builder()
                .id(driverDevelopment.getId())
                .name(driverDevelopment.getName())
                .payment(driverDevelopment.getPayment())
                .expirationDate(driverDevelopment.getExpirationDate())
                .state(driverDevelopment.getState())
                .DateOfLicenseAcquisition(driverDevelopment.getDateOfLicenseAcquisition())
                .driverLicence(driverDevelopment.getDriverLicence())
                .build();
    }

}
