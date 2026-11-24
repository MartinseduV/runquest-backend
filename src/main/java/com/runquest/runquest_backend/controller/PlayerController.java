
package com.runquest.runquest_backend.controller;

import com.runquest.runquest_backend.dto.PlayerResponse;
import com.runquest.runquest_backend.dto.RunRequest;
import com.runquest.runquest_backend.dto.RunResponse;
import com.runquest.runquest_backend.entity.Player;
import com.runquest.runquest_backend.repository.PlayerRepository;
import com.runquest.runquest_backend.service.PlayerService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerRepository playerRepository;
    private final PlayerService playerService;

    public PlayerController(
            PlayerRepository playerRepository,
            PlayerService playerService) {

        this.playerRepository = playerRepository;
        this.playerService = playerService;
    }

    @PostMapping
    public PlayerResponse create(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    @GetMapping
    public List<PlayerResponse> getPlayers() {
        return playerRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public PlayerResponse findPlayer(@PathVariable Long id) {

        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Jogador não encontrado"
                ));

        return toResponse(player);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public PlayerResponse changePlayer(
            @PathVariable Long id,
            @RequestBody Player updatedPlayer) {

        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Jogador não encontrado"
                ));

        player.setNickname(updatedPlayer.getNickname());
        player.setEmail(updatedPlayer.getEmail());
        player.setLevel(updatedPlayer.getLevel());
        player.setGold(updatedPlayer.getGold());
        player.setXp(updatedPlayer.getXp());

        Player savedPlayer = playerRepository.save(player);

        return toResponse(savedPlayer);
    }

    @PostMapping("/{id}/runs")
    public PlayerResponse completeRun(
            @PathVariable Long id,
            @Valid @RequestBody RunRequest runRequest) {

        Player player = playerService.completeRun(id, runRequest);

        return toResponse(player);
    }

    @GetMapping("/{id}/runs")
    public List<RunResponse> getPlayerRuns(@PathVariable Long id) {
        return playerService.getRunsByPlayerId(id);
    }

    private PlayerResponse toResponse(Player player) {

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
