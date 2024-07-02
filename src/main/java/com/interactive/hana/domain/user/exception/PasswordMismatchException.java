package com.interactive.hana.domain.user.exception;

public class PasswordMismatchException extends IllegalArgumentException {

    public PasswordMismatchException(UserExceptionMessage m) {
        super(m.getMessage());
    }

}
