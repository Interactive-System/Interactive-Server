package com.interactive.hana.domain.contract.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TopUsernameResponse {

    private final String username;

    public static TopUsernameResponse from(String username) {
        return new TopUsernameResponse(username);
    }
}
