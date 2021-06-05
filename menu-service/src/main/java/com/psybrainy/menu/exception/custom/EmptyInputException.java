package com.psybrainy.menu.exception.custom;

public class EmptyInputException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public EmptyInputException(String stringError) {
        super(stringError);
    }
}
