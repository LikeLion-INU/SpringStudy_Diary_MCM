package com.example.diary.global.common.exception;

import com.example.diary.global.common.reponse.ApiResponse;
import com.example.diary.global.common.reponse.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private String msg;
    private ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    public CustomException(ErrorCode errorCode, String msg) {
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public ApiResponse<?> body(){
        return ApiResponse.FAILURE(errorCode.getCode(), "CustomException : " + errorCode.getMsg());
    }

    public HttpStatus status(){
        return HttpStatus.BAD_REQUEST;
    }
}