package com.employeedept.api.exception;

import com.employeedept.api.exception.dto.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<Message> handleEmployeeNotFound(GeneralException ex) {
        Message error = new Message(ex.getMessage(), ex.getStatus().name());
        return new ResponseEntity<>(error, ex.getStatus());
    }

}
