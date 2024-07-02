package com.interactive.hana.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpUserResponse {

    private String email;
    private String message;

    public static SignUpUserResponse of(String email, String message) {
        return SignUpUserResponse.builder()
                .email(email)
                .message(message)
                .build();
    }

}
