package com.mystore.app.config;

public class NotFoundException extends Exception {
    private String errorCode;
    private String errorMessage;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String errorCode, String errorMessage) {
        super(errorMessage); // Set the exception message
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
