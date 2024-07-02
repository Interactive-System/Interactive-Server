package com.interactive.hana.domain.insurance.domain;

import com.interactive.hana.domain.insurance.dto.CarInsuranceDetailResponse;
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
public class CarInsurance extends Insurance {

    private Long carPrice;
    private LocalDate carReleaseDate;
    private Long drivingDistance;

    @Builder
    public CarInsurance(String name, Long payment, int expirationDate, Long carPrice,
                        LocalDate carReleaseDate, Long drivingDistance) {
        super(name, payment, expirationDate);
        this.carPrice = carPrice;
        this.carReleaseDate = carReleaseDate;
        this.drivingDistance = drivingDistance;
    }

    @SuppressWarnings("unchecked")
    @Override
    public CarInsuranceDetailResponse toDetailResponse() {
        return CarInsuranceDetailResponse.builder()
                .id(this.getId())
                .name(this.getName())
                .payment(this.getPayment())
                .expirationDate(this.getExpirationDate())
                .physical(this.getCapacityPolicy().getPhysical())
                .economical(this.getCapacityPolicy().getEconomical())
                .environmental(this.getCapacityPolicy().getEnvironmental())
                .isAvailableSale(this.isAvailableSale())
                .carPrice(this.carPrice)
                .carReleaseDate(this.carReleaseDate)
                .drivingDistance(this.drivingDistance)
                .build();
    }

}
