package com.interactive.hana.domain.user.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.interactive.hana.domain.user.domain.User;
import com.interactive.hana.domain.user.domain.UserRoleType;
import com.interactive.hana.domain.user.domain.UserStateType;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SignUpUserRequest {

    @Email(message = "이메일 형식에 맞춰 입력해주세요.")
    private String email;

    @NotBlank(message = "패스워드를 입력해주세요.")
    @Size(min = 7, max = 20, message = "패스워드는 7글자 이상 20글자 이하여야 합니다.")
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min = 2, max = 10, message = "이름은 최소 2글자 이상 10글자 이하여야 합니다.")
    private String name;

    @NotBlank(message = "전화번호를 입력해주세요.")
    private String phoneNumber;

    @NotBlank(message = "주소를 입력해주세요.")
    private String address;

    @NotBlank(message = "주민등록번호를 입력해주세요.")
    private String residentRegistrationNumber;

    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(this.email)
                .name(this.name)
                .password(passwordEncoder.encode(this.password))
                .phoneNumber(this.phoneNumber)
                .address(this.address)
                .residentRegistrationNumber(this.residentRegistrationNumber)
                .role(UserRoleType.ROLE_USER)
                .state(UserStateType.WAIT)
                .build();
    }

}

