package me.kmilo.chatilo;

import lombok.RequiredArgsConstructor;
import me.kmilo.chatilo.entity.UserEntity;
import me.kmilo.chatilo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ChatiloBackend implements CommandLineRunner {

    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(ChatiloBackend.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(UserEntity.builder()
                .id("123")
                .email("kamil@email.com")
                .username("kmilo")
                .password("pass")
                .build());

        userRepository.save(UserEntity.builder()
                .id("345")
                .email("just.minias@gmail.com")
                .username("Just")
                .password("password")
                .build());
    }
}
