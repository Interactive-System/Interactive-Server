package com.interactive.hana.domain.capacitypolicy.exception;

public class AlreadyHaveCapacityPolicyException extends IllegalArgumentException {

    public AlreadyHaveCapacityPolicyException(CapacityPolicyExceptionMessages m) {
        super(m.getMessage());
    }

}
