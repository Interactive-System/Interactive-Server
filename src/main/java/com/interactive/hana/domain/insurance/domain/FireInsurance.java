package com.interactive.hana.domain.insurance.domain;

import com.interactive.hana.domain.insurance.dto.FireInsuranceDetailResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("Fire")
@Entity
public class FireInsurance extends Insurance {

    private Long buildingPrice;
    private LocalDate constructionDate;
    private Long siteArea;
    private Integer numberOfFloors;

    @Builder
    public FireInsurance(String name, Long payment, int expirationDate, Long buildingPrice,
                         LocalDate constructionDate, Long siteArea, Integer numberOfFloors) {
        super(name, payment, expirationDate);
        this.buildingPrice = buildingPrice;
        this.constructionDate = constructionDate;
        this.siteArea = siteArea;
        this.numberOfFloors = numberOfFloors;
    }

    @SuppressWarnings("unchecked")
    @Override
    public FireInsuranceDetailResponse toDetailResponse() {
        return FireInsuranceDetailResponse.builder()
                .id(this.getId())
                .name(this.getName())
                .payment(this.getPayment())
                .expirationDate(this.getExpirationDate())
                .physical(this.getCapacityPolicy().getPhysical())
                .economical(this.getCapacityPolicy().getEconomical())
                .environmental(this.getCapacityPolicy().getEnvironmental())
                .isAvailableSale(this.isAvailableSale())
                .buildingPrice(this.buildingPrice)
                .constructionDate(this.constructionDate)
                .siteArea(this.siteArea)
                .numberOfFloors(this.numberOfFloors)
                .build();
    }
}
