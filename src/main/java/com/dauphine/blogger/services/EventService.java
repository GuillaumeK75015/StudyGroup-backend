package com.dauphine.blogger.services;

import com.dauphine.blogger.controllers.requestbody.RatingReviewRequest;
import com.dauphine.blogger.models.Category;
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

    Event create(String title, String content, UUID categoryId, String location, LocalDateTime dateTime, String creatorName) throws CategoryNotFoundByIdException;

    Event update(UUID eventId, String title, String content, String location, LocalDateTime dateTime, UUID categoryId, String lastModifiedBy)
            throws EventNotFoundByIdException, CategoryNotFoundByIdException;
    Event addParticipant(UUID eventId, String participant) throws EventNotFoundByIdException;

    Event addRatingReview(UUID eventId, RatingReviewRequest ratingReviewRequest) throws EventNotFoundByIdException;

    Event removeParticipant(UUID eventId, String participant) throws EventNotFoundByIdException;


    void deleteById(UUID id) throws EventNotFoundByIdException;
}