package com.interactive.hana.domain.contract.service;

import com.interactive.hana.domain.contract.dao.ContractRepository;
import com.interactive.hana.domain.contract.domain.Contract;
import com.interactive.hana.domain.contract.domain.UwDueProcessType;
import com.interactive.hana.domain.contract.dto.CalculatePaymentResponse;
import com.interactive.hana.domain.contract.dto.ContractCreateRequest;
import com.interactive.hana.domain.contract.dto.UwStateCountResponse;
import com.interactive.hana.domain.contract.exception.ContractExceptionMessages;
import com.interactive.hana.domain.contract.exception.ContractNotFoundException;
import com.interactive.hana.domain.insurance.domain.Insurance;
import com.interactive.hana.domain.insurance.domain.InsuranceType;
import com.interactive.hana.domain.insurance.dto.CountResponse;
import com.interactive.hana.domain.insurance.service.InsuranceService;
import com.interactive.hana.domain.user.domain.User;
import com.interactive.hana.domain.user.service.UserService;
import com.interactive.hana.global.config.security.auth.PrincipalDetails;
import com.interactive.hana.global.dto.PaginationDto;
import com.interactive.hana.global.util.ListSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Component
public abstract class ContractServiceImpl<C extends Contract<Res>, DetailRes, I extends Insurance,
        CreateReq extends ContractCreateRequest<C, I>, Res> implements ContractService<I, CreateReq, Res, C>{

    @Autowired
    private ContractRepository<C> contractRepository;

    @Autowired
    private InsuranceService<DetailRes, I> insuranceService;

    @Autowired
    private UserService userService;

    @Autowired
    protected ListSpecification<C> specification;

    @Override
    public void create(PrincipalDetails principal, CreateReq dto) {
        User user = userService.findByEmail(principal.getUsername());
        I insurance = insuranceService.findById(dto.getInsuranceId());
        Long payment = calculatePayment(dto, insurance);
        this.contractRepository.save(dto.toEntity(user, insurance, payment));
    }

    @Override
    public CalculatePaymentResponse calculateRequest(PrincipalDetails principal, CreateReq dto) {
        I insurance = insuranceService.findById(dto.getInsuranceId());
        return CalculatePaymentResponse.from(calculatePayment(dto, insurance));
    }

    @Override
    public PaginationDto<List<Res>> dueProcessWaitList(Pageable pageable) {
        Page<C> page = contractRepository.findAll(getUwDueProcessNoneSpecification(), pageable);
        List<Res> data = page.get().map(Contract::toResponse).collect(Collectors.toList());
        return PaginationDto.of(page, data);
    }

    @Override
    public Res read(PrincipalDetails principal, Long id) {
        return findById(id).toResponse();
    }

    @Override
    public void uwApprove(PrincipalDetails principal, Long id) {
        findById(id).uwDueProcessApprove();
    }

    @Override
    public void uwReject(PrincipalDetails principal, Long id) {
        findById(id).uwDueProcessReject();
    }

    @Override
    public Contract<Res> findById(Long id) {
        return contractRepository.findById(id).orElseThrow(() -> new ContractNotFoundException(ContractExceptionMessages.CONTRACT_NOT_FOUND_EXCEPTION));
    }

    @Override
    public UwStateCountResponse getUwStateCount() {
        int waitCount = 0, approveCount = 0, rejectCount = 0;
        for (C c : this.contractRepository.findAll()) {
            UwDueProcessType uwDueProcessType = c.getUwDueProcessType();
            if (uwDueProcessType.name().equals(UwDueProcessType.WAIT.name())) {
                waitCount++;
            } else if(uwDueProcessType.name().equals(UwDueProcessType.APPROVE.name())) {
                approveCount++;
            } else if(uwDueProcessType.name().equals(UwDueProcessType.REJECT.name())) {
                rejectCount++;
            }
        }
        return UwStateCountResponse.from(waitCount, approveCount, rejectCount);
    }
}
