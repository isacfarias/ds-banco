package com.farias.banco.dspessoa.handler.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class DataBaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final HttpStatus status;

    public DataBaseException(HttpStatus status,  String message) {
        super(message);
        this.status = status;
    }

    public DataBaseException(HttpStatus status, String message, Throwable e) {
        super(message, e);
        this.status = status;
    }
}