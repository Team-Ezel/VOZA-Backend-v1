package com.ezel.voza.domain.chat.presenation.dto.response;

import com.ezel.voza.domain.chat.entity.RoomChat;
import com.ezel.voza.global.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ChatResponse {

    private String sender;
    private String message;
    private String sendAt;

    public static ChatResponse toMessage(RoomChat roomChat) {

        return ChatResponse.builder()
                .sender(roomChat.getSender())
                .message(roomChat.getMessage())
                .sendAt(DateUtil.toTimeAgo(roomChat.getCreatedDate()))
                .build();
    }
}
