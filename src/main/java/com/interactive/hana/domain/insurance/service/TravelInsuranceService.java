package com.interactive.hana.domain.insurance.service;

import com.interactive.hana.domain.insurance.domain.TravelInsurance;
import com.interactive.hana.domain.insurance.dto.TravelInsuranceDetailResponse;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class TravelInsuranceService extends InsuranceServiceImpl<TravelInsuranceDetailResponse, TravelInsurance> {

    @Override
    public Specification<TravelInsurance> getAvailableSpecification() {
        return specification.equalToType("Travel").and(specification.equalToAvailable());
    }

    @Override
    public Specification<TravelInsurance> getUnAvailableSpecification() {
        return specification.equalToType("Travel").and(specification.equalToUnAvailable());
    }

}
