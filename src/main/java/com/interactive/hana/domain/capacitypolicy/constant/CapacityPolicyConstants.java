package com.interactive.hana.domain.capacitypolicy.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CapacityPolicyConstants {

    COMPLETE_SAVE_CAPACITY_POLICY("인수 정책을 등록 완료 했습니다."),
    COMPLETE_UPDATE_CAPACITY_POLICY("인수 정책을 수정 완료 했습니다."),
    COMPLETE_DELETE_CAPACITY_POLICY("인수 정책을 삭제 완료 했습니다.");

    private final String message;

}
