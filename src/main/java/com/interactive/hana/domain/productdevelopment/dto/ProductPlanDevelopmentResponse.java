package com.interactive.hana.domain.productdevelopment.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.interactive.hana.domain.productdevelopment.domain.DevelopmentState;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductPlanDevelopmentResponse {

    private Long id;
    private String name;
    private Long payment;
    private DevelopmentState state;

//    public static ProductPlanDevelopmentResponse from(CarDevelopment entity) {
//        return ProductPlanDevelopmentResponse.builder()
//                .id(entity.getId())
//                .name(entity.getName())
//                .payment(entity.getPayment())
//                .state(entity.getState())
//                .build();
//    }
//
//    public static ProductPlanDevelopmentResponse from(DriverDevelopment entity) {
//        return ProductPlanDevelopmentResponse.builder()
//                .id(entity.getId())
//                .name(entity.getName())
//                .payment(entity.getPayment())
//                .state(entity.getState())
//                .build();
//    }
//
//    public static ProductPlanDevelopmentResponse from(ProductDevelopment entity) {
//        return ProductPlanDevelopmentResponse.builder()
//                .id(entity.getId())
//                .name(entity.getName())
//                .payment(entity.getPayment())
//                .state(entity.getState())
//                .build();
//    }
//
//    public static ProductPlanDevelopmentResponse from(TravelDevelopment entity) {
//        return ProductPlanDevelopmentResponse.builder()
//                .id(entity.getId())
//                .name(entity.getName())
//                .payment(entity.getPayment())
//                .state(entity.getState())
//                .build();
//    }
}
