package com.interactive.hana.domain.contract.exception;

public class ContractNotFoundException extends IllegalArgumentException {

    public ContractNotFoundException(ContractExceptionMessages e) {
        super(e.getMessage());
    }

}
