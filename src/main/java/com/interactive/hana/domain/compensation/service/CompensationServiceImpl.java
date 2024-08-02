package com.interactive.hana.domain.compensation.service;

import com.interactive.hana.domain.accident.domain.Accident;
import com.interactive.hana.domain.accident.service.AccidentService;
import com.interactive.hana.domain.compensation.dao.CompensationRepository;
import com.interactive.hana.domain.compensation.domain.Compensation;
import com.interactive.hana.domain.compensation.dto.CompensationAmountResponse;
import com.interactive.hana.domain.compensation.dto.CompensationApproveRequest;
import com.interactive.hana.domain.compensation.dto.CompensationTotalAmountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CompensationServiceImpl implements CompensationService {

    private final CompensationRepository compensationRepository;
    private final AccidentService accidentService;

    @Transactional
    @Override
    public void compensationApprove(CompensationApproveRequest request) {
        Accident accident = this.accidentService.findById(request.getAccidentId());
        Compensation compensation = this.compensationRepository.save(Compensation.of(request.getCompensationAmount(), accident));
        accident.compensationApprove(compensation);
    }

    @Transactional
    @Override
    public void compensationReject(Long id) {
        this.accidentService.findById(id).compensationReject();
    }

    @Override
    public CompensationAmountResponse getCompensationAmount() {
        List<Compensation> all = this.compensationRepository.findAll();

        if (all.isEmpty()) {
            return CompensationAmountResponse.from(0L, 0L);
        }

        Long maxAmount = 0L;
        Long minAmount = 0L;

        for (Compensation compensation : all) {
            Long compensationAmount = compensation.getCompensationAmount();
            if (compensationAmount > maxAmount) {
                maxAmount = compensationAmount;
            }
            if (compensationAmount < minAmount) {
                minAmount = compensationAmount;
            }
        }

        return CompensationAmountResponse.from(maxAmount, minAmount);
    }

    @Override
    public CompensationTotalAmountResponse getCompensationTotalAmount() {
        List<Compensation> all = this.compensationRepository.findAll();
        Long totalAmount = 0L;

        for (Compensation compensation : all) {
            Long compensationAmount = compensation.getCompensationAmount();
            totalAmount += compensationAmount;
        }

        return CompensationTotalAmountResponse.from(totalAmount);
    }
}
