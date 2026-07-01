package com.munglog.munglog.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    FAIL_SAVE_PROFILE("400", "프로필 저장에 실패하였습니다."),
    FAIL_FIND_BY_ID("400", "상세 정보 조회가 불가능합니다."),
    FAIL_DELETE_PROFILE("400", "프로필 삭제에 실패하였습니다.");

    private final String code;
    private final String message;
}
