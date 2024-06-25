package com.interactive.hana.domain.insurance.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CarInsuranceDetailResponse {

    private final Long id;
    private final String name;
    private final Long payment;
    private final int expirationDate;
    private final String physical;
    private final String economical;
    private final String environmental;
    private final boolean isAvailableSale;
    private final Long carPrice;
    private final LocalDate carReleaseDate;
    private final Long drivingDistance;

}
