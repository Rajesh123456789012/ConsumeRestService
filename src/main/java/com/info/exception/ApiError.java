package com.info.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

public class ApiError implements Serializable {

    private HttpStatus status;
    private String message;
    private List<Error> errors;

    public ApiError(HttpStatus status, String message, List<Error> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<Error> getErrors() {
        return errors;
    }
}
