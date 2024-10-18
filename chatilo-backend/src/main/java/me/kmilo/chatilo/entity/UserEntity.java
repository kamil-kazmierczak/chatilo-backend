package me.kmilo.chatilo.entity;

import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "USERS")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserEntity {

    @Id
    private String id;

    private String username;

    private String email;

    private String password;

}
