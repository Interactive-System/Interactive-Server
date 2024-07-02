package com.interactive.hana.infra.mail.exception;

public class AuthCodeMismatchException extends IllegalArgumentException{

    public AuthCodeMismatchException(EmailExceptionMessage m) {
        super(m.getMessage());
    }

}
