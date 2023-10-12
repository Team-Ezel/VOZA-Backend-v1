package com.ezel.voza.domain.chat.presenation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class ChatListResponse {

    List<ChatResponse> chatResponses;
}
