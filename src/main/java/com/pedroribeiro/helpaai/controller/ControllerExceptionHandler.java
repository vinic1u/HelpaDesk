package com.pedroribeiro.helpaai.controller;

import com.pedroribeiro.helpaai.dtos.exceptions.CustomError;
import com.pedroribeiro.helpaai.dtos.exceptions.CustomFieldError;
import com.pedroribeiro.helpaai.dtos.exceptions.CustomFieldErrorMessage;
import com.pedroribeiro.helpaai.exceptions.AlreadyRegisteredException;
import com.pedroribeiro.helpaai.exceptions.NotAllowedException;
import com.pedroribeiro.helpaai.exceptions.ResourceNotFoundException;
import com.pedroribeiro.helpaai.exceptions.TokenNotValidException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomFieldError> handleMethodArgumentNotValidException(HttpServletRequest req,MethodArgumentNotValidException exception){
        HttpStatus http = HttpStatus.BAD_REQUEST;
        CustomFieldError customFieldError = new CustomFieldError(http.value(),"Corpo de Requisição Invalido!");
        for(FieldError fieldError  :  exception.getFieldErrors()){
            customFieldError.getErrors().add(new CustomFieldErrorMessage(fieldError.getField(),fieldError.getDefaultMessage()));
        }
        return new ResponseEntity<>(customFieldError,http);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> handleResourceNotFoundException(HttpServletRequest req,ResourceNotFoundException exception){
        HttpStatus http = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(http.value(),exception.getMessage());
        return new ResponseEntity<>(customError,http);
    }

    @ExceptionHandler(NotAllowedException.class)
    public ResponseEntity<CustomError> handleNotAllowedException(HttpServletRequest req,NotAllowedException exception){
        HttpStatus http = HttpStatus.UNAUTHORIZED;
        CustomError customError = new CustomError(http.value(),exception.getMessage());
        return new ResponseEntity<>(customError,http);
    }





}
