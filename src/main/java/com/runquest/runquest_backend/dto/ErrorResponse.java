package com.runquest.runquest_backend.dto;

public record ErrorResponse(
        int status,
        String message,
        String timeStamp,
        String path
) {

}
