package com.munglog.munglog.exception;

import com.munglog.munglog.config.ErrorCode;

public class ProFileException extends BusinessException {
    public ProFileException(ErrorCode errorCode) {
        super(errorCode);
    }
}
