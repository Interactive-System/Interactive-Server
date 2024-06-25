package com.interactive.hana.domain.user.exception;

public class SecondEmailDuplicateException extends IllegalArgumentException {

    public SecondEmailDuplicateException(UserExceptionMessage m) {
        super(m.getMessage());
    }

}
