package com.interactive.hana.domain.accident.exception;

public class AccidentNotFoundException extends IllegalArgumentException {

    public AccidentNotFoundException(AccidentExceptionMessages m) {
        super(m.getMessage());
    }

}
