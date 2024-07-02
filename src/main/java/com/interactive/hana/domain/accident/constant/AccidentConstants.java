package com.interactive.hana.domain.accident.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccidentConstants {

    COMPLETE_ACCIDENT_RECEPTION("사고 접수 완료");

    private final String message;

}
