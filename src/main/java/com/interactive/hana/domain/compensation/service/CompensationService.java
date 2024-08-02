package com.interactive.hana.domain.compensation.service;

import com.interactive.hana.domain.compensation.dto.CompensationAmountResponse;
import com.interactive.hana.domain.compensation.dto.CompensationApproveRequest;
import com.interactive.hana.domain.compensation.dto.CompensationTotalAmountResponse;

public interface CompensationService {

    void compensationApprove(CompensationApproveRequest request);

    void compensationReject(Long id);

    CompensationAmountResponse getCompensationAmount();

    CompensationTotalAmountResponse getCompensationTotalAmount();

}
