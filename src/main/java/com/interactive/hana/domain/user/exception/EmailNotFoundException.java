package com.interactive.hana.domain.user.exception;

public class EmailNotFoundException extends IllegalArgumentException {

    public EmailNotFoundException(UserExceptionMessage m) {
        super(m.getMessage());
    }

}
