package com.interactive.hana.domain.capacitypolicy.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CapacityPolicyExceptionMessages {

    ALREADY_HAVE_CAPACITY_POLICY_EXCEPTION_MESSAGE("해당 보험 상품은 이미 인수 정책을 가지고 있습니다."),
    CAPACITY_POLICY_NOT_FOUND_EXCEPTION_MESSAGE("해당 인수 정책을 찾을 수 없습니다.");

    private final String message;

}
