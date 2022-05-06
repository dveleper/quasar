package com.meli.quasar.infrastructure.entry_points.api_rest.exception;

public class MessageException extends Exception {

    public MessageException(String errorMessage){
        super(errorMessage);
    }
}
