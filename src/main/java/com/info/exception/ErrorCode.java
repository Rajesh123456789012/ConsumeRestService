package com.info.exception;

public enum ErrorCode {

    APP1404("1404","Employee ID not found in DB"),
    APP1400("1400","Field error");


    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
