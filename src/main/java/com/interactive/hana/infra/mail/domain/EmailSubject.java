package com.interactive.hana.infra.mail.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EmailSubject {

    EMAIL_AUTH_REQUEST("[DogFoot] 회원가입 인증 메일"),
    FIND_PASSWORD_REQUEST("[DogFoot] 임시 비밀번호 안내 메일"),
    FIND_EMAIL_REQUEST("[DogFoot] 이메일 찾기 안내 메일");

    @Getter
    private final String subject;

}
