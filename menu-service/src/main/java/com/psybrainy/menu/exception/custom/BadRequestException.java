package com.psybrainy.menu.exception.custom;

public class BadRequestException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public BadRequestException(String stringError) {
        super(stringError);
    }
}
