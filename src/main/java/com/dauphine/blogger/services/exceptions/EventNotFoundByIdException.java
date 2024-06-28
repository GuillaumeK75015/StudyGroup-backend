package com.dauphine.blogger.services.exceptions;


import java.util.UUID;

public class EventNotFoundByIdException extends Exception {
    public EventNotFoundByIdException(String msg) {
        super(msg);
    }

    public EventNotFoundByIdException(UUID eventId) {
        super("Event not found with ID: " + eventId);}
}