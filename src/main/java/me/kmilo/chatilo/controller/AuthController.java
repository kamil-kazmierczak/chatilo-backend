package me.kmilo.chatilo.controller;

import lombok.extern.log4j.Log4j2;
import me.kmilo.chatilo.dto.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class AuthController {

    @GetMapping("/api/auth")
    public ResponseEntity<Void> auth() {
        log.info("Login success!");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}