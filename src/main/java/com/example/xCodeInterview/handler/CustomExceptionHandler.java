package com.example.xCodeInterview.handler;

import com.example.xCodeInterview.exception.CustomException;
import com.example.xCodeInterview.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    protected ResponseEntity<Object> handleCustomException(CustomException ex) {
        return buildResponse(new ErrorResponse(ex.getHttpStatus(), ex.getMessage()));
    }

    private ResponseEntity<Object> buildResponse(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

}
