package me.kmilo.chatilo.controller;

import lombok.extern.log4j.Log4j2;
import me.kmilo.chatilo.dto.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@Log4j2
public class ChatController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate; // may be used to manual sending msg

    @MessageMapping("/all") // message from frontend-client /app/all
    @SendTo("/topic/message") // message send next after retrieving
    public TextMessage broadcastMessage(@Payload TextMessage textMessage) {
        return textMessage.toBuilder()
                .time(Instant.now().toEpochMilli())
                .build();
    }

}
