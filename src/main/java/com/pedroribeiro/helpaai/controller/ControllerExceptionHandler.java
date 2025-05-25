package com.pedroribeiro.helpaai.controller;

import com.pedroribeiro.helpaai.dtos.exceptions.CustomError;
import com.pedroribeiro.helpaai.exceptions.AlreadyRegisteredException;
import com.pedroribeiro.helpaai.exceptions.TokenNotValidException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<CustomError> handleAuthenticationException(HttpServletRequest req,AuthenticationException exception){
        HttpStatus http = HttpStatus.UNAUTHORIZED;
        CustomError customError= new CustomError(http.value(),exception.getMessage());
        return new ResponseEntity<>(customError,http);
    }

    @ExceptionHandler(AlreadyRegisteredException.class)
    public ResponseEntity<CustomError> handleUserAlreadyRegisteredException(HttpServletRequest req,AlreadyRegisteredException exception){
        HttpStatus http = HttpStatus.UNAUTHORIZED;
        CustomError customError = new CustomError(http.value(),exception.getMessage());
        return new ResponseEntity<>(customError,http);
    }





}
