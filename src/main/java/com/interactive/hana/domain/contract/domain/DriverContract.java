package com.interactive.hana.domain.contract.domain;

import com.interactive.hana.domain.contract.dto.DriverContractResponse;
import com.interactive.hana.domain.insurance.domain.DriverLicence;
import com.interactive.hana.domain.insurance.domain.Insurance;
import com.interactive.hana.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("Driver")
@Entity
public class DriverContract extends Contract<DriverContractResponse> {

    private LocalDate dateOfLicenseAcquisition;
    private DriverLicence driverLicence;

    @Builder
    public DriverContract(User user, Insurance insurance, String customerPhysical, String customerEconomical,
                          String customerEnvironmental, Long calculatedPayment, LocalDate dateOfLicenseAcquisition, DriverLicence driverLicence) {
        super(user, insurance, customerPhysical, customerEconomical, customerEnvironmental, calculatedPayment);
        this.dateOfLicenseAcquisition = dateOfLicenseAcquisition;
        this.driverLicence = driverLicence;
    }

    @Override
    public DriverContractResponse toResponse() {
        return DriverContractResponse.builder()
                .id(getId())
                .userName(getUser().getName())
                .email(getUser().getEmail())
                .insuranceId(getInsurance().getId())
                .insuranceName(getInsurance().getName())
                .customerPhysical(getCustomerPhysical())
                .customerEconomical(getCustomerEconomical())
                .customerEnvironmental(getCustomerEnvironmental())
                .calculatedPayment(getCalculatedPayment())
                .expirationDate(getExpirationDate())
                .uwDueProcessType(getUwDueProcessType())
                .dateOfLicenseAcquisition(this.getDateOfLicenseAcquisition())
                .driverLicence(this.getDriverLicence())
                .build();
    }
}
