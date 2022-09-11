package pl.chatilo.app;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JwtResponse {
    String token;
    String id;
    String username;
    String email;
}
