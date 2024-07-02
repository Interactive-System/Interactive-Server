package com.interactive.hana.domain.contract.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContractExceptionMessages {

    CONTRACT_NOT_FOUND_EXCEPTION("해당 계약을 찾을 수 없습니다.");

    private final String message;

}
