package me.kmilo.chatilo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Table(name = "USERS")
@Entity
@Getter
@EqualsAndHashCode
@ToString(exclude = "friends")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserEntity {

    @Id
    private String id;

    private String username;

    private String email;

    private String password;

    @ManyToMany
    @JoinTable(name = "USER_FRIENDS",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "FRIEND_ID"))
    @Builder.Default
    private Set<UserEntity> friends = new HashSet<>();

    public void addFriend(UserEntity friend) {
        getFriends().add(friend);
        friend.getFriends().add(this);
    }

    public void removeFriend(UserEntity friend) {
        getFriends().remove(friend);
        friend.getFriends().remove(this);
    }

}
