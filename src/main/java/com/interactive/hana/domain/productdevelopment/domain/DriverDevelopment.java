package com.interactive.hana.domain.productdevelopment.domain;

import com.interactive.hana.domain.insurance.domain.DriverInsurance;
import com.interactive.hana.domain.insurance.domain.DriverLicence;
import com.interactive.hana.domain.productdevelopment.dto.DriverProductDesignRequest;
import com.interactive.hana.domain.productdevelopment.dto.ProductPlanDevelopmentResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("Driver")
@Entity
public class DriverDevelopment extends ProductDevelopment {

    private LocalDate dateOfLicenseAcquisition;

    @Enumerated(EnumType.STRING)
    private DriverLicence driverLicence;

    @Builder
    public DriverDevelopment(String name, Long payment, int expirationDate, LocalDate dateOfLicenseAcquisition, DriverLicence driverLicence) {
        super(name, payment, expirationDate);
        this.dateOfLicenseAcquisition = dateOfLicenseAcquisition;
        this.driverLicence = driverLicence;
        changeState(DevelopmentState.PLAN);
        changeApproveSate(ApproveState.NONE);
    }

    public DriverDevelopment design(DriverProductDesignRequest dto) {
        this.dateOfLicenseAcquisition = dto.getDateOfLicenseAcquisition();
        this.driverLicence = dto.getDriverLicence();
        changeState(DevelopmentState.DESIGN);
        return this;
    }

    public DriverDevelopment authorize() {
        changeState(DevelopmentState.AUTHORIZE);
        return this;
    }

    public DriverInsurance approve() {
        changeState(DevelopmentState.APPROVE);
        changeApproveSate(ApproveState.APPROVE);
        return DriverInsurance.builder()
                .name(getName())
                .payment(getPayment())
                .expirationDate(getExpirationDate())
                .dateOfLicenseAcquisition(this.dateOfLicenseAcquisition)
                .driverLicence(this.driverLicence)
                .build();
    }

    @Override
    public ProductPlanDevelopmentResponse toResponse() {
        return ProductPlanDevelopmentResponse.builder()
                .id(this.getId())
                .name(this.getName())
                .payment(this.getPayment())
                .state(this.getState())
                .build();
    }

}
