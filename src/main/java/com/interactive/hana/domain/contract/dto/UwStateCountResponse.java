package com.interactive.hana.domain.contract.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UwStateCountResponse {

    private final int waitCount;
    private final int approveCount;
    private final int rejectCount;

    public static UwStateCountResponse from(int waitCount, int approveCount, int rejectCount) {
        return new UwStateCountResponse(waitCount, approveCount, rejectCount);
    }
}
