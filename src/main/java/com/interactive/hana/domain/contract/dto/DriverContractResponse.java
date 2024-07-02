package com.interactive.hana.domain.contract.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.interactive.hana.domain.contract.domain.UwDueProcessType;
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
public class DriverContractResponse {

    private final Long id;
    private final String userName;
    private final String email;
    private final Long insuranceId;
    private final String insuranceName;
    private final String customerPhysical;
    private final String customerEconomical;
    private final String customerEnvironmental;
    private final Long calculatedPayment;
    private final LocalDate expirationDate;
    private final UwDueProcessType uwDueProcessType;
    private final LocalDate dateOfLicenseAcquisition;
    private final DriverLicence driverLicence;

}
