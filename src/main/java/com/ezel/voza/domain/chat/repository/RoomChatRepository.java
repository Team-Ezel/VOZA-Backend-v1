package com.ezel.voza.domain.chat.repository;

import com.ezel.voza.domain.chat.entity.RoomChat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomChatRepository extends JpaRepository<RoomChat, Long> {

    List<RoomChat> findByGroupId(Long groupId);
}
