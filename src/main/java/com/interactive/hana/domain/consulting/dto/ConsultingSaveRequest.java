package com.interactive.hana.domain.consulting.dto;

import com.interactive.hana.domain.consulting.domain.Consulting;
import com.interactive.hana.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsultingSaveRequest {

    @Length(max = 65, message = "제목은 최대 65자까지 작성 가능합니다.")
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String contents;

    public Consulting toEntity(User user) {
        return Consulting.builder()
                .title(this.title)
                .contents(this.contents)
                .user(user)
                .build();
    }

}
