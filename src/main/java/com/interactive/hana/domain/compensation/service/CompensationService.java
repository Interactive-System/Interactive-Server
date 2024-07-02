package com.interactive.hana.domain.compensation.service;

import com.interactive.hana.domain.compensation.dto.CompensationApproveRequest;

public interface CompensationService {

    void compensationApprove(CompensationApproveRequest request);

    void compensationReject(Long id);

}
