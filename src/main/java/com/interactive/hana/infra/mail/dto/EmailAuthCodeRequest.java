package com.interactive.hana.infra.mail.dto;

import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class EmailAuthCodeRequest {

    @Email(message = "올바른 E-Mail 주소가 아닙니다.")
    private String email;

}
