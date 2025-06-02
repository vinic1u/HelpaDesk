package com.pedroribeiro.helpaai.exceptions;


public class AlreadyRegisteredException extends  RuntimeException{

    public AlreadyRegisteredException(String message) {
        super(message);
    }
}
