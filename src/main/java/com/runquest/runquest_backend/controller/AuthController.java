package com.runquest.runquest_backend.controller;
import com.runquest.runquest_backend.dto.LoginRequest;
import com.runquest.runquest_backend.dto.PlayerResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.runquest.runquest_backend.service.PlayerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/auth")
public class AuthController {
        private final PlayerService playerService;


    public AuthController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/login")
    public PlayerResponse login(@RequestBody LoginRequest request) {

        return playerService.login(
                request.getEmail(),
                request.getPassword()
        );
    }
}
