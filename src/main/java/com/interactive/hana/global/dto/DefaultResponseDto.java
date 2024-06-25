package com.interactive.hana.global.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DefaultResponseDto {

    private LocalDateTime responseTime;
    private String message;

    private DefaultResponseDto(String message) {
        this.responseTime = LocalDateTime.now();
        this.message = message;
    }

    public static DefaultResponseDto from(String message) {
        return new DefaultResponseDto(message);
    }

}
