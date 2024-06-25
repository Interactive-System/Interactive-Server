package com.interactive.hana.domain.insurance.dao;

import com.interactive.hana.domain.insurance.domain.Insurance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InsuranceRepository<T extends Insurance> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

    Page<T> findAllByAvailableSale(boolean availableSale, Pageable pageable);

}
