package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.models.Event;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.User;
import com.dauphine.blogger.repository.EventRepository;
import com.dauphine.blogger.repository.UserRepository;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.EventService;
import com.dauphine.blogger.services.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.services.exceptions.EventNotFoundByIdException;
import com.dauphine.blogger.services.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final CategoryService categoryService;
    private final UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, CategoryService categoryService, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.categoryService = categoryService;
        this.userRepository = userRepository;
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

    @Override
    public Event getById(UUID id) throws EventNotFoundByIdException {
        return eventRepository.findById(id).orElseThrow(() -> new EventNotFoundByIdException("Event with id " + id + " not found"));
    }

    @Override
    public Event create(String title, String content, UUID categoryId, String location, LocalDateTime dateTime, UUID userId)
            throws CategoryNotFoundByIdException, UserNotFoundException {
        Category category = categoryService.getById(categoryId);
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        Event event = new Event(title, content, category, location, dateTime, user);
        return eventRepository.save(event);
    }

    @Override
    public Event update(UUID id, String title, String content) throws EventNotFoundByIdException {
        Event event = getById(id);
        event.setTitle(title);
        event.setContent(content);
        return eventRepository.save(event);
    }

    @Override
    public void deleteById(UUID id) throws EventNotFoundByIdException {
        getById(id);
        eventRepository.deleteById(id);
    }
}
