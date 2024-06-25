package com.interactive.hana.domain.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserExceptionMessage {

    NOT_LOGIN_EXCEPTION_MESSAGE("로그인 하지 않았습니다. 로그인 후 진행해주시기 바랍니다."),
    PASSWORD_MISMATCH_EXCEPTION_MESSAGE("패스워드가 일치하지 않습니다."),
    PASSWORD_DUPLICATED_EXCEPTION_MESSAGE("기존 패스워드와 동일합니다."),
    USERNAME_NOT_FOUND_EXCEPTION_MESSAGE("해당 유저가 존재하지 않습니다."),
    EMAIL_DUPLICATE_EXCEPTION_MESSAGE("해당 이메일 주소는 이미 회원가입된 이메일 주소입니다."),
    SECOND_EMAIL_DUPLICATE_EXCEPTION_MESSAGE("해당 이메일 주소는 이미 등록된 2차 이메일 주소입니다."),
    EMAIL_NOT_VERIFIED_EXCEPTION_MESSAGE("아직 이메일 인증이 되지 않았습니다. 이메일 인증을 해주시길 바랍니다."),
    EMAIL_NOT_FOUND_EXCEPTION_MESSAGE("해당 이메일 주소는 회원가입되지 않은 이메일 주소입니다."),
    WITHDRAWAL_ACCOUNT_EXCEPTION_MESSAGE("해당 계정은 탈퇴딘 계정입니다."),
    NO_LOGIN_EXCEPTION_MESSAGE("로그인을 해주세요.");

    private final String message;

}
