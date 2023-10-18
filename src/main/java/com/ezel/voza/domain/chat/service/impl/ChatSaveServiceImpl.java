package com.ezel.voza.domain.chat.service.impl;

import com.ezel.voza.domain.auth.exception.UserNotFoundException;
import com.ezel.voza.domain.chat.entity.Room;
import com.ezel.voza.domain.chat.entity.RoomChat;
import com.ezel.voza.domain.chat.exception.NotExistRoomUserException;
import com.ezel.voza.domain.chat.presenation.dto.request.Message;
import com.ezel.voza.domain.chat.repository.RoomChatRepository;
import com.ezel.voza.domain.chat.repository.RoomRepository;
import com.ezel.voza.domain.chat.service.ChatSaveService;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.domain.user.repository.UserRepository;
import com.ezel.voza.global.annotation.ServiceWithTransactional;
import com.ezel.voza.global.util.GroupUtil;
import lombok.RequiredArgsConstructor;

@ServiceWithTransactional
@RequiredArgsConstructor
public class ChatSaveServiceImpl implements ChatSaveService {

    private final RoomRepository roomRepository;
    private final GroupUtil groupUtil;
    private final RoomChatRepository roomChatRepository;
    private final UserRepository userRepository;

    @Override
    public void execute(Message message) {

        Room room = roomRepository.findByGroup(groupUtil.findGroupById(Long.valueOf(message.getGroupId())));
        socketUtil(message, room);

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

    private void socketUtil(Message message, Room room) {

        User user = userRepository.findByNickName(message.getSender())
                .orElseThrow(UserNotFoundException::new);

        if (!room.getGroup().getMembers().containsKey(user)) {
            throw new NotExistRoomUserException();
        }
    }
}
