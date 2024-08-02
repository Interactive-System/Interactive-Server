package com.interactive.hana.domain.contract.service;

import com.interactive.hana.domain.contract.dao.ContractRepository;
import com.interactive.hana.domain.contract.domain.Contract;
import com.interactive.hana.domain.contract.domain.UwDueProcessType;
import com.interactive.hana.domain.contract.dto.*;
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

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public ContractPerQuarterResponse getContractPerQuarter() {
        int carFirstQuarterCount = 0;
        int carSecondQuarterCount = 0;
        int carThirdQuarterCount = 0;
        int carFourthQuarterCount = 0;

        int travelFirstQuarterCount = 0;
        int travelSecondQuarterCount = 0;
        int travelThirdQuarterCount = 0;
        int travelFourthQuarterCount = 0;

        List<C> contracts = this.contractRepository.findAll();
        for (C c : contracts) {
            String dType = c.getDtype().toUpperCase();
            Timestamp createdDate = c.getCreatedDate();
            LocalDateTime localDateTime = createdDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            int month = localDateTime.getMonthValue();

            if (dType.equals("CAR")) {
                if (month >= 1 && month <= 3) {
                    carFirstQuarterCount++;
                } else if (month >= 4 && month <= 6) {
                    carSecondQuarterCount++;
                } else if (month >= 7 && month <= 9) {
                    carThirdQuarterCount++;
                } else if (month >= 10 && month <= 12) {
                    carFourthQuarterCount++;
                }
            } else if (dType.equals("TRAVEL")) {
                if (month >= 1 && month <= 3) {
                    travelFirstQuarterCount++;
                } else if (month >= 4 && month <= 6) {
                    travelSecondQuarterCount++;
                } else if (month >= 7 && month <= 9) {
                    travelThirdQuarterCount++;
                } else if (month >= 10 && month <= 12) {
                    travelFourthQuarterCount++;
                }
            }
        }

        ContractPerQuarterResponse response = ContractPerQuarterResponse.from(
                carFirstQuarterCount, carSecondQuarterCount, carThirdQuarterCount, carFourthQuarterCount,
                travelFirstQuarterCount, travelSecondQuarterCount, travelThirdQuarterCount, travelFourthQuarterCount
        );
        return response;
    }

    @Override
    public TopUsernameResponse getTopUsername() {
        List<C> all = this.contractRepository.findAll();
        Map<Long, Integer> userContractCount = new HashMap<>();

        for (C c : all) {
            Long userId = c.getUser().getId();
            userContractCount.put(userId, userContractCount.getOrDefault(userId, 0) + 1);
        }

        Long topUserId = null;
        int maxContracts = 0;

        for (Map.Entry<Long, Integer> entry : userContractCount.entrySet()) {
            if (entry.getValue() > maxContracts) {
                topUserId = entry.getKey();
                maxContracts = entry.getValue();
            }
        }

        if (topUserId != null) {
            User user = userService.findById(topUserId);
            return TopUsernameResponse.from(user.getName());
        } else {
            return TopUsernameResponse.from("");
        }
    }

    @Override
    public List<TopInsuranceResponse> getTopInsurance() {
        List<C> all = contractRepository.findAll();
        int totalContracts = all.size();

        Map<String, Map<String, Long>> dtypeCountMap = all.stream()
                .collect(Collectors.groupingBy(C::getDtype,
                        Collectors.groupingBy(c -> c.getInsurance().getName(), Collectors.counting())));

        List<TopInsuranceResponse> topInsurances = dtypeCountMap.entrySet().stream()
                .flatMap(entry -> entry.getValue().entrySet().stream()
                        .map(innerEntry -> new AbstractMap.SimpleEntry<>(entry.getKey(), innerEntry)))
                .sorted((e1, e2) -> e2.getValue().getValue().compareTo(e1.getValue().getValue()))
                .limit(5)
                .map(entry -> {
                    String dtype = entry.getKey();
                    String insuranceName = entry.getValue().getKey();
                    int count = entry.getValue().getValue().intValue();
                    int percentage = (int) ((double) count / totalContracts * 100);
                    return TopInsuranceResponse.from(dtype, insuranceName, percentage);
                })
                .collect(Collectors.toList());

        return topInsurances;
    }
}
