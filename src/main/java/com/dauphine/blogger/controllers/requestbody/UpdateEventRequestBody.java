package com.dauphine.blogger.controllers.requestbody;


import java.time.LocalDateTime;
import java.util.UUID;

public record UpdateEventRequestBody(String title, String content, String location, LocalDateTime dateTime, UUID categoryId, String lastModifiedBy) {
}
