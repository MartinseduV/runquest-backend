package com.runquest.runquest_backend.repository;

import com.runquest.runquest_backend.entity.Run;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RunRepository extends JpaRepository<Run, Long> {

    List<Run> findByPlayer_Id(Long playerId);
}
