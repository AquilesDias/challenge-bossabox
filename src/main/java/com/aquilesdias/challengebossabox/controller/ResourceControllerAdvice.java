package com.aquilesdias.challengebossabox.controller;

import com.aquilesdias.challengebossabox.exception.DuplicateDataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceControllerAdvice {

    @ExceptionHandler(DuplicateDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handlerDuplicateDataException(DuplicateDataException ex){
        String msg = ex.getMessage();
        return new ApiError(msg);
    }

}
