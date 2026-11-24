package com.runquest.runquest_backend.service;
import com.runquest.runquest_backend.dto.PlayerResponse;
import com.runquest.runquest_backend.dto.RunRequest;
import com.runquest.runquest_backend.dto.RunResponse;
import com.runquest.runquest_backend.entity.Player;
import com.runquest.runquest_backend.entity.Run;
import com.runquest.runquest_backend.exceptions.PlayerNotFoundException;
import com.runquest.runquest_backend.repository.PlayerRepository;
import com.runquest.runquest_backend.repository.RunRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final RunRepository runRepository;
    private final PasswordEncoder passwordEncoder;

    public PlayerService (PlayerRepository playerRepository, RunRepository runRepository, PasswordEncoder passwordEncoder){
        this.playerRepository = playerRepository;
        this.runRepository = runRepository;
        this.passwordEncoder = passwordEncoder;


    }

    public PlayerResponse createPlayer(Player player) {

        player.setPassword(passwordEncoder.encode(player.getPassword()));

        Player savedPlayer = playerRepository.save(player);

        return new PlayerResponse(
                savedPlayer.getId(),
                savedPlayer.getNickname(),
                savedPlayer.getEmail(),
                savedPlayer.getGold(),
                savedPlayer.getXp(),
                savedPlayer.getLevel()
        );
    }



    @Transactional
    public Player completeRun (Long playerId, RunRequest request){
        Player player = playerRepository
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

    public PlayerResponse login(String email, String password) {

        Player player = playerRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Email ou senha inválidos"));

        if (!passwordEncoder.matches(password, player.getPassword())) {
            throw new BadCredentialsException("Email ou senha inválidos");
        }

        return new PlayerResponse(
                player.getId(),
                player.getNickname(),
                player.getEmail(),
                player.getGold(),
                player.getXp(),
                player.getLevel()
        );
    }

}
