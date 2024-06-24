package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Event;

import com.dauphine.blogger.services.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.services.exceptions.EventNotFoundByIdException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventService {

    List<Event> getAllByCategoryId(UUID categoryId) throws CategoryNotFoundByIdException;

    List<Event> getAll();

    List<Event> getAllByTitleOrContent(String titleOrContent);

    List<Event> searchEvents(String title, UUID categoryId, String location);


    Event getById(UUID id) throws EventNotFoundByIdException;

    Event create(String title, String content, UUID categoryId, String location, LocalDateTime dateTime, UUID uuid) throws CategoryNotFoundByIdException;

    Event update(UUID id, String title, String content) throws EventNotFoundByIdException;

    void deleteById(UUID id) throws EventNotFoundByIdException;
}