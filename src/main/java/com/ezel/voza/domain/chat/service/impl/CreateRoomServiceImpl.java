package com.ezel.voza.domain.chat.service.impl;

import com.ezel.voza.domain.chat.entity.Room;
import com.ezel.voza.domain.chat.exception.ExistRoomException;
import com.ezel.voza.domain.chat.repository.RoomRepository;
import com.ezel.voza.domain.chat.service.CreateRoomService;
import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.global.annotation.ServiceWithTransactional;
import com.ezel.voza.global.util.GroupUtil;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@ServiceWithTransactional
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

        Room room = Room.builder()
                .group(group)
                .lastChat(groupId + "에 대한 방이 생성되었습니다.")
                .lastSendAt(LocalDateTime.now())
                .build();

        roomRepository.save(room);
    }
}
