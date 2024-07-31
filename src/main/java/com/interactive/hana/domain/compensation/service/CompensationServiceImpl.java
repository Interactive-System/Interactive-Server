package com.interactive.hana.domain.compensation.service;

import com.interactive.hana.domain.accident.domain.Accident;
import com.interactive.hana.domain.accident.service.AccidentService;
import com.interactive.hana.domain.compensation.dao.CompensationRepository;
import com.interactive.hana.domain.compensation.domain.Compensation;
import com.interactive.hana.domain.compensation.dto.CompensationApproveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}