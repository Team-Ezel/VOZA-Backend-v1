package com.ezel.voza.domain.chat.service.impl;

import com.ezel.voza.domain.chat.entity.RoomChat;
import com.ezel.voza.domain.chat.exception.NotAllowedFindChatException;
import com.ezel.voza.domain.chat.presenation.dto.response.ChatListResponse;
import com.ezel.voza.domain.chat.presenation.dto.response.ChatResponse;
import com.ezel.voza.domain.chat.repository.RoomChatRepository;
import com.ezel.voza.domain.chat.service.FindAllChatService;
import com.ezel.voza.global.annotation.ReadOnlyService;
import com.ezel.voza.global.util.GroupUtil;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ReadOnlyService
@RequiredArgsConstructor
public class FindAllChatServiceImpl implements FindAllChatService {

    private final RoomChatRepository roomChatRepository;
    private final GroupUtil groupUtil;
    private final UserUtil userUtil;

    @Override
    public ChatListResponse execute(Long groupId) {

        if (!groupUtil.findGroupById(groupId).getMembers().containsKey(userUtil.currentUser())) {
            throw new NotAllowedFindChatException();
        }

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
