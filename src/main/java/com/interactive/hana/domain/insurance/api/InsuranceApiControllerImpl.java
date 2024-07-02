package com.interactive.hana.domain.insurance.api;

import com.interactive.hana.domain.insurance.domain.Insurance;
import com.interactive.hana.domain.insurance.dto.InsuranceResponse;
import com.interactive.hana.domain.insurance.service.InsuranceService;
import com.interactive.hana.global.dto.PaginationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class InsuranceApiControllerImpl<DetailReq, I extends Insurance> implements InsuranceApiController<DetailReq> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private InsuranceService<DetailReq, I> insuranceService;

    @Override
    public ResponseEntity<PaginationDto<List<InsuranceResponse>>> listByAvailableSale(Pageable pageable) {
        return ResponseEntity.ok(this.insuranceService.listByAvailableSale(pageable));
    }

    @Override
    public ResponseEntity<PaginationDto<List<InsuranceResponse>>> listByUnAvailableSale(Pageable pageable) {
        return ResponseEntity.ok(this.insuranceService.listByUnAvailableSale(pageable));
    }

    @Override
    public ResponseEntity<DetailReq> detail(Long id) {
        return ResponseEntity.ok(this.insuranceService.read(id));
    }

}
