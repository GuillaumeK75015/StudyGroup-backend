package com.dauphine.blogger.controllers.requestbody;

import com.dauphine.blogger.models.Category;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UpdateEventRequestBody(String title, String content, String location, LocalDateTime dateTime, UUID categoryId, String lastModifiedBy) {
}
