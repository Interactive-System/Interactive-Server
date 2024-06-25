package com.interactive.hana.domain.contract.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContractConstants {

    COMPLETE_APPLY_INSURANCE_CONTRACT("보험 신청이 완료되었습니다."),
    APPROVE_CONTRACT("계약을 승인했습니다."),
    REJECT_CONTRACT("계약을 거절했습니다.");


    private final String message;

}
