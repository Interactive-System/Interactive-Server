package com.interactive.hana.domain.productdevelopment.domain;

import com.interactive.hana.domain.insurance.domain.CarInsurance;
import com.interactive.hana.domain.productdevelopment.dto.CarProductDesignRequest;
import com.interactive.hana.domain.productdevelopment.dto.ProductPlanDevelopmentResponse;
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
public class CarDevelopment extends ProductDevelopment {

    private Long carPrice;
    private LocalDate carReleaseDate;
    private Long drivingDistance;

    @Builder
    public CarDevelopment(String name, Long payment, int expirationDate, Long carPrice, LocalDate carReleaseDate, Long drivingDistance) {
        super(name, payment, expirationDate);
        this.carPrice = carPrice;
        this.carReleaseDate = carReleaseDate;
        this.drivingDistance = drivingDistance;
        changeState(DevelopmentState.PLAN);
        changeApproveSate(ApproveState.NONE);
    }

    public CarDevelopment design(CarProductDesignRequest dto) {
        this.carPrice = dto.getCarPrice();
        this.carReleaseDate = dto.getCarReleaseDate();
        this.drivingDistance = dto.getDrivingDistance();
        changeState(DevelopmentState.DESIGN);
        return this;
    }

    public CarDevelopment authorize() {
        changeState(DevelopmentState.AUTHORIZE);
        changeApproveSate(ApproveState.WAIT);
        return this;
    }

    public CarInsurance approve() {
        changeState(DevelopmentState.APPROVE);
        changeApproveSate(ApproveState.APPROVE);
        return CarInsurance.builder()
                .name(getName())
                .payment(getPayment())
                .expirationDate(getExpirationDate())
                .carPrice(this.carPrice)
                .carReleaseDate(this.carReleaseDate)
                .drivingDistance(this.drivingDistance)
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
