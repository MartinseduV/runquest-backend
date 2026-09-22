package com.runquest.runquest_backend.controller;

import com.runquest.runquest_backend.dto.RunRequest;
import com.runquest.runquest_backend.dto.RunResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.runquest.runquest_backend.entity.Player;
import com.runquest.runquest_backend.repository.PlayerRepository;
import com.runquest.runquest_backend.service.PlayerService;
import java.util.List;


@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerRepository playerRepository;
    private final PlayerService playerService;

    public PlayerController (PlayerRepository playerRepository, PlayerService service) {
        this.playerRepository = playerRepository;
        playerService = service;

    }

    @PostMapping
    public Player create(@RequestBody Player player){
        return playerRepository.save(player);
    }

    @GetMapping
    public List<Player> getPlayers(){
        return playerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Player findPlayer(@PathVariable Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id){
        playerRepository.deleteById(id);
    }

    @PutMapping ("/{id}")
    public Player changePlayer(@PathVariable Long id, @RequestBody Player playerr){
       Player player = playerRepository.findById(id).orElse(null);

       if (player == null){
           return null;
       }
       player.setNickname(player.getNickname());
       player.setEmail(playerr.getEmail());
       player.setLevel(playerr.getLevel());
       player.setGold(playerr.getGold());
       player.setXp(playerr.getXp());

       return playerRepository.save(player);
    }

    @PostMapping("/{id}/runs")
    public Player completeRun(
            @PathVariable Long id,
            @Valid
            @RequestBody RunRequest runRequest) {

        return playerService.completeRun(id, runRequest);
    }

    @GetMapping("/{id}/runs")
    public List<RunResponse> getPlayerRuns(@PathVariable Long id){

        return playerService.getRunsByPlayerId(id);
    }


}
