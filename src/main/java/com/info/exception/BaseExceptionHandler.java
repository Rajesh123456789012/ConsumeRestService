package com.info.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebInputException;

import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class BaseExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ApiError handle(BusinessException businessException) {

       /* HttpStatus httpStatus = Optional.ofNullable(businessException.getStatus()).orElse(HttpStatus.INTERNAL_SERVER_ERROR);
        exchange.getResponse().setStatusCode(httpStatus);*/

        return new ApiError(HttpStatus.NOT_FOUND, businessException.getMessage(), businessException.getErrors());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handle(Exception exception) {
        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(),null);
    }

    /*@ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ApiError handle(WebExchangeBindException webExchangeBindException) {
        List<Error> errors = new ArrayList<>();
        for (FieldError fieldError : webExchangeBindException.getBindingResult().getFieldErrors()){
            Error error = new Error();
            error.setCode(ErrorCode.APP1400.getCode());
            error.setMessage("Input is missed");
            errors.add(error);
        }

        *//*for (ObjectError objectError : webExchangeBindException.getBindingResult().getGlobalErrors()){

            Error error = new Error();
            error.setCode(ErrorCode.APP1400.getCode());
            error.setMessage(objectError.getObjectName()+":"+ objectError.getDefaultMessage());
            errors.add(error);
        }*//*
        return new ApiError(HttpStatus.BAD_REQUEST, ErrorCode.APP1400.getMessage(), errors);
    }*/

    @ExceptionHandler(ServerWebInputException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ApiError handle(ServerWebInputException ex) {
        List<Error> errors = new ArrayList<>();

            Error error = new Error();
            error.setCode(ErrorCode.APP1400.getCode());
            error.setMessage(ex.getMessage());
            errors.add(error);


        /*for (ObjectError objectError : webExchangeBindException.getBindingResult().getGlobalErrors()){

            Error error = new Error();
            error.setCode(ErrorCode.APP1400.getCode());
            error.setMessage(objectError.getObjectName()+":"+ objectError.getDefaultMessage());
            errors.add(error);
        }*/
        return new ApiError(HttpStatus.BAD_REQUEST, ErrorCode.APP1400.getMessage(), errors);
    }
}
