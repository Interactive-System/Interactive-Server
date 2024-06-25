package com.interactive.hana.domain.productdevelopment.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductDevelopmentConstants {

    PLAN_SAVE("기획서를 작성 완료했습니다."),
    DESIGN_SAVE("설계를 작성 완료했습니다."),
    COMPLETE_AUTHORIZE("인가 완료"),
    DELETE_PRODUCT_DEVELOPMENT("해당 상품 개발을 삭제했습니다.");

    private final String message;

}
