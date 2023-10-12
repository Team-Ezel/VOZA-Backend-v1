package com.ezel.voza.domain.chat.entity;

import com.ezel.voza.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RoomChat extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;

    @Column(nullable = false)
    private Long groupId;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
