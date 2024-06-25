package com.interactive.hana.domain.insurance.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.interactive.hana.domain.insurance.domain.DriverLicence;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DriverInsuranceDetailResponse {

    private final Long id;
    private final String name;
    private final Long payment;
    private final int expirationDate;
    private final String physical;
    private final String economical;
    private final String environmental;
    private final boolean isAvailableSale;
    private final LocalDate dateOfLicenseAcquisition;
    private final DriverLicence driverLicence;

}
