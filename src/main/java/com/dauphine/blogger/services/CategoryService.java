package com.dauphine.blogger.services;

import java.util.List;
import com.dauphine.blogger.services.exceptions.CategoryNotFoundByIdException;
import java.util.UUID;

import com.dauphine.blogger.models.Category;

public interface CategoryService {

    List<Category> getAll();

    List<Category> getAllByName(String name);

    Category getById(UUID id) throws CategoryNotFoundByIdException;

    Category create(String name);

    Category updateName(UUID id, String name) throws CategoryNotFoundByIdException;
    
    void deleteById(UUID id) throws CategoryNotFoundByIdException;
}
