package com.carcatalog.exception;

public class CarAlreadyExistsException extends RuntimeException {
    public CarAlreadyExistsException(String msg){
        super(msg);
    }
}
