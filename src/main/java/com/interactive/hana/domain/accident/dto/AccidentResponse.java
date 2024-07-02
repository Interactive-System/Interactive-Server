package com.interactive.hana.domain.accident.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.interactive.hana.domain.accident.domain.Accident;
import com.interactive.hana.domain.accident.domain.AccidentState;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AccidentResponse {

    private final Long accidentId;
    private final Long contractId;
    private final String insuranceName;
    private final String userName;
    private final String email;
    private final AccidentState state;
    private final LocalDateTime accidentApplyDate;

    public static AccidentResponse from(Accident accident) {
        return AccidentResponse.builder()
                .accidentId(accident.getId())
                .contractId(accident.getContract().getId())
                .insuranceName(accident.getContract().getInsurance().getName())
                .userName(accident.getContract().getUser().getName())
                .email(accident.getContract().getUser().getEmail())
                .state(accident.getState())
                .accidentApplyDate(accident.getCreatedDate().toLocalDateTime())
                .build();
    }

}
