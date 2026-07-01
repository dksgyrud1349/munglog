package com.munglog.munglog.exception;

import com.munglog.munglog.config.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

    private final String code;
    private final String message;
    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.errorCode = errorCode;
    }
}
