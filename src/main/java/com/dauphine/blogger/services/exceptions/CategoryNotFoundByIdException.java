package com.dauphine.blogger.services.exceptions;

public class CategoryNotFoundByIdException extends Exception {
    public CategoryNotFoundByIdException(String msg) {
        super(msg);
    }
}
