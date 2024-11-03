package me.kmilo.chatilo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.kmilo.chatilo.dto.TextMessage;
import me.kmilo.chatilo.entity.User;
import me.kmilo.chatilo.exception.InvalidAuthorizationException;
import me.kmilo.chatilo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@RestController
@Log4j2
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    @GetMapping("/api/auth")
    @CrossOrigin
    public ResponseEntity<Void> auth(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        log.info("Authorization header: " + authorizationHeader);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Basic ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .build();
        }

        String credentials = authorizationHeader.substring("Basic".length()).trim();
        byte[] decodedBytes = Base64.getDecoder().decode(credentials);
        String decoded = new String(decodedBytes, StandardCharsets.UTF_8);

        String[] parts = decoded.split(":", 2);
        String username = parts[0];
        String password = parts[1];

        log.info("Username: " + username);
        log.info("Password: " + password);


        if (isValidUser(username, password)) {
            return ResponseEntity.ok().build();
        }
        throw new InvalidAuthorizationException("Invalid username or password");
    }

    boolean isValidUser(String username, String password) {
        Optional<User> byUsername = userRepository.findByUsername(username);

        if (byUsername.isEmpty()) {
            return false;
        }

        return password.equals(byUsername.get().getPassword());
    }
}