package com.psybrainy.menu.exception;

import com.psybrainy.menu.exception.custom.BadRequestException;
import com.psybrainy.menu.exception.custom.EmptyInputException;
import com.psybrainy.menu.exception.custom.ForbiddenException;
import javassist.NotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest req) {
        String message= "";
        ErrorResponse errorFound = new ErrorResponse(400, new Date(), message, req.getRequestURI());
        return new ResponseEntity<>(errorFound, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<?> notFoundException(Exception e, HttpServletRequest req){
        ErrorResponse errorFound = new ErrorResponse(404, new Date(), e.getMessage(), req.getRequestURI());
        return new ResponseEntity<>(errorFound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            BadRequestException.class,
            DuplicateKeyException.class,
            MethodArgumentTypeMismatchException.class,
            EmptyInputException.class
    })
    protected ResponseEntity<?> badRequestException(Exception e, HttpServletRequest req){
        ErrorResponse errorFound = new ErrorResponse(400, new Date(), e.getMessage(), req.getRequestURI());
        return new ResponseEntity<>(errorFound, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    protected ResponseEntity<?> forbiddenException(Exception e, HttpServletRequest req){
        ErrorResponse errorFound = new ErrorResponse(403, new Date(), e.getMessage(), req.getRequestURI());
        return new ResponseEntity<>(errorFound, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(HttpClientErrorException.Conflict.class)
    protected ResponseEntity<?> conflictException(Exception e, HttpServletRequest req){
        ErrorResponse errorFound = new ErrorResponse(409, new Date(), e.getMessage(), req.getRequestURI());
        return new ResponseEntity<>(errorFound, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({
            HttpClientErrorException.Unauthorized.class,
            AccessDeniedException.class
    })
    protected void unauthorizedException(Exception e, HttpServletRequest req){
    }

    @ExceptionHandler({
            IOException.class,
            Exception.class
    })
    protected ResponseEntity<?> exception(Exception e, HttpServletRequest req){
        ErrorResponse errorFound = new ErrorResponse(500, new Date(), e.getMessage(), req.getRequestURI());
        return new ResponseEntity<>(errorFound, HttpStatus.BAD_REQUEST);
    }
}
