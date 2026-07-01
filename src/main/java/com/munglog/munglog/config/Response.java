package com.munglog.munglog.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {

    // 결과 코드
    private String code;

    // 결과 메시지
    private String message;

    // 데이터
    private T data;

    public static <T> Response<T> success(T data, String message) {
        Response<T> res = new Response<>();
        res.code = "SUCCESS";
        res.message = message;
        res.data = data;
        return res;
    }

    public static <T> Response<T> error(String code, String message) {
        Response<T> res = new Response<>();
        res.code = code;
        res.message = message;
        return res;
    }

    public static <T> Response<T> resultData(T data, String code, String message) {
        Response<T> res = new Response<>();
        res.code = code;
        res.message = message;
        res.data = data;
        return res;
    }

    public static <T> Response<T> result(String code, String message) {
        Response<T> res = new Response<>();
        res.code = code;
        res.message = message;
        return res;
    }

    public static <T> Response<T> result(ErrorCode errorCode) {
        Response<T> res = new Response<>();
        res.code = errorCode.getCode();
        res.message = errorCode.getMessage();
        return res;
    }
}
