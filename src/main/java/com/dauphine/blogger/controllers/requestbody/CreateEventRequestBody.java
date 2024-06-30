package com.dauphine.blogger.controllers.requestbody;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateEventRequestBody(String title, String content, UUID categoryId, String location, LocalDateTime dateTime, String creatorName) {
}





