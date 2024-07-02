package com.interactive.hana.domain.insurance.service;

import com.interactive.hana.domain.insurance.domain.Insurance;
import com.interactive.hana.domain.insurance.dto.InsuranceResponse;
import com.interactive.hana.global.dto.PaginationDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface InsuranceService<DetailRes, T extends Insurance> {

    PaginationDto<List<InsuranceResponse>> listByAvailableSale(Pageable pageable);

    PaginationDto<List<InsuranceResponse>> listByUnAvailableSale(Pageable pageable);

    DetailRes read(Long id);

    T findById(Long id);

    Specification<T> getAvailableSpecification();

    Specification<T> getUnAvailableSpecification();

}
