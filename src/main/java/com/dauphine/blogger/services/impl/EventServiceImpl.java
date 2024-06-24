package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.models.Event;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.repository.EventRepository;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.EventService;
import com.dauphine.blogger.services.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.services.exceptions.EventNotFoundByIdException;

import org.springframework.stereotype.Service;
import java.util.List;
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
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getAllByTitleOrContent(String titleOrContent) {
        return eventRepository.findAllByTitleOrContent(titleOrContent,titleOrContent);
    }

    @Override
    public Event getById(UUID id) throws EventNotFoundByIdException {
        return eventRepository.findById(id).orElseThrow(() -> new EventNotFoundByIdException("Post with id "
                + id + " not found"));
    }

    @Override
    public Event create(String title, String content, UUID categoryId) throws CategoryNotFoundByIdException {
        Category category = categoryService.getById(categoryId);
        Event event = new Event(title, content, category);
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