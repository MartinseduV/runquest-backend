package com.runquest.runquest_backend.repository;

import com.runquest.runquest_backend.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
