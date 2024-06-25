package com.interactive.hana.domain.capacitypolicy.exception;

public class CapacityPolicyNotFoundException extends IllegalArgumentException {

    public CapacityPolicyNotFoundException(CapacityPolicyExceptionMessages m) {
        super(m.getMessage());
    }

}
