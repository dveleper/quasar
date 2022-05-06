package com.meli.quasar.infrastructure.entrypoints.apirest.exception;

public class MessageException extends Exception {

    public MessageException(String errorMessage){
        super(errorMessage);
    }
}
