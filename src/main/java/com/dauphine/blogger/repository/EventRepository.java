package com.dauphine.blogger.repository;

import com.dauphine.blogger.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID>, JpaSpecificationExecutor<Event> {

    List<Event> findAllByCategoryId(UUID uuid);

    List<Event> findAllByTitleOrContent(String title, String content);




}