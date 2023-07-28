package com.example.simple_marketplace.dto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerResource {
    @ExceptionHandler
    public ResponseEntity<ResponseDto<Void>> methodArgumentNotValidException(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest().body(ResponseDto.<Void>builder()
                        .code(-3)
                        .message("Validation error!")
                        .errors(e.getBindingResult().getFieldErrors().stream().map(fieldError -> {
                            String field = fieldError.getField();
                            String rejectedValue = String.valueOf(fieldError.getRejectedValue());
                            String defaultMessage = fieldError.getDefaultMessage();
                            return new ErrorDto(field, String.format("Message :: %s ,rejection-value :: %s", defaultMessage, rejectedValue));
                        }).toList())
                .build());
    }

}
