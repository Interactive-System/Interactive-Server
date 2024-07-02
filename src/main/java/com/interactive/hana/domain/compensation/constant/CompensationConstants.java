package com.interactive.hana.domain.compensation.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CompensationConstants {

    COMPLETE_COMPENSATION_PAID("보상 지급 완료"),
    REJECT_COMPENSATION("보상 지급을 거절했습니다");

    private final String message;

}
