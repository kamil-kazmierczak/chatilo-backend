package me.kmilo.chatilo.controller;

import lombok.extern.log4j.Log4j2;
import me.kmilo.chatilo.dto.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class ChatController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @SendTo("/topic/message")
    public TextMessage broadcastMessage(@Payload TextMessage textMessageDTO) {
        return textMessageDTO;
    }


    @PostMapping("/send")
    @CrossOrigin
    public TextMessage send(@RequestBody @Payload TextMessage textMessage) {
        log.error("MESSAGE SEND: {}", textMessage);
        simpMessagingTemplate.convertAndSend("/topic/message", textMessage);

        return textMessage;
    }

}
