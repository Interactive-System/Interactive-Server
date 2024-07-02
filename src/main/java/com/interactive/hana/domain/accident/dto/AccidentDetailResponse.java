package com.interactive.hana.domain.accident.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.interactive.hana.domain.accident.domain.Accident;
import com.interactive.hana.domain.accident.domain.AccidentState;
import com.interactive.hana.global.file.file.MyFile;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AccidentDetailResponse {

    private final Long accidentId;
    private final Long contractId;
    private final String insuranceName;
    private final String userName;
    private final String email;
    private final AccidentState state;
    private final List<MyFile> files;
    private final LocalDateTime accidentApplyDate;

    public static AccidentDetailResponse from(Accident accident) {
        return AccidentDetailResponse.builder()
                .accidentId(accident.getId())
                .contractId(accident.getContract().getId())
                .insuranceName(accident.getContract().getInsurance().getName())
                .userName(accident.getContract().getUser().getName())
                .email(accident.getContract().getUser().getEmail())
                .state(accident.getState())
                .files(accident.getFiles())
                .accidentApplyDate(accident.getCreatedDate().toLocalDateTime())
                .build();
    }

}
