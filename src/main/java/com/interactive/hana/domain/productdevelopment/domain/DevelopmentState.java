package com.interactive.hana.domain.productdevelopment.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DevelopmentState {

    PLAN("기획"), DESIGN("설계"), AUTHORIZE("인가"), APPROVE("승인");

    private final String value;

}
