package com.interactive.hana.domain.insurance.exception;

public class DuplicateInsuranceNameException extends IllegalArgumentException {
    public DuplicateInsuranceNameException(String reason) {
        super(reason);
    }
}
