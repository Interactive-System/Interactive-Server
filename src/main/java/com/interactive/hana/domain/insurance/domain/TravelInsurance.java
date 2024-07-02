package com.interactive.hana.domain.insurance.domain;

import com.interactive.hana.domain.insurance.dto.TravelInsuranceDetailResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("Travel")
@Entity
public class TravelInsurance extends Insurance {

    private SafetyRank safetyRank;

    @Builder
    public TravelInsurance(String name, Long payment, int expirationDate, SafetyRank safetyRank) {
        super(name, payment, expirationDate);
        this.safetyRank = safetyRank;
    }

    @SuppressWarnings("unchecked")
    @Override
    public TravelInsuranceDetailResponse toDetailResponse() {
        return TravelInsuranceDetailResponse.builder()
                .id(this.getId())
                .name(this.getName())
                .payment(this.getPayment())
                .expirationDate(this.getExpirationDate())
                .physical(this.getCapacityPolicy().getPhysical())
                .economical(this.getCapacityPolicy().getEconomical())
                .environmental(this.getCapacityPolicy().getEnvironmental())
                .isAvailableSale(this.isAvailableSale())
                .safetyRank(this.safetyRank)
                .build();
    }
}
