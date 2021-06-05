package com.psybrainy.menu.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ForbiddenException(String stringError) {
        super(stringError);
    }

}
