package com.ezel.voza.domain.chat.repository;

import com.ezel.voza.domain.chat.entity.Room;
import com.ezel.voza.domain.group.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Boolean existsByGroup(Group group);

    Room findByGroup(Group group);
}
