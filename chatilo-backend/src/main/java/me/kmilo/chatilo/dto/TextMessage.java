package me.kmilo.chatilo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(toBuilder = true)
public class TextMessage {
    private String from;
    private String message;
    private long time;
}
