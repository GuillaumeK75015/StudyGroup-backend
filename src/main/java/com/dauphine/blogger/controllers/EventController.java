package com.dauphine.blogger.controllers;

import com.dauphine.blogger.controllers.requestbody.CreateEventRequestBody;
import com.dauphine.blogger.controllers.requestbody.RatingReviewRequest;
import com.dauphine.blogger.controllers.requestbody.UpdateEventRequestBody;
import com.dauphine.blogger.models.Event;
import com.dauphine.blogger.repository.CategoryRepository;
import com.dauphine.blogger.services.EventService;
import com.dauphine.blogger.services.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.services.exceptions.EventNotFoundByIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private CategoryRepository categoryRepository;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("")
    public ResponseEntity<List<Event>> getAllEvents(@RequestParam(required = false) String value) {
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

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Event>> getEventsByCategoryId(@PathVariable UUID categoryId) throws CategoryNotFoundByIdException {
        List<Event> events = eventService.getAllByCategoryId(categoryId);
        return ResponseEntity.ok(events);
    }

    @PostMapping("")
    public ResponseEntity<Event> createEvent(@RequestBody CreateEventRequestBody createEventRequestBody)
            throws CategoryNotFoundByIdException {
        Event event = eventService.create(createEventRequestBody.title(), createEventRequestBody.content(),
                createEventRequestBody.categoryId(), createEventRequestBody.location(), createEventRequestBody.dateTime(),
                createEventRequestBody.creatorName());
        return ResponseEntity
                .created(URI.create("v1/events/" + event.getId()))
                .body(event);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable UUID id, @RequestBody UpdateEventRequestBody updateEventRequestBody)
            throws EventNotFoundByIdException, CategoryNotFoundByIdException {
        Event event = eventService.update(id, updateEventRequestBody.title(), updateEventRequestBody.content(),
                updateEventRequestBody.location(), updateEventRequestBody.dateTime(),updateEventRequestBody.categoryId(), updateEventRequestBody.lastModifiedBy());
        return ResponseEntity
                .created(URI.create("v1/events/" + event.getId()))
                .body(event);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable UUID id) throws EventNotFoundByIdException {
        eventService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable UUID id) {
        try {
            Event event = eventService.getById(id);
            return ResponseEntity.ok(event);
        } catch (EventNotFoundByIdException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{id}/participants")
    public ResponseEntity<Event> addParticipant(@PathVariable UUID id, @RequestParam String participant) {
        try {
            Event updatedEvent = eventService.addParticipant(id, participant);
            return ResponseEntity.ok(updatedEvent);
        } catch (EventNotFoundByIdException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}/participants")
    public ResponseEntity<Event> removeParticipant(@PathVariable UUID id, @RequestParam String participant) {
        try {
            Event updatedEvent = eventService.removeParticipant(id, participant);
            return ResponseEntity.ok(updatedEvent);
        } catch (EventNotFoundByIdException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @PostMapping("/{id}/rating-review")
    public ResponseEntity<Event> addRatingReview(@PathVariable UUID id, @RequestBody RatingReviewRequest ratingReviewRequest) {
        try {
            Event updatedEvent = eventService.addRatingReview(id, ratingReviewRequest);
            return ResponseEntity.ok(updatedEvent);
        } catch (EventNotFoundByIdException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }






}
