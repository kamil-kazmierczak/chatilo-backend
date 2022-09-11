package pl.chatilo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.chatilo.app.security.JwtUtils;
import pl.chatilo.app.security.UserDetailsImpl;

@RestController
public class ChatController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    SimpMessagingTemplate template;

    @PostMapping("/send")
    public void sendMessage(@RequestBody TextMessage textMessage) {
        template.convertAndSend("/topic/message", textMessage);
    }

    @GetMapping("/api/test/user")
    @CrossOrigin(origins = {"http://localhost:3000"})
    public LoginRequest getUserTest() {
        return new LoginRequest("GOOD", "JOB");
    }

    @PostMapping("/api/auth/signin")
    @CrossOrigin(origins = {"http://localhost:3000"})
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail()));
    }

    @MessageMapping("/sendMessage")
    public void receiveMessage(@Payload TextMessage textMessage) {
        // receive message from client
    }


    @SendTo("/topic/message")
    public TextMessage broadcastMessage(@Payload TextMessage textMessage) {
        return textMessage;
    }

}
