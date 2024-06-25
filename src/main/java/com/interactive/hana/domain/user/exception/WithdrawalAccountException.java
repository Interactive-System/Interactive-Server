package com.interactive.hana.domain.user.exception;

public class WithdrawalAccountException extends IllegalArgumentException {

    public WithdrawalAccountException(UserExceptionMessage e) {
        super(e.getMessage());
    }

}
