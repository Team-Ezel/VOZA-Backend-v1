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

    @ElementCollection
    @Builder.Default
    Map<String, String> stringStringMap = new LinkedHashMap<>();

    @Embedded
    private LastChat lastChat;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class LastChat {
        private String lastChat;
        private LocalDateTime lastSendAt;

        public LastChat(String lastChat, String toTimeAgo) {
        }
    }

    public void updateLastChat(String lastChat) {
        this.lastChat.lastChat = lastChat;
        this.lastChat.lastSendAt = LocalDateTime.now();
    }

    public void putMessage(String sender, String message) {
        stringStringMap.put(sender, message);
    }
}
