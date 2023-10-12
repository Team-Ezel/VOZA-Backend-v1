package com.ezel.voza.domain.chat.entity;

import com.ezel.voza.domain.group.entity.Group;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(nullable = false)
    private String lastChat;

    private LocalDateTime lastSendAt;

    public void updateLastChat(String message) {
        this.lastChat = message;
        this.lastSendAt = LocalDateTime.now();
    }
}
