package com.dauphine.blogger.controllers;

import com.dauphine.blogger.controllers.requestbody.CreateEventRequestBody;
import com.dauphine.blogger.controllers.requestbody.UpdateEventRequestBody;
import com.dauphine.blogger.models.Event;
import com.dauphine.blogger.services.EventService;
import com.dauphine.blogger.services.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.services.exceptions.EventNotFoundByIdException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("")
    public ResponseEntity<List<Event>> getAllEvents(@RequestParam(required = false) String value){
        List<Event> events = value == null || value.isBlank()
                ? eventService.getAll()
                : eventService.getAllByTitleOrContent(value);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Event>> searchEvents(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) String location) {
        List<Event> events = eventService.searchEvents(title, categoryId, location);
        return ResponseEntity.ok(events);
    }


    @GetMapping("/{categoryId}")
    public ResponseEntity<List<Event>> getEventsByCategoryId(@PathVariable UUID categoryId) throws CategoryNotFoundByIdException {
        List<Event> events = eventService.getAllByCategoryId(categoryId);
        return ResponseEntity.ok(events);
    }

    @PostMapping("")
    public ResponseEntity<Event> createEvent(@RequestBody CreateEventRequestBody createEventRequestBody)
            throws CategoryNotFoundByIdException {
        Event event = eventService.create(createEventRequestBody.title(), createEventRequestBody.content(),
                createEventRequestBody.categoryId(),createEventRequestBody.location(),createEventRequestBody.dateTime(),createEventRequestBody.userId());
        return ResponseEntity
                .created(URI.create("v1/events" + event.getId()))
                .body(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable UUID id, UpdateEventRequestBody updateEventRequestBody)
            throws EventNotFoundByIdException {
        Event event = eventService.update(id, updateEventRequestBody.title(), updateEventRequestBody.content());
        return ResponseEntity
                .created(URI.create("v1/events" + event.getId()))
                .body(event);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable UUID id) throws EventNotFoundByIdException {
        eventService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}