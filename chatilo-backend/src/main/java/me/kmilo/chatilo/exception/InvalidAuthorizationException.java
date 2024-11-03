package me.kmilo.chatilo.exception;

public class InvalidAuthorizationException extends RuntimeException {
    public InvalidAuthorizationException(String message) {
        super(message);
    }
}