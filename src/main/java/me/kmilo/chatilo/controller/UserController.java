package me.kmilo.chatilo.controller;

import lombok.extern.log4j.Log4j2;
import me.kmilo.chatilo.dto.TextMessage;
import me.kmilo.chatilo.dto.UserDto;
import me.kmilo.chatilo.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@Log4j2
public class UserController {

    @GetMapping("/api/user")
    public UserDto getCurrentUser(@AuthenticationPrincipal UserDetailsImpl principal) {
        return UserDto.builder()
                .id(principal.getId())
                .email(principal.getEmail())
                .username(principal.getUsername())
                .password(principal.getPassword())
                .build();
    }

}
