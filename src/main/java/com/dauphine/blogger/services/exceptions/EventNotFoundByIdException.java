package com.dauphine.blogger.services.exceptions;


public class EventNotFoundByIdException extends Exception {
    public EventNotFoundByIdException(String msg) {
        super(msg);
    }
}