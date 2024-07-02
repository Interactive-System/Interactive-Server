package com.interactive.hana.domain.consulting.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.interactive.hana.domain.consulting.domain.ConsultingAnswer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ConsultingAnswerResponse {

    private final Long id;
    private final String title;
    private final String contents;
    private final String writer;
    private final LocalDate CreationDate;

    public static ConsultingAnswerResponse from(ConsultingAnswer answer) {
        return ConsultingAnswerResponse.builder()
                .id(answer.getId())
                .title(answer.getTitle())
                .contents(answer.getContents())
                .writer("관리자")
                .CreationDate(answer.getCreatedDate().toLocalDateTime().toLocalDate())
                .build();
    }

}
