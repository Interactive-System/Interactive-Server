package com.interactive.hana.domain.contract.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContractPerQuarterResponse {

    private final int carFirstQuarterCount;
    private final int carSecondQuarterCount;
    private final int carThirdQuarterCount;
    private final int carFourthQuarterCount;

    private final int travelFirstQuarterCount;
    private final int travelSecondQuarterCount;
    private final int travelThirdQuarterCount;
    private final int travelFourthQuarterCount;

    public static ContractPerQuarterResponse from(int carFirstQuarterCount, int carSecondQuarterCount,
                                                  int carThirdQuarterCount, int carFourthQuarterCount,
                                                  int travelFirstQuarterCount, int travelSecondQuarterCount,
                                                  int travelThirdQuarterCount, int travelFourthQuarterCount) {
        return new ContractPerQuarterResponse(carFirstQuarterCount, carSecondQuarterCount,
                carThirdQuarterCount, carFourthQuarterCount,
                travelFirstQuarterCount, travelSecondQuarterCount,
                travelThirdQuarterCount, travelFourthQuarterCount
        );
    }
}
