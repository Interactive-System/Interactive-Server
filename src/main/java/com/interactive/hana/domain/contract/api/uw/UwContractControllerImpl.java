package com.interactive.hana.domain.contract.api.uw;

import com.interactive.hana.domain.contract.constant.ContractConstants;
import com.interactive.hana.domain.contract.domain.Contract;
import com.interactive.hana.domain.contract.service.ContractService;
import com.interactive.hana.domain.insurance.domain.Insurance;
import com.interactive.hana.global.config.security.auth.PrincipalDetails;
import com.interactive.hana.global.dto.DefaultResponseDto;
import com.interactive.hana.global.dto.PaginationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class UwContractControllerImpl<I extends Insurance, Res, C extends Contract<Res>> implements UwContractController<Res> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ContractService<I, ?, Res, C> contractService;

    @Override
    public ResponseEntity<PaginationDto<List<Res>>> dueProcessWaitList(Pageable pageable) {
        return ResponseEntity.ok(contractService.dueProcessWaitList(pageable));
    }

    @Override
    public ResponseEntity<Res> read(PrincipalDetails principal, Long id) {
        return ResponseEntity.ok(contractService.read(principal, id));
    }

    @Override
    public ResponseEntity<DefaultResponseDto> uwApprove(PrincipalDetails principal, Long id) {
        this.contractService.uwApprove(principal, id);
        return ResponseEntity.ok(DefaultResponseDto.from(ContractConstants.APPROVE_CONTRACT.getMessage()));
    }

    @Override
    public ResponseEntity<DefaultResponseDto> uwReject(PrincipalDetails principal, Long id) {
        this.contractService.uwReject(principal, id);
        return ResponseEntity.ok(DefaultResponseDto.from(ContractConstants.REJECT_CONTRACT.getMessage()));
    }
}
