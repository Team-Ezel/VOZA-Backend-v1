package com.ezel.voza.domain.chat.service.impl;

import com.ezel.voza.domain.chat.entity.Room;
import com.ezel.voza.domain.chat.entity.RoomChat;
import com.ezel.voza.domain.chat.presenation.dto.request.Message;
import com.ezel.voza.domain.chat.repository.RoomChatRepository;
import com.ezel.voza.domain.chat.repository.RoomRepository;
import com.ezel.voza.domain.chat.service.ChatSaveService;
import com.ezel.voza.global.util.GroupUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ChatSaveServiceImpl implements ChatSaveService {

    private final RoomRepository roomRepository;
    private final GroupUtil groupUtil;
    private final RoomChatRepository roomChatRepository;

    @Override
    public void execute(Message message) {
        Room room = roomRepository.findByGroup(groupUtil.findGroupById(Long.valueOf(message.getGroupId())));

        room.updateLastChat(message.getMessage());

        RoomChat roomChat = RoomChat.builder()
                .groupId(Long.valueOf(message.getGroupId()))
                .sender(message.getSender())
                .message(message.getMessage())
                .room(room)
                .build();

        roomRepository.save(room);
        roomChatRepository.save(roomChat);
    }
}
