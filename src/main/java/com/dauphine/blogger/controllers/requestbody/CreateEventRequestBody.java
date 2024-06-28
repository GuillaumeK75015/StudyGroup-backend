package com.dauphine.blogger.controllers.requestbody;

import com.dauphine.blogger.models.Category;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CreateEventRequestBody(String title, String content, UUID categoryId, String location, LocalDateTime dateTime, String creatorName) {
}





