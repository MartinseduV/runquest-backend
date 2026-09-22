package com.runquest.runquest_backend.dto;

import com.runquest.runquest_backend.entity.Run;

import java.time.LocalDateTime;

public class RunResponse {

    private Long id;
    private Long distanceMeters;
    private Long durationSeconds;
    private LocalDateTime createdAt;

    public RunResponse(Long id, Long distanceMeters, Long durationSeconds, LocalDateTime createdAt){
        this.id = id;
        this.distanceMeters = distanceMeters;
        this.durationSeconds = durationSeconds;
        this.createdAt = createdAt;
    }


    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getDurationSeconds() {
        return durationSeconds;
    }

    public Long getDistanceMeters() {
        return distanceMeters;
    }
}
