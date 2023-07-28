package com.example.simple_marketplace.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<T> {
    /*TODO:  0 - success
      TODO: -1 - server
      TODO: -2 - database
      TODO: -3 - validation*/
    private String message;
    private int code;
    private boolean success;
    private T data;
    private List<ErrorDto> errors;
}
