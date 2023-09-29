package com.ezel.voza.domain.chat.service.impl;

import com.ezel.voza.domain.chat.entity.Room;
import com.ezel.voza.domain.chat.presenation.dto.request.Message;
import com.ezel.voza.domain.chat.repository.RoomRepository;
import com.ezel.voza.domain.chat.service.ChatSaveService;
import com.ezel.voza.global.util.GroupUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatSaveServiceImpl implements ChatSaveService {

    private final RoomRepository roomRepository;
    private final GroupUtil groupUtil;

    @Override
    public void execute(Message message) {
        Room room = roomRepository.findByGroup(groupUtil.findGroupById(Long.valueOf(message.getGroupId())));

        room.putMessage(message.getSender(), message.getMessage());

        room.updateLastChat(message.getMessage());

        roomRepository.save(room);
    }
}
