package me.kmilo.chatilo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kmilo.chatilo.entity.User;
import me.kmilo.chatilo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class ChatiloBackend implements CommandLineRunner {

    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(ChatiloBackend.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        User kmilo = User.builder()
                .id("123")
                .email("kamil@email.com")
                .username("kmilo")
                .password("pass")
                .build();

        userRepository.save(kmilo);

        User just = User.builder()
                .id("345")
                .email("just.minias@gmail.com")
                .username("just")
                .password("pass")
                .build();

        just.addFriend(kmilo);

        userRepository.save(just);

        log.debug("kmilo: {}", kmilo.getFriends());

        log.debug("just: {}", just.getFriends());

    }
}
