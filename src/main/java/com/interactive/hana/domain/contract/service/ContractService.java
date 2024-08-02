package com.interactive.hana.domain.contract.service;

import com.interactive.hana.domain.contract.domain.Contract;
import com.interactive.hana.domain.contract.dto.CalculatePaymentResponse;
import com.interactive.hana.domain.contract.dto.ContractPerQuarterResponse;
import com.interactive.hana.domain.contract.dto.UwStateCountResponse;
import com.interactive.hana.domain.insurance.domain.Insurance;
import com.interactive.hana.global.config.security.auth.PrincipalDetails;
import com.interactive.hana.global.dto.PaginationDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContractService<I extends Insurance, CreateReq, Res, C extends Contract<Res>> {

    @Transactional
    void create(PrincipalDetails principal, CreateReq dto);

    CalculatePaymentResponse calculateRequest(PrincipalDetails principal, CreateReq dto);

    Long calculatePayment(CreateReq dto, I entity);

    @Transactional
    PaginationDto<List<Res>> dueProcessWaitList(Pageable pageable);

    Specification<C> getUwDueProcessNoneSpecification();

    Res read(PrincipalDetails principal, Long id);

    Contract<Res> findById(Long id);

    @Transactional
    void uwApprove(PrincipalDetails principal, Long id);

    @Transactional
    void uwReject(PrincipalDetails principal, Long id);

    UwStateCountResponse getUwStateCount();
    ContractPerQuarterResponse getContractPerQuarter();
}
