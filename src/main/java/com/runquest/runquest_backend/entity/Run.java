package com.runquest.runquest_backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Run {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)

    private Long id;
    private Long durationSeconds;
    private Long distanceMeters;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(Long durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public Long getDistanceMeters() {
        return distanceMeters;
    }

    public void setDistanceMeters(Long distanceMeters) {
        this.distanceMeters = distanceMeters;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
