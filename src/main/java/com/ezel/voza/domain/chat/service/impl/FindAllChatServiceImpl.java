package com.ezel.voza.domain.chat.service.impl;

import com.ezel.voza.domain.chat.entity.RoomChat;
import com.ezel.voza.domain.chat.presenation.dto.response.ChatListResponse;
import com.ezel.voza.domain.chat.presenation.dto.response.ChatResponse;
import com.ezel.voza.domain.chat.repository.RoomChatRepository;
import com.ezel.voza.domain.chat.service.FindAllChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindAllChatServiceImpl implements FindAllChatService {

    private final RoomChatRepository roomChatRepository;

    @Override
    public ChatListResponse execute(Long groupId) {

        List<RoomChat> list = roomChatRepository.findByGroupId(groupId);

        return ChatListResponse.builder()
                .chatResponses(
                    list.stream()
                            .map(ChatResponse::toMessage)
                            .collect(Collectors.toList())
                )
                .build();
    }
}
