package com.psybrainy.menu.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class ErrorResponse {

    private int status;
    private Date timestamp;
    private String message;
    private String path;

    public ErrorResponse(int status, Date timestamp, String message, String path) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        this.path = path;
    };
}
