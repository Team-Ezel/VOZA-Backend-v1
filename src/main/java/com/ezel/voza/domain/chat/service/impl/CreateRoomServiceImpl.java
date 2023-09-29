package com.ezel.voza.domain.chat.service.impl;

import com.ezel.voza.domain.chat.entity.Room;
import com.ezel.voza.domain.chat.exception.ExistRoomException;
import com.ezel.voza.domain.chat.repository.RoomRepository;
import com.ezel.voza.domain.chat.service.CreateRoomService;
import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.global.util.GroupUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class CreateRoomServiceImpl implements CreateRoomService {

    private final RoomRepository roomRepository;
    private final GroupUtil groupUtil;

    @Override
    public void execute(Long groupId) {

        Group group = groupUtil.findGroupById(groupId);

        if (roomRepository.existsByGroup(group)) {
            throw new ExistRoomException();
        }

        Room.LastChat lastChat = new Room.LastChat(
                "그룹" + groupId.toString() + "에 대한 채팅방이 생성되었습니다.",
                LocalDateTime.now()
        );

        Room room = Room.builder()
                .group(group)
                .stringStringMap(new LinkedHashMap<>())
                .lastChat(lastChat)
                .build();

        roomRepository.save(room);
    }
}
