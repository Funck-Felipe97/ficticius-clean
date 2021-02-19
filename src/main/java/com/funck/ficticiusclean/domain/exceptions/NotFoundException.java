package com.funck.ficticiusclean.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        this("Recurso não encontrado");
    }

    public NotFoundException(String message) {
        super(message);
    }

}
