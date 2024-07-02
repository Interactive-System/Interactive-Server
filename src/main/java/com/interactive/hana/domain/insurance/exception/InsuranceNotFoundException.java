package com.interactive.hana.domain.insurance.exception;

public class InsuranceNotFoundException extends IllegalArgumentException {

    public InsuranceNotFoundException(InsuranceExceptionMessages m) {
        super(m.getMessage());
    }

}
