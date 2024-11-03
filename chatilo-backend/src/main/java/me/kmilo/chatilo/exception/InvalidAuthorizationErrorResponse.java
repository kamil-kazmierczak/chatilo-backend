package me.kmilo.chatilo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class InvalidAuthorizationErrorResponse {
    private String error;
}