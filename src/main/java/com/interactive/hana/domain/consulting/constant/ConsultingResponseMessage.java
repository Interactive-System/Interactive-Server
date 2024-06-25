package com.interactive.hana.domain.consulting.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConsultingResponseMessage {

    CONSULTING_SAVE_SUCCESS_MESSAGE("상담 작성이 완료되었습니다.");

    private final String message;

}
