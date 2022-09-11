package me.kmilo.chatilo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    SimpMessagingTemplate template;

    @PostMapping("/send")
    public void sendMessage(@RequestBody TextMessage textMessage) {
        template.convertAndSend("/topic/message", textMessage);
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
