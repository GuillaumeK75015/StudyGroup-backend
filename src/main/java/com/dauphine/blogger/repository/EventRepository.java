package com.dauphine.blogger.repository;

import com.dauphine.blogger.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    List<Event> findAllByCategoryId(UUID uuid);

    List<Event> findAllByTitleOrContent(String title, String content);
}