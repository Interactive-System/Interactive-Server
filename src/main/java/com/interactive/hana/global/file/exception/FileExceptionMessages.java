package com.interactive.hana.global.file.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FileExceptionMessages {

    FILE_NOT_FOUND_EXCEPTION_MESSAGE("파일이 존재하지 않습니다."),
    FILE_TO_SAVE_NOT_EXIST_EXCEPTION_MESSAGE("저장할 파일이 존재하지 않습니다.");

    private final String message;

}
