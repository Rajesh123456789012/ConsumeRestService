package com.info.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessException extends  RuntimeException {

    private HttpStatus status;
    private List<Error> errors;
    private Throwable cause;
    private String message;

   // public BusinessException() {}

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(HttpStatus status, String message, List<Error> errors) {
        super(message);
        this.message = message;
        this.status = status;
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
