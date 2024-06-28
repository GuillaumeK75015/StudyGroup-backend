package com.dauphine.blogger.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "location")
    private String location;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "creator_name")
    private String creatorName;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @ElementCollection
    @CollectionTable(name = "event_participants", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "participant_name")
    private Set<String> participants = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventReview> reviews = new ArrayList<>();

    // Constructors, Getters and Setters

    public Event() {
    }

    public Event(String title, String content, Category category, String location, LocalDateTime dateTime, String creatorName) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.content = content;
        this.createdDate = LocalDateTime.now();
        this.category = category;
        this.location = location;
        this.dateTime = dateTime;
        this.creatorName = creatorName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Set<String> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<String> participants) {
        this.participants = participants;
    }

    public List<EventReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<EventReview> reviews) {
        this.reviews = reviews;
    }
}
