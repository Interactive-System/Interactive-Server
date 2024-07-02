package com.interactive.hana.domain.productdevelopment.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.interactive.hana.domain.productdevelopment.domain.CarDevelopment;
import com.interactive.hana.domain.productdevelopment.domain.DriverDevelopment;
import com.interactive.hana.domain.productdevelopment.domain.FireDevelopment;
import com.interactive.hana.domain.productdevelopment.domain.TravelDevelopment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductPlanCreateRequest {

    @NotBlank
    private String name;

    @NotNull
    private Long payment;

    @NotNull
    private int expirationDate;

    public CarDevelopment toCarProductDevelopmentEntity() {
        return CarDevelopment.builder()
                .name(this.name)
                .payment(this.payment)
                .expirationDate(this.expirationDate)
                .build();
    }

    public DriverDevelopment toDriverProductDevelopmentEntity() {
        return DriverDevelopment.builder()
                .name(this.name)
                .payment(this.payment)
                .expirationDate(this.expirationDate)
                .build();
    }

    public FireDevelopment toFireProductDevelopmentEntity() {
        return FireDevelopment.builder()
                .name(this.name)
                .payment(this.payment)
                .expirationDate(this.expirationDate)
                .build();
    }

    public TravelDevelopment toTravelProductDevelopmentEntity() {
        return TravelDevelopment.builder()
                .name(this.name)
                .payment(this.payment)
                .expirationDate(this.expirationDate)
                .build();
    }

}
