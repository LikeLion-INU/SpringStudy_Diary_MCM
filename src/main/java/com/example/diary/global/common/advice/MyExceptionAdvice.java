package com.example.diary.global.common.advice;

import com.example.diary.global.common.exception.CustomException;
import com.example.diary.global.common.exception.Exception500;
import com.example.diary.global.common.reponse.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MyExceptionAdvice {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customException(CustomException e) { return new ResponseEntity<>(e.body(), e.status());}

    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> serverError(Exception500 e){
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> unknownServerError(Exception e){
        ApiResponse<String> response = ApiResponse.ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unknown ServerError");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}