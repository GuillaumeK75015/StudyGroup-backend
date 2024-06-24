package com.dauphine.blogger.controllers.requestbody;

import java.util.UUID;

public record CreateEventRequestBody(String title, String content, UUID categoryId) {
}
