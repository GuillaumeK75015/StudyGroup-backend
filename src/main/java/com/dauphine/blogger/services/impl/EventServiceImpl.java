package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.controllers.requestbody.RatingReviewRequest;
import com.dauphine.blogger.models.Event;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.EventReview;
import com.dauphine.blogger.repository.EventRepository;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.EventService;
import com.dauphine.blogger.services.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.services.exceptions.EventNotFoundByIdException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final CategoryService categoryService;

    public EventServiceImpl(EventRepository eventRepository, CategoryService categoryService) {
        this.eventRepository = eventRepository;
        this.categoryService = categoryService;

    }

    @Override
    public List<Event> getAllByCategoryId(UUID categoryId) throws CategoryNotFoundByIdException {
        categoryService.getById(categoryId);
        return eventRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public List<Event> searchEvents(String title, UUID categoryId, String location) {
        return eventRepository.searchEvents(title, categoryId, location);
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getAllByTitleOrContent(String titleOrContent) {
        return eventRepository.findAllByTitleOrContent(titleOrContent, titleOrContent);
    }

    public Event getById(UUID id) throws EventNotFoundByIdException {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            return event.get();
        } else {
            throw new EventNotFoundByIdException("Event not found with id: " + id);
        }
    }

    @Override
    public Event create(String title, String content, UUID categoryId, String location, LocalDateTime dateTime, String creatorName)
            throws CategoryNotFoundByIdException {
        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Cannot create an event with a past date.");
        }

        Category category = categoryService.getById(categoryId);
        Event event = new Event(title, content, category, location, dateTime, creatorName);
        return eventRepository.save(event);
    }



    @Override
    public Event update(UUID eventId, String title, String content, String location, LocalDateTime dateTime,UUID categoryId, String lastModifiedBy)
            throws EventNotFoundByIdException, CategoryNotFoundByIdException {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundByIdException(eventId));
        if (event.getDateTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Cannot update an event that has already passed.");
        }
        Category category = categoryService.getById(categoryId);


        event.setTitle(title);
        event.setContent(content);
        event.setLocation(location);
        event.setDateTime(dateTime);
        event.setCategory(category);
        event.setLastModifiedBy(lastModifiedBy);

        return eventRepository.save(event);
    }

    @Override
    public void deleteById(UUID id) throws EventNotFoundByIdException {
        Event event = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundByIdException(id));
        if (event.getDateTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Cannot delete an event that has already passed.");
        }
        eventRepository.deleteById(id);

    }

    @Override
    public Event addParticipant(UUID eventId, String participant) throws EventNotFoundByIdException {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundByIdException(eventId));
        event.getParticipants().add(participant);
        return eventRepository.save(event);
    }

    @Override
    public Event removeParticipant(UUID eventId, String participant) throws EventNotFoundByIdException {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundByIdException(eventId));
        event.getParticipants().remove(participant);
        return eventRepository.save(event);
    }

    @Override
    public Event addRatingReview(UUID eventId, RatingReviewRequest ratingReviewRequest) throws EventNotFoundByIdException {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundByIdException(eventId));
        EventReview review = new EventReview();
        review.setEvent(event);
        review.setRating(ratingReviewRequest.rating());
        review.setReview(ratingReviewRequest.review());
        review.setReviewerName(ratingReviewRequest.reviewerName());
        event.getReviews().add(review);
        return eventRepository.save(event);
    }










}
