package com.interactive.hana.domain.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRoleType {
    ROLE_USER("유저"),
    ROLE_UW("언더라이터"),
    ROLE_INSURANCE_PLANNER("보험설계자"),
    ROLE_INSURANCE_SELLER("보험판매자"),
    ROLE_INSURANCE_COMPENSATION_PLANNER("보험보상기획자"),
    ROLE_INSURANCE_COMPENSATION_HANDLER("보험보상처리자"),
    ROLE_CONTRACT_MANAGER("계약관리자"),
    ROLE_FINANCIAL_SUPERVISORY_SERVICE("금융감독원"),
    ROLE_ADMIN("총관리자");

    private final String name;
}
