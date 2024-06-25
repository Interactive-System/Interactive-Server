package com.interactive.hana.domain.contract.domain;

import com.interactive.hana.domain.contract.dto.CarContractResponse;
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
@DiscriminatorValue("Car")
@Entity
public class CarContract extends Contract<CarContractResponse> {

    private Long customerCarPrice;
    private LocalDate customerCarReleaseDate;
    private Long customerDrivingDistance;

    @Builder
    public CarContract(User user, Insurance insurance, String customerPhysical, String customerEconomical,
                       String customerEnvironmental, Long calculatedPayment, Long customerCarPrice,
                       LocalDate customerCarReleaseDate, Long customerDrivingDistance) {
        super(user, insurance, customerPhysical, customerEconomical, customerEnvironmental, calculatedPayment);
        this.customerCarPrice = customerCarPrice;
        this.customerCarReleaseDate = customerCarReleaseDate;
        this.customerDrivingDistance = customerDrivingDistance;
    }

    @Override
    public CarContractResponse toResponse() {
        return CarContractResponse.builder()
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
                .customerCarPrice(this.customerCarPrice)
                .customerCarReleaseDate(this.customerCarReleaseDate)
                .customerDrivingDistance(this.customerDrivingDistance)
                .build();
    }

}
