package com.dauphine.blogger.repository;

import com.dauphine.blogger.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    List<Event> findAllByCategoryId(UUID uuid);

    List<Event> findAllByTitleOrContent(String title, String content);

    @Query("SELECT e FROM Event e WHERE "
            + "(:title IS NULL OR e.title LIKE %:title%) AND "
            + "(:categoryId IS NULL OR e.category.id = :categoryId) AND "
            + "(:location IS NULL OR e.location LIKE %:location%)")
    List<Event> searchEvents(@Param("title") String title, @Param("categoryId") UUID categoryId, @Param("location") String location);

}