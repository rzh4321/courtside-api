package com.courtside.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class RegistrationFailedException extends RuntimeException {

    public RegistrationFailedException(String message) {
        super(message);
    }
}
