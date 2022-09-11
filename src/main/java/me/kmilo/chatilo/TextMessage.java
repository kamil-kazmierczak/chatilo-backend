package me.kmilo.chatilo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TextMessage {

    private String message;
    private long time = Instant.now().toEpochMilli();
    private String userId;
    private String roomNumber;
}
