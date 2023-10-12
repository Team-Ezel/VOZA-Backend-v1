package com.ezel.voza.domain.chat.presenation;

import com.ezel.voza.domain.chat.presenation.dto.request.Message;
import com.ezel.voza.domain.chat.service.ChatSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final ChatSaveService chatSaveService;

    @MessageMapping("/chat")
    public void sendMessage(Message message) {
        chatSaveService.execute(message);
        simpMessageSendingOperations.convertAndSend("/sub/" + message.getGroupId(), message);
    }
}
