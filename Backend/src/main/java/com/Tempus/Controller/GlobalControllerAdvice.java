package com.Tempus.Controller;

import com.Tempus.Exceptions.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFound.class)
    public Map<String, String> handlerResourceNotFound(ResourceNotFound resourceNotFound){
        Map<String,String> response = new HashMap<>();

        response.put("Message", resourceNotFound.getMessage());

        return response;
    }
}
