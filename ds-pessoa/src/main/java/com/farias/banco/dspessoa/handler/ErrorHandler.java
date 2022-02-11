package com.farias.banco.dspessoa.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.ConstraintViolationException;
import com.farias.banco.dspessoa.handler.exception.DataBaseException;

@ControllerAdvice
public class ErrorHandler {


	@ResponseBody
    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<StandardError> handlerResponseStatus(ResponseStatusException e, HttpServletRequest request) {
        return ResponseEntity
        		.status(e.getStatus())
        		.body(StandardError.builder()
        		        .message(List.of(e.getReason()))
                        .error(e.getStatus().getReasonPhrase())
                        .path(request.getRequestURI())
                        .status(e.getStatus().value())
                        .build());
	}
	
	@ResponseBody
    @ExceptionHandler({Exception.class})
    public ResponseEntity<StandardError> handlerGenericException(Exception e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity
                .status(status)
                .body(StandardError.builder()
                        .status(status.value())
                        .error("Server Error")
                        .message(List.of(e.getMessage()))
                        .path(request.getRequestURI())
                        .build());
    }
	
    @ExceptionHandler({DataBaseException.class})
    public ResponseEntity<StandardError> handleDataBaseException(DataBaseException e, HttpServletRequest request) {
        HttpStatus status = e.getStatus();
        return ResponseEntity
                .status(status)
                .body(StandardError.builder()
                        .status(status.value())
                        .error("Database exception")
                        .message(List.of(e.getMessage()))
                        .path(request.getRequestURI())
                        .build());
    }
    
	
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<StandardError> handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity
                .status(status)
                .body(StandardError.builder()
                        .status(status.value())
                        .error("Database exception")
                        .message(List.of(e.getMessage()))
                        .path(request.getRequestURI())
                        .build());
    }
    
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<StandardError> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity
                .status(status)
                .body(StandardError.builder()
                        .status(status.value())
                        .error("Database exception")
                        .message(List.of(e.getMessage()))
                        .path(request.getRequestURI())
                        .build());
    }

}
