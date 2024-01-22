package com.aquilesdias.challengebossabox.controller;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiError {

    @Getter
    private List<String> errors;

    public ApiError(String messageError){
        this.errors = Arrays.asList(messageError);
    }

    public ApiError(List<String> messageErrors){
        this.errors = messageErrors;
    }
}
