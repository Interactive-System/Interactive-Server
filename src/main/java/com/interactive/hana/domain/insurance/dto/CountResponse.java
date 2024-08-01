package com.interactive.hana.domain.insurance.dto;

import com.interactive.hana.domain.insurance.domain.Insurance;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CountResponse {

    private final int carCount;
    private final int travelCount;

    public static CountResponse from(int carCount, int travelCount) {
        return new CountResponse(carCount, travelCount);
    }
}
