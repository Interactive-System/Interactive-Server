package com.interactive.hana.domain.user.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.interactive.hana.domain.user.domain.User;
import com.interactive.hana.domain.user.domain.UserRoleType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserInfoResponse {

    private final String name;
    private final UserRoleType role;
    private final String roleName;

    public static UserInfoResponse from(User user) {
        return UserInfoResponse.builder()
                .name(user.getName())
                .role(user.getRole())
                .roleName(user.getRole().getName())
                .build();
    }

}
