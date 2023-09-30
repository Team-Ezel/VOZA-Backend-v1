package com.ezel.voza.domain.chat.service;

import com.ezel.voza.domain.chat.presenation.dto.response.ChatListResponse;

import java.util.Map;

public interface FindAllChatService {

        ChatListResponse execute(Long groupId);
}
