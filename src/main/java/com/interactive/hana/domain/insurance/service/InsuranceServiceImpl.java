package com.interactive.hana.domain.insurance.service;

import com.interactive.hana.domain.insurance.dao.InsuranceRepository;
import com.interactive.hana.domain.insurance.domain.Insurance;
import com.interactive.hana.domain.insurance.domain.InsuranceType;
import com.interactive.hana.domain.insurance.dto.CountResponse;
import com.interactive.hana.domain.insurance.dto.InsuranceResponse;
import com.interactive.hana.domain.insurance.exception.InsuranceExceptionMessages;
import com.interactive.hana.domain.insurance.exception.InsuranceNotFoundException;
import com.interactive.hana.global.dto.PaginationDto;
import com.interactive.hana.global.util.ListSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public abstract class InsuranceServiceImpl<DetailRes, T extends Insurance> implements InsuranceService<DetailRes, T> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private InsuranceRepository<T> insuranceRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected ListSpecification<T> specification;


    @Override
    public PaginationDto<List<InsuranceResponse>> listByAvailableSale(Pageable pageable) {
        Specification<T> spec = getAvailableSpecification();
        return getListPagination(pageable, spec);
    }

    @Override
    public PaginationDto<List<InsuranceResponse>> listByUnAvailableSale(Pageable pageable) {
        Specification<T> spec = getUnAvailableSpecification();
        return getListPagination(pageable, spec);
    }

    private PaginationDto<List<InsuranceResponse>> getListPagination(Pageable pageable, Specification<T> spec) {
        Page<T> page = this.insuranceRepository.findAll(spec, pageable);
        List<InsuranceResponse> data = page.get().map(InsuranceResponse::from).collect(Collectors.toList());
        return PaginationDto.of(page, data);
    }

    @Override
    public DetailRes read(Long id) {
        return this.findById(id).toDetailResponse();
    }

    @Override
    public T findById(Long id) {
        return this.insuranceRepository.findById(id)
                .orElseThrow(() -> new InsuranceNotFoundException(InsuranceExceptionMessages.INSURANCE_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Override
    public CountResponse getCount() {
        int carCount = 0, travelCount = 0;
        for (T t : this.insuranceRepository.findAll()) {
            String dtype = t.getDtype();
            if (dtype.toUpperCase().equals(InsuranceType.CAR.name())) {
                carCount++;
            } else if(dtype.toUpperCase().equals(InsuranceType.TRAVEL.name())) {
                travelCount++;
            }
        }
        return CountResponse.from(carCount, travelCount);
    }
}
