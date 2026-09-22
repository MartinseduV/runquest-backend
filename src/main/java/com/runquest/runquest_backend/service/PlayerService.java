package com.runquest.runquest_backend.service;
import com.runquest.runquest_backend.dto.RunRequest;
import com.runquest.runquest_backend.dto.RunResponse;
import com.runquest.runquest_backend.entity.Player;
import com.runquest.runquest_backend.entity.Run;
import com.runquest.runquest_backend.exceptions.PlayerNotFoundException;
import com.runquest.runquest_backend.repository.PlayerRepository;
import com.runquest.runquest_backend.repository.RunRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final RunRepository runRepository;

    public PlayerService (PlayerRepository playerRepository, RunRepository runRepository){
        this.playerRepository = playerRepository;
        this.runRepository = runRepository;


    }

    @Transactional
    public Player completeRun (Long playerId, RunRequest request){
        Player player = playerRepository.findById(playerId).orElse(null);

        Player player1 = playerRepository
                .findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Player não encontrado"));


        Run run = new Run();

        run.setDistanceMeters(request.getDistanceMeters());
        run.setDurationSeconds(request.getDurationSeconds());
        run.setCreatedAt(LocalDateTime.now());
        run.setPlayer(player);

        runRepository.save(run);

        long xpGanho = request.getDistanceMeters() /100;

        long novoXp = player.getXp() + xpGanho;
        int level = player.getLevel();

        while (novoXp >= 100){
            novoXp -= 100;
            level++;

        }

        player.setXp(novoXp);
        player.setLevel(level);

        return playerRepository.save(player);


    }

    public List<RunResponse> getRunsByPlayerId (Long playerId){
        List<Run> runs = runRepository.findByPlayer_Id(playerId);

        return runs.stream()
                .map(run -> new RunResponse(
                        run.getId(),
                        run.getDistanceMeters(),
                        run.getDurationSeconds(),
                        run.getCreatedAt()
                ))
                .toList();
    }


}
