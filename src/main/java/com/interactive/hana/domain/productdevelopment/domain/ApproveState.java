package com.interactive.hana.domain.productdevelopment.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApproveState {

    NONE("상태없음"), WAIT("대기"), APPROVE("승인"), REJECT("거절");

    private final String value;

}
