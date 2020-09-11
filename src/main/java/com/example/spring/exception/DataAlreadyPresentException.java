package com.example.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DataAlreadyPresentException extends RuntimeException {


    public DataAlreadyPresentException() {
        super("Data already present / user already registered");
    }

}
