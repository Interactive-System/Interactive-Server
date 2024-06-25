package com.interactive.hana.domain.insurance.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InsuranceExceptionMessages {

    INSURANCE_NOT_FOUND_EXCEPTION_MESSAGE("해당 보험 상품을 찾을 수 없습니다.");

    private final String message;

}
