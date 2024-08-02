package com.interactive.hana.domain.contract.api;

import com.interactive.hana.domain.contract.constant.ContractConstants;
import com.interactive.hana.domain.contract.domain.Contract;
import com.interactive.hana.domain.contract.dto.*;
import com.interactive.hana.domain.contract.service.ContractService;
import com.interactive.hana.domain.insurance.domain.Insurance;
import com.interactive.hana.global.config.security.auth.PrincipalDetails;
import com.interactive.hana.global.dto.DefaultResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class ContractApiControllerImpl<I extends Insurance, CreateReq, Res, C extends Contract<Res>> implements
        ContractApiController<CreateReq> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ContractService<I, CreateReq, Res, C> contractService;

    @Override
    public ResponseEntity<DefaultResponseDto> apply(PrincipalDetails principal, CreateReq dto) {
        contractService.create(principal, dto);
        return ResponseEntity.ok(DefaultResponseDto.from(ContractConstants.COMPLETE_APPLY_INSURANCE_CONTRACT.getMessage()));
    }

    @Override
    public ResponseEntity<CalculatePaymentResponse> calculatePayment(PrincipalDetails principal, CreateReq dto) {
        return ResponseEntity.ok(contractService.calculateRequest(principal, dto));
    }

    @Override
    public ResponseEntity<UwStateCountResponse> getUwStateCount() {
        return ResponseEntity.ok(this.contractService.getUwStateCount());
    }

    @Override
    public ResponseEntity<ContractPerQuarterResponse> getContractPerQuarter() {
        return ResponseEntity.ok(this.contractService.getContractPerQuarter());
    }

    @Override
    public ResponseEntity<TopUsernameResponse> getTopUsername() {
        return ResponseEntity.ok(this.contractService.getTopUsername());
    }

    @Override
    public ResponseEntity<List<TopInsuranceResponse>> getTopInsurance() {
        return ResponseEntity.ok(this.contractService.getTopInsurance());
    }
}
