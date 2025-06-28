package com.employeedept.api.exception;

import org.springframework.http.HttpStatus;

public class GeneralException extends RuntimeException {

    private final HttpStatus status;

    public GeneralException(
            String message,
            HttpStatus status
    ) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
