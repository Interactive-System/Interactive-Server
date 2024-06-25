package com.interactive.hana.domain.contract.service;

import com.interactive.hana.domain.contract.domain.FireContract;
import com.interactive.hana.domain.contract.dto.FireContractCreateRequest;
import com.interactive.hana.domain.contract.dto.FireContractResponse;
import com.interactive.hana.domain.insurance.domain.FireInsurance;
import com.interactive.hana.domain.insurance.dto.FireInsuranceDetailResponse;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;

@Service
public class FireContractService extends
        ContractServiceImpl<FireContract, FireInsuranceDetailResponse, FireInsurance,
                FireContractCreateRequest, FireContractResponse> {

    @Override
    public Long calculatePayment(FireContractCreateRequest dto, FireInsurance fireInsurance) {
        float rate = 1;
        Long buildingPrice = fireInsurance.getBuildingPrice();
        int floors = dto.getNumberOfFloors();
        Long area = dto.getSiteArea();
        long day = Duration.between(dto.getConstructionDate().atStartOfDay(), LocalDate.now().atStartOfDay()).toDays();
        long criteriaDay = Duration.between(fireInsurance.getConstructionDate().atStartOfDay(), LocalDate.now().atStartOfDay()).toDays();
        if (buildingPrice < fireInsurance.getBuildingPrice() + 300000000) rate += 0.1;
        else if (buildingPrice < fireInsurance.getBuildingPrice() + 600000000) rate += 0.2;
        else if (buildingPrice < fireInsurance.getBuildingPrice() + 90000000) rate += 0.3;
        else rate += 0.4;
        if (day < criteriaDay + 365) rate += 0.1;
        else if (day < criteriaDay + 1095) rate += 0.2;
        else if (day < criteriaDay+ 2190) rate += 0.3;
        else rate += 0.4;
        if (floors < fireInsurance.getNumberOfFloors() + 5) rate += 0.1;
        else if (floors < fireInsurance.getNumberOfFloors() + 10) rate += 0.2;
        else if (floors < fireInsurance.getNumberOfFloors() + 15) rate += 0.3;
        else rate += 0.4;
        if (area < fireInsurance.getSiteArea() + 30) rate += 0.1;
        else if (area < fireInsurance.getNumberOfFloors() + 60) rate += 0.2;
        else if (area < fireInsurance.getNumberOfFloors() + 90) rate += 0.3;
        else rate += 0.4;
        return (long) Math.round(fireInsurance.getPayment() * rate);
    }

    @Override
    public Specification<FireContract> getUwDueProcessNoneSpecification() {
        return Specification.where(specification.equalToType("Fire").and(specification.equalUwDueProcessWait()));
    }
}
