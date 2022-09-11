package me.kmilo.chatilo;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class ChatController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;


    @PostMapping("/send")
    @CrossOrigin
    public TextMessage send(@RequestBody TextMessage textMessage) {
        log.error("MESSAGE SEND: {}", textMessage);
        simpMessagingTemplate.convertAndSend("/topic/message", textMessage);

        return textMessage;
    }

    @GetMapping("/basicauth")
    @CrossOrigin
    public void auth() {
        log.error("SUCCESS");
    }

}
