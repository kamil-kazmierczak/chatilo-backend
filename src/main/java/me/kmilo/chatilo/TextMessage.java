package me.kmilo.chatilo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TextMessage {

    private String message;
    private LocalDateTime localDateTime = LocalDateTime.now();
    private String userId;
    private String roomNumber;
}
